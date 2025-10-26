package com.cloud.Class.Management.System.Repository;

import com.cloud.Class.Management.System.Dto.CourseSummaryDto;
import com.cloud.Class.Management.System.Dto.TeacherSummaryDto;
import com.cloud.Class.Management.System.Entity.Course;
import com.cloud.Class.Management.System.Entity.Teacher;
import com.cloud.Class.Management.System.Entity.TeacherCourse;
import com.google.firebase.database.*;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class TeacherCourseRepository {

    private final FirebaseDatabase database;

    public TeacherCourseRepository() {
        this.database = FirebaseDatabase.getInstance();
    }

    public List<TeacherSummaryDto> findTeachersByCourseId(Long courseId) {
        String courseKey = String.valueOf(courseId);
        // 1) Find all teacher-course mappings for the course
        List<TeacherCourse> mappings = queryTeacherCoursesByField("course_id", courseKey);

        if (mappings.isEmpty()) return List.of();

        // 2) Fetch each teacher and map to summary
        Set<String> teacherIds = new LinkedHashSet<>();
        for (TeacherCourse tc : mappings) {
            if (tc != null && tc.getTeacher_id() != null) {
                teacherIds.add(tc.getTeacher_id());
            }
        }

        List<TeacherSummaryDto> result = new ArrayList<>();
        for (String tid : teacherIds) {
            Teacher teacher = readOnce("teachers/" + tid, Teacher.class);
            if (teacher != null) {
                result.add(new TeacherSummaryDto(
                        teacher.getId(),
                        teacher.getFirstName(),
                        teacher.getLastName(),
                        teacher.getEmail()
                ));
            }
        }

        // Order by lastName, firstName like the JPQL intended
        result.sort(Comparator
                .comparing(TeacherSummaryDto::lastName, Comparator.nullsLast(String::compareToIgnoreCase))
                .thenComparing(TeacherSummaryDto::firstName, Comparator.nullsLast(String::compareToIgnoreCase)));
        return result;
    }

    public List<CourseSummaryDto> findCoursesByTeacherId(Long teacherId) {
        String teacherKey = String.valueOf(teacherId);
        // 1) Find all teacher-course mappings for the teacher
        List<TeacherCourse> mappings = queryTeacherCoursesByField("teacher_id", teacherKey);

        if (mappings.isEmpty()) return List.of();

        // 2) Fetch each course and map to summary
        Set<String> courseIds = new LinkedHashSet<>();
        for (TeacherCourse tc : mappings) {
            if (tc != null && tc.getCourse_id() != null) {
                courseIds.add(tc.getCourse_id());
            }
        }

        List<CourseSummaryDto> result = new ArrayList<>();
        for (String cid : courseIds) {
            Course course = readOnce("courses/" + cid, Course.class);
            if (course != null) {
                result.add(new CourseSummaryDto(
                        course.getId(),
                        course.getCode(),
                        course.getTitle()
                ));
            }
        }

        // Order by course code asc like the JPQL intended
        result.sort(Comparator.comparing(CourseSummaryDto::code, Comparator.nullsLast(String::compareToIgnoreCase)));
        return result;
    }

    private List<TeacherCourse> queryTeacherCoursesByField(String field, String value) {
        DatabaseReference ref = database.getReference("teacherCourses");
        Query query = ref.orderByChild(field).equalTo(value);

        AtomicReference<List<TeacherCourse>> resultRef = new AtomicReference<>(new ArrayList<>());
        AtomicReference<DatabaseError> errorRef = new AtomicReference<>(null);
        CountDownLatch latch = new CountDownLatch(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<TeacherCourse> list = new ArrayList<>();
                for (DataSnapshot child : snapshot.getChildren()) {
                    TeacherCourse tc = child.getValue(TeacherCourse.class);
                    // Fallback: if null, try to read fields manually
                    if (tc == null) {
                        tc = new TeacherCourse();
                        Object id = child.child("teacher_course_id").getValue();
                        Object tid = child.child("teacher_id").getValue();
                        Object cid = child.child("course_id").getValue();
                        tc.setTeacher_course_id(id != null ? String.valueOf(id) : child.getKey());
                        tc.setTeacher_id(tid != null ? String.valueOf(tid) : null);
                        tc.setCourse_id(cid != null ? String.valueOf(cid) : null);
                    }
                    list.add(tc);
                }
                resultRef.set(list);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                errorRef.set(error);
                latch.countDown();
            }
        });

        awaitLatch(latch, errorRef);
        return resultRef.get();
    }

    private <T> T readOnce(String path, Class<T> clazz) {
        DatabaseReference ref = database.getReference(path);
        AtomicReference<T> resultRef = new AtomicReference<>(null);
        AtomicReference<DatabaseError> errorRef = new AtomicReference<>(null);
        CountDownLatch latch = new CountDownLatch(1);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                T obj = snapshot.getValue(clazz);
                resultRef.set(obj);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                errorRef.set(error);
                latch.countDown();
            }
        });

        awaitLatch(latch, errorRef);
        return resultRef.get();
    }

    private void awaitLatch(CountDownLatch latch, AtomicReference<DatabaseError> errorRef) {
        try {
            boolean ok = latch.await(15, TimeUnit.SECONDS);
            if (!ok) {
                throw new IllegalStateException("Timed out waiting for Firebase response");
            }
            if (errorRef.get() != null) {
                throw new IllegalStateException("Firebase error: " + errorRef.get());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for Firebase response", e);
        }
    }
}
