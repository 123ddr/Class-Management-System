package com.cloud.Class.Management.System.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Long id;
    private Long departmentId;
    private String code;
    private String title; // aka name
    private Integer credits;
}

