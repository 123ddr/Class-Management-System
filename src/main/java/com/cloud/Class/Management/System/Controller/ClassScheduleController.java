package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.ClassSchedule;
import com.cloud.Class.Management.System.Service.ClassScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/class-schedules")
public class ClassScheduleController {

    private final ClassScheduleService service;

    public ClassScheduleController(ClassScheduleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ClassSchedule schedule) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.createClassSchedule(schedule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSchedule> get(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getClassSchedule(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassSchedule>> getAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getAllClassSchedules());
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClassSchedule schedule) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.updateClassSchedule(schedule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.deleteClassSchedule(id));
    }
}
