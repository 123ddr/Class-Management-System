package com.cloud.Class.Management.System.Service;

import com.cloud.Class.Management.System.Dto.CourseSummaryDto;
import com.cloud.Class.Management.System.Dto.TeacherSummaryDto;
import com.cloud.Class.Management.System.Repository.TeacherCourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherCourseService {

    private final TeacherCourseRepository teacherCourseRepository;

    public TeacherCourseService(TeacherCourseRepository teacherCourseRepository) {
        this.teacherCourseRepository = teacherCourseRepository;
    }

    @Transactional(readOnly = true)
    public List<TeacherSummaryDto> getTeachersByCourse(Long courseId) {
        return teacherCourseRepository.findTeachersByCourseId(courseId);
    }

    @Transactional(readOnly = true)
    public List<CourseSummaryDto> getCoursesByTeacher(Long teacherId) {
        return teacherCourseRepository.findCoursesByTeacherId(teacherId);
    }
}
