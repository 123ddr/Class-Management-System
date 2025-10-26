package com.cloud.Class.Management.System.Controller;

import com.cloud.Class.Management.System.Service.TeacherCourseService;
import com.cloud.Class.Management.System.Dto.CourseSummaryDto;
import com.cloud.Class.Management.System.Dto.TeacherSummaryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher-courses")
public class TeacherCourseController {

    private final TeacherCourseService teacherCourseService;

    public TeacherCourseController(TeacherCourseService teacherCourseService) {
        this.teacherCourseService = teacherCourseService;
    }

    // View all teachers assigned to a specific course
    // GET /api/teacher-courses/courses/{courseId}/teachers
    @GetMapping("/courses/{courseId}/teachers")
    public ResponseEntity<List<TeacherSummaryDto>> getTeachersByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(teacherCourseService.getTeachersByCourse(courseId));
    }

    // View all courses assigned to a specific teacher
    // GET /api/teacher-courses/teachers/{teacherId}/courses
    @GetMapping("/teachers/{teacherId}/courses")
    public ResponseEntity<List<CourseSummaryDto>> getCoursesByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(teacherCourseService.getCoursesByTeacher(teacherId));
    }
}
