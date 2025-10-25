package com.cloud.Class.Management.System.controller;



import com.cloud.Class.Management.System.Entity.BatchRosterMail;
import com.cloud.Class.Management.System.Service.BatchRosterMailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/rosters")
public class BatchRosterMailController {

    @Autowired
    private BatchRosterMailService rosterService;

    @PostMapping
    public ResponseEntity<String> createRoster(@Valid @RequestBody BatchRosterMail roster)
            throws ExecutionException, InterruptedException {
        String rosterId = rosterService.createRoster(roster);
        return ResponseEntity.ok(rosterId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BatchRosterMail> getRoster(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        BatchRosterMail roster = rosterService.getRoster(id);
        return roster != null ? ResponseEntity.ok(roster) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<BatchRosterMail>> getAllRosters()
            throws ExecutionException, InterruptedException {
        List<BatchRosterMail> rosters = rosterService.getAllRosters();
        return ResponseEntity.ok(rosters);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRoster(@PathVariable String id, @Valid @RequestBody BatchRosterMail roster)
            throws ExecutionException, InterruptedException {
        rosterService.updateRoster(id, roster);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoster(@PathVariable String id)
            throws ExecutionException, InterruptedException {
        rosterService.deleteRoster(id);
        return ResponseEntity.ok().build();
    }
}