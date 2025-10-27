package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.TeacherCourse;
import com.cloud.Class.Management.System.Repository.CourseRepository;
import com.cloud.Class.Management.System.Repository.TeacherCourseRepository;
import com.cloud.Class.Management.System.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TeacherCourseService {

    private final TeacherCourseRepository repository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    public TeacherCourseService(TeacherCourseRepository repository,
                                TeacherRepository teacherRepository,
                                CourseRepository courseRepository) {
        this.repository = repository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public String createTeacherCourse(TeacherCourse tc) throws ExecutionException, InterruptedException {
        if (teacherRepository.getTeacherById(tc.getTeacherId()) == null) {
            throw new IllegalArgumentException("Teacher does not exist!");
        }
        if (courseRepository.getCourseById(tc.getCourseId()) == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }
        return repository.saveTeacherCourse(tc);
    }

    public TeacherCourse getTeacherCourse(String id) throws ExecutionException, InterruptedException {
        return repository.getTeacherCourseById(id);
    }

    public List<TeacherCourse> getAllTeacherCourses() throws ExecutionException, InterruptedException {
        return repository.getAllTeacherCourses();
    }

    public String updateTeacherCourse(TeacherCourse tc) throws ExecutionException, InterruptedException {
        if (teacherRepository.getTeacherById(tc.getTeacherId()) == null) {
            throw new IllegalArgumentException("Teacher does not exist!");
        }
        if (courseRepository.getCourseById(tc.getCourseId()) == null) {
            throw new IllegalArgumentException("Course does not exist!");
        }
        return repository.updateTeacherCourse(tc);
    }

    public String deleteTeacherCourse(String id) throws ExecutionException, InterruptedException {
        return repository.deleteTeacherCourse(id);
    }

    public List<TeacherCourse> getCoursesByTeacher(String teacherId) throws ExecutionException, InterruptedException {
        return repository.getByTeacherId(teacherId);
    }

    public List<TeacherCourse> getTeachersByCourse(String courseId) throws ExecutionException, InterruptedException {
        return repository.getByCourseId(courseId);
    }
}
