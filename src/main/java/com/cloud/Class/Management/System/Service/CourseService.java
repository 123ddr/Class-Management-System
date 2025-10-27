package com.cloud.Class.Management.System.Service;


import com.cloud.Class.Management.System.Entity.Course;
import com.cloud.Class.Management.System.Repository.CourseRepository;
import com.cloud.Class.Management.System.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public String createCourse(Course course) throws ExecutionException, InterruptedException {
        // Validate department exists
        if (departmentRepository.getDepartmentById(course.getDepartmentId()) == null) {
            throw new IllegalArgumentException("Department does not exist!");
        }
        return courseRepository.saveCourse(course);
    }

    public Course getCourse(String courseId) throws ExecutionException, InterruptedException {
        return courseRepository.getCourseById(courseId);
    }

    public List<Course> getAllCourses() throws ExecutionException, InterruptedException {
        return courseRepository.getAllCourses();
    }

    public String updateCourse(Course course) throws ExecutionException, InterruptedException {
        // Validate department exists
        if (departmentRepository.getDepartmentById(course.getDepartmentId()) == null) {
            throw new IllegalArgumentException("Department does not exist!");
        }
        return courseRepository.updateCourse(course);
    }

    public String deleteCourse(String courseId) throws ExecutionException, InterruptedException {
        return courseRepository.deleteCourse(courseId);
    }

    // Optional: Get all courses by department
    public List<Course> getCoursesByDepartment(String departmentId) throws ExecutionException, InterruptedException {
        return courseRepository.getAllCourses().stream()
                .filter(c -> c.getDepartmentId().equals(departmentId))
                .toList();
    }
}
