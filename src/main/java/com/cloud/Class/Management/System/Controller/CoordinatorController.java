package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.Coordinator;
import com.cloud.Class.Management.System.Service.CoordinatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/coordinators")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @PostMapping
    public ResponseEntity<String> createCoordinator(@RequestBody Coordinator coordinator) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(coordinatorService.createCoordinator(coordinator));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordinator> getCoordinator(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(coordinatorService.getCoordinator(id));
    }

    @GetMapping
    public ResponseEntity<List<Coordinator>> getAllCoordinators() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(coordinatorService.getAllCoordinators());
    }

    @PutMapping
    public ResponseEntity<String> updateCoordinator(@RequestBody Coordinator coordinator) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(coordinatorService.updateCoordinator(coordinator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCoordinator(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(coordinatorService.deleteCoordinator(id));
    }
}
