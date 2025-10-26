package com.cloud.Class.Management.System.Repository;

import com.cloud.Class.Management.System.Dto.CourseSummaryDto;
import com.cloud.Class.Management.System.Dto.TeacherSummaryDto;
import com.cloud.Class.Management.System.Entity.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, String> {

    @Query("""
           select new com.cloud.Class.Management.System.Dto.TeacherSummaryDto(
               t.id, t.firstName, t.lastName, t.email
           )
           from TeacherCourse tc
             join tc.teacher t
           where tc.course.id = :courseId
           order by t.lastName asc, t.firstName asc
           """)
    List<TeacherSummaryDto> findTeachersByCourseId(@Param("courseId") Long courseId);

    @Query("""
           select new com.cloud.Class.Management.System.Dto.CourseSummaryDto(
               c.id, c.code, c.title
           )
           from TeacherCourse tc
             join tc.course c
           where tc.teacher.id = :teacherId
           order by c.code asc
           """)
    List<CourseSummaryDto> findCoursesByTeacherId(@Param("teacherId") Long teacherId);
}
