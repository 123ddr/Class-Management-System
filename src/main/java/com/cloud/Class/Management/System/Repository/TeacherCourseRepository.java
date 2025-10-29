package com.cloud.Class.Management.System.Repository;


import com.cloud.Class.Management.System.Entity.TeacherCourse;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TeacherCourseRepository {

    private static final String COLLECTION_NAME = "teacher_courses";

    private final Firestore firestore;

    private CollectionReference getCollection() {
        return firestore.collection(COLLECTION_NAME);
    }

    public String saveTeacherCourse(TeacherCourse teacherCourse) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(teacherCourse.getTeacherCourseId()).set(teacherCourse);
        return future.get().getUpdateTime().toString();
    }

    public TeacherCourse getTeacherCourseById(String id) throws ExecutionException, InterruptedException {
        return getCollection().document(id).get().get().toObject(TeacherCourse.class);
    }

    public List<TeacherCourse> getAllTeacherCourses() throws ExecutionException, InterruptedException {
        return getCollection().get().get()
                .getDocuments()
                .stream()
                .map(doc -> doc.toObject(TeacherCourse.class))
                .collect(Collectors.toList());
    }

    public String updateTeacherCourse(TeacherCourse teacherCourse) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(teacherCourse.getTeacherCourseId()).set(teacherCourse);
        return future.get().getUpdateTime().toString();
    }

    public String deleteTeacherCourse(String id) throws ExecutionException, InterruptedException {
        ApiFuture<WriteResult> future = getCollection().document(id).delete();
        return future.get().getUpdateTime().toString();
    }

    // Optional: Get courses by teacher
    public List<TeacherCourse> getByTeacherId(String teacherId) throws ExecutionException, InterruptedException {
        return getAllTeacherCourses().stream()
                .filter(tc -> tc.getTeacherId().equals(teacherId))
                .toList();
    }

    // Optional: Get teachers by course
    public List<TeacherCourse> getByCourseId(String courseId) throws ExecutionException, InterruptedException {
        return getAllTeacherCourses().stream()
                .filter(tc -> tc.getCourseId().equals(courseId))
                .toList();
    }
}
