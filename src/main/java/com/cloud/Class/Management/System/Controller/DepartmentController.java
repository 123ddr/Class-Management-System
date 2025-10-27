package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.Department;
import com.cloud.Class.Management.System.Service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<String> createDepartment(@RequestBody Department department) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @PutMapping
    public ResponseEntity<String> updateDepartment(@RequestBody Department department) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(departmentService.updateDepartment(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(departmentService.deleteDepartment(id));
    }
}
