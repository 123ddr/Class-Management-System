package com.cloud.Class.Management.System.Entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherCourse {
    private String teacher_course_id;
    private String teacher_id;
    private String course_id;
}

