package com.cloud.Class.Management.System.Controller;


import com.cloud.Class.Management.System.Entity.ClassRoom;
import com.cloud.Class.Management.System.Service.ClassRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/classrooms")
public class ClassRoomController {

    private final ClassRoomService service;

    public ClassRoomController(ClassRoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ClassRoom classroom) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.createClassRoom(classroom));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassRoom> get(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getClassRoom(id));
    }

    @GetMapping
    public ResponseEntity<List<ClassRoom>> getAll() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.getAllClassRooms());
    }

    @PutMapping
    public ResponseEntity<String> update(@RequestBody ClassRoom classroom) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.updateClassRoom(classroom));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable String id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(service.deleteClassRoom(id));
    }
}
