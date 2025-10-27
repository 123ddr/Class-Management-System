package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.BatchRosterEmail;
import com.cloud.Class.Management.System.Service.BatchRosterEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/batch-rosters")
public class BatchRosterEmailController {

    private final BatchRosterEmailService service;

    public BatchRosterEmailController(BatchRosterEmailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody BatchRosterEmail roster) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.createRoster(roster));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchRosterEmail> get(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getRoster(id));
    }

    @GetMapping
    public ResponseEntity<List<BatchRosterEmail>> getAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getAllRosters());
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody BatchRosterEmail roster) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.updateRoster(roster));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.deleteRoster(id));
    }

    @GetMapping("/batch/{batchId}")
    public ResponseEntity<List<BatchRosterEmail>> getByBatch(@PathVariable String batchId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getRostersByBatch(batchId));
    }
}
