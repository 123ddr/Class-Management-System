package com.cloud.Class.Management.System.Service;

import com.cloud.Class.Management.System.Dto.ClassroomCreateRequest;
import com.cloud.Class.Management.System.Dto.ClassroomResponse;
import com.cloud.Class.Management.System.Dto.ClassroomUpdateRequest;
import com.cloud.Class.Management.System.Entity.Classroom;
import com.cloud.Class.Management.System.Repository.ClassroomRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    // ----------------- SUPERADMIN ONLY: CREATE -----------------
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ClassroomResponse create(ClassroomCreateRequest req) {
        Classroom c = new Classroom();
        c.setName(req.name());
        c.setBuildingId(req.buildingId());
        c.setBuildingNumber(req.buildingNumber());
        c.setCapacity(req.capacity());
        Classroom saved = classroomRepository.save(c);
        return toResponse(saved);
    }

    // ----------------- SUPERADMIN ONLY: UPDATE (capacity & building) -----------------
    @PreAuthorize("hasRole('SUPERADMIN')")
    public ClassroomResponse update(Long classroomId, ClassroomUpdateRequest req) {
        Classroom c = classroomRepository.findById(classroomId)
                .orElseThrow(() -> new IllegalArgumentException("Classroom not found: " + classroomId));
        if (req.capacity() != null) c.setCapacity(req.capacity());
        if (req.buildingId() != null) c.setBuildingId(req.buildingId());
        if (req.buildingNumber() != null && !req.buildingNumber().isBlank()) c.setBuildingNumber(req.buildingNumber());
        Classroom saved = classroomRepository.save(c);
        return toResponse(saved);
    }

    // ----------------- SUPERADMIN ONLY: DELETE -----------------
    @PreAuthorize("hasRole('SUPERADMIN')")
    public void delete(Long classroomId) {
        if (!classroomRepository.existsById(classroomId)) {
            throw new IllegalArgumentException("Classroom not found: " + classroomId);
        }
        classroomRepository.deleteById(classroomId);
    }

    // ----------------- READS (COORDINATOR or SUPERADMIN) -----------------

    // View all registered classrooms for a building (by buildingId)
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('SUPERADMIN','COORDINATOR')")
    public List<ClassroomResponse> getByBuildingId(Long buildingId) {
        return classroomRepository.findByBuildingId(buildingId)
                .stream().map(this::toResponse).toList();
    }

    // View all registered classrooms for a building with minimum capacity
    @Transactional(readOnly = true)
    @PreAuthorize("hasAnyRole('SUPERADMIN','COORDINATOR')")
    public List<ClassroomResponse> getByBuildingIdAndCapacity(Long buildingId, Integer minCapacity) {
        return classroomRepository.findByBuildingIdAndCapacityGreaterThanEqual(buildingId, minCapacity)
                .stream().map(this::toResponse).toList();
    }

    // Superadmin can view all assigned classes with the building number (interpreted as: list classrooms grouped/filterable by buildingNumber)
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<ClassroomResponse> getByBuildingNumber(String buildingNumber) {
        return classroomRepository.findByBuildingNumber(buildingNumber)
                .stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('SUPERADMIN')")
    public List<ClassroomResponse> getByBuildingNumberAndCapacity(String buildingNumber, Integer minCapacity) {
        return classroomRepository.findByBuildingNumberAndCapacityGreaterThanEqual(buildingNumber, minCapacity)
                .stream().map(this::toResponse).toList();
    }

    private ClassroomResponse toResponse(Classroom c) {
        return new ClassroomResponse(
                c.getId(),
                c.getName(),
                c.getBuildingId(),
                c.getBuildingNumber(),
                c.getCapacity()
        );
    }
}
