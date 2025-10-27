package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.Teacher;
import com.cloud.Class.Management.System.Repository.DepartmentRepository;
import com.cloud.Class.Management.System.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    public TeacherService(TeacherRepository teacherRepository, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.departmentRepository = departmentRepository;
    }

    public String createTeacher(Teacher teacher) throws ExecutionException, InterruptedException {
        // Check if department exists
        if (departmentRepository.getDepartmentById(teacher.getDepartmentId()) == null) {
            throw new IllegalArgumentException("Department does not exist!");
        }
        return teacherRepository.saveTeacher(teacher);
    }

    public Teacher getTeacher(String teacherId) throws ExecutionException, InterruptedException {
        return teacherRepository.getTeacherById(teacherId);
    }

    public List<Teacher> getAllTeachers() throws ExecutionException, InterruptedException {
        return teacherRepository.getAllTeachers();
    }

    public String updateTeacher(Teacher teacher) throws ExecutionException, InterruptedException {
        // Validate department on update
        if (departmentRepository.getDepartmentById(teacher.getDepartmentId()) == null) {
            throw new IllegalArgumentException("Department does not exist!");
        }
        return teacherRepository.updateTeacher(teacher);
    }

    public String deleteTeacher(String teacherId) throws ExecutionException, InterruptedException {
        return teacherRepository.deleteTeacher(teacherId);
    }
}
