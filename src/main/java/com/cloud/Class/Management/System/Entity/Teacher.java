package com.cloud.Class.Management.System.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private Long id;
    private Long departmentId;
    private String staffNo;
    private String title;

    // Basic profile fields used by TeacherSummaryDto
    private String firstName;
    private String lastName;
    private String email;
}

