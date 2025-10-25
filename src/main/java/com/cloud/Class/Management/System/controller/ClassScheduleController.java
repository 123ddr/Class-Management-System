package com.cloud.Class.Management.System.controller;






import com.cloud.Class.Management.System.Entity.ClassSchedule;
import com.cloud.Class.Management.System.Service.ClassScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/schedules")
public class ClassScheduleController {

    @Autowired
    private ClassScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<String> createSchedule(@Valid @RequestBody ClassSchedule schedule)
            throws ExecutionException, InterruptedException {
        String scheduleId = scheduleService.createSchedule(schedule);
        return ResponseEntity.ok(scheduleId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassSchedule> getSchedule(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        ClassSchedule schedule = scheduleService.getSchedule(id);
        return schedule != null ? ResponseEntity.ok(schedule) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<ClassSchedule>> getAllSchedules()
            throws ExecutionException, InterruptedException {
        List<ClassSchedule> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(@PathVariable String id, @Valid @RequestBody ClassSchedule schedule)
            throws ExecutionException, InterruptedException {
        scheduleService.updateSchedule(id, schedule);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().build();
    }
}