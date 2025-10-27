package com.cloud.Class.Management.System.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassRoom {
    private String classroomId;
    private String roomCode;
    private String building;
    private String capacity;
    private String features;
}

