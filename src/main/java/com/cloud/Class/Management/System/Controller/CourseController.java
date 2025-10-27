package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.Course;
import com.cloud.Class.Management.System.Service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.getCourse(id));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @PutMapping
    public ResponseEntity<String> updateCourse(@RequestBody Course course) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.updateCourse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Course>> getCoursesByDepartment(@PathVariable String departmentId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(courseService.getCoursesByDepartment(departmentId));
    }
}
