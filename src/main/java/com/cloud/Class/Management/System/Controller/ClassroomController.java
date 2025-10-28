package com.cloud.Class.Management.System.Controller;

import com.cloud.Class.Management.System.Service.ClassroomService;
import com.cloud.Class.Management.System.Dto.ClassroomCreateRequest;
import com.cloud.Class.Management.System.Dto.ClassroomResponse;
import com.cloud.Class.Management.System.Dto.ClassroomUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    // ----------------- SUPERADMIN ONLY: CREATE -----------------
    @PostMapping
    public ResponseEntity<ClassroomResponse> create(@Valid @RequestBody ClassroomCreateRequest req) {
        return ResponseEntity.ok(classroomService.create(req));
    }

    // ----------------- SUPERADMIN ONLY: UPDATE -----------------
    @PutMapping("/{id}")
    public ResponseEntity<ClassroomResponse> update(@PathVariable Long id,
                                                    @Valid @RequestBody ClassroomUpdateRequest req) {
        return ResponseEntity.ok(classroomService.update(id, req));
    }

    // ----------------- SUPERADMIN ONLY: DELETE -----------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classroomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ----------------- READS (COORDINATOR or SUPERADMIN) -----------------

    // View by buildingId
    @GetMapping("/by-building/{buildingId}")
    public ResponseEntity<List<ClassroomResponse>> getByBuildingId(@PathVariable Long buildingId) {
        return ResponseEntity.ok(classroomService.getByBuildingId(buildingId));
    }

    // View by buildingId with minimum capacity
    @GetMapping("/by-building/{buildingId}/capacity/{minCapacity}")
    public ResponseEntity<List<ClassroomResponse>> getByBuildingIdAndCapacity(@PathVariable Long buildingId,
                                                                              @PathVariable Integer minCapacity) {
        return ResponseEntity.ok(classroomService.getByBuildingIdAndCapacity(buildingId, minCapacity));
    }

    // Superadmin: view by buildingNumber
    @GetMapping("/by-building-number/{buildingNumber}")
    public ResponseEntity<List<ClassroomResponse>> getByBuildingNumber(@PathVariable String buildingNumber) {
        return ResponseEntity.ok(classroomService.getByBuildingNumber(buildingNumber));
    }

    // Superadmin: view by buildingNumber with minimum capacity
    @GetMapping("/by-building-number/{buildingNumber}/capacity/{minCapacity}")
    public ResponseEntity<List<ClassroomResponse>> getByBuildingNumberAndCapacity(@PathVariable String buildingNumber,
                                                                                  @PathVariable Integer minCapacity) {
        return ResponseEntity.ok(classroomService.getByBuildingNumberAndCapacity(buildingNumber, minCapacity));
    }
}
