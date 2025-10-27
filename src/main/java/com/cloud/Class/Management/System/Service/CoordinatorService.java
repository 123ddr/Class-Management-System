package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.Coordinator;
import com.cloud.Class.Management.System.Repository.CoordinatorRepository;
import com.cloud.Class.Management.System.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CoordinatorService {

    private final CoordinatorRepository coordinatorRepository;
    private final DepartmentRepository departmentRepository;

    public CoordinatorService(CoordinatorRepository coordinatorRepository, DepartmentRepository departmentRepository) {
        this.coordinatorRepository = coordinatorRepository;
        this.departmentRepository = departmentRepository;
    }

    public String createCoordinator(Coordinator coordinator) throws ExecutionException, InterruptedException {
        // Validate department exists
        if (departmentRepository.getDepartmentById(coordinator.getDepartmentId()) == null) {
            throw new IllegalArgumentException("Department does not exist!");
        }
        return coordinatorRepository.saveCoordinator(coordinator);
    }

    public Coordinator getCoordinator(String coordinatorId) throws ExecutionException, InterruptedException {
        return coordinatorRepository.getCoordinatorById(coordinatorId);
    }

    public List<Coordinator> getAllCoordinators() throws ExecutionException, InterruptedException {
        return coordinatorRepository.getAllCoordinators();
    }

    public String updateCoordinator(Coordinator coordinator) throws ExecutionException, InterruptedException {
        // Validate department exists
        if (departmentRepository.getDepartmentById(coordinator.getDepartmentId()) == null) {
            throw new IllegalArgumentException("Department does not exist!");
        }
        return coordinatorRepository.updateCoordinator(coordinator);
    }

    public String deleteCoordinator(String coordinatorId) throws ExecutionException, InterruptedException {
        return coordinatorRepository.deleteCoordinator(coordinatorId);
    }
}
