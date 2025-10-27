package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.Batch;
import com.cloud.Class.Management.System.Service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    private final BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping
    public ResponseEntity<String> createBatch(@RequestBody Batch batch) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(batchService.createBatch(batch));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Batch> getBatch(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(batchService.getBatch(id));
    }

    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(batchService.getAllBatches());
    }

    @PutMapping
    public ResponseEntity<String> updateBatch(@RequestBody Batch batch) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(batchService.updateBatch(batch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBatch(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(batchService.deleteBatch(id));
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Batch>> getBatchesByCourse(@PathVariable String courseId) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(batchService.getBatchesByCourse(courseId));
    }
}
