package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.TeacherCourse;
import com.cloud.Class.Management.System.Service.TeacherCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/teacher-courses")
public class TeacherCourseController {

    private final TeacherCourseService service;

    public TeacherCourseController(TeacherCourseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody TeacherCourse tc) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.createTeacherCourse(tc));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherCourse> get(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getTeacherCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherCourse>> getAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getAllTeacherCourses());
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody TeacherCourse tc) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.updateTeacherCourse(tc));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.deleteTeacherCourse(id));
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TeacherCourse>> getCoursesByTeacher(@PathVariable String teacherId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getCoursesByTeacher(teacherId));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TeacherCourse>> getTeachersByCourse(@PathVariable String courseId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getTeachersByCourse(courseId));
    }
}
