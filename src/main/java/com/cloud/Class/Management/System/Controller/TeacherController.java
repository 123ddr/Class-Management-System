package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.Teacher;
import com.cloud.Class.Management.System.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(teacherService.createTeacher(teacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @PutMapping
    public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(teacherService.updateTeacher(teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(teacherService.deleteTeacher(id));
    }
}
