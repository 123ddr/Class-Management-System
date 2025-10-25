package com.cloud.Class.Management.System.controller;




import com.cloud.Class.Management.System.Entity.Batch;
import com.cloud.Class.Management.System.Service.BatchService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/batches")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @PostMapping
    public ResponseEntity<String> createBatch(@Valid @RequestBody Batch batch)
            throws ExecutionException, InterruptedException {
        String batchId = batchService.createBatch(batch);
        return ResponseEntity.ok(batchId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Batch> getBatch(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        Batch batch = batchService.getBatch(id);
        return batch != null ? ResponseEntity.ok(batch) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Batch>> getAllBatches()
            throws ExecutionException, InterruptedException {
        List<Batch> batches = batchService.getAllBatches();
        return ResponseEntity.ok(batches);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBatch(@PathVariable String id, @Valid @RequestBody Batch batch)
            throws ExecutionException, InterruptedException {
        batchService.updateBatch(id, batch);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBatch(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        batchService.deleteBatch(id);
        return ResponseEntity.ok().build();
    }
}