package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.Department;
import com.cloud.Class.Management.System.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public String createDepartment(Department department) throws ExecutionException, InterruptedException {
        return departmentRepository.saveDepartment(department);
    }

    public Department getDepartment(String departmentId) throws ExecutionException, InterruptedException {
        return departmentRepository.getDepartmentById(departmentId);
    }

    public List<Department> getAllDepartments() throws ExecutionException, InterruptedException {
        return departmentRepository.getAllDepartments();
    }

    public String updateDepartment(Department department) throws ExecutionException, InterruptedException {
        return departmentRepository.updateDepartment(department);
    }

    public String deleteDepartment(String departmentId) throws ExecutionException, InterruptedException {
        return departmentRepository.deleteDepartment(departmentId);
    }
}
