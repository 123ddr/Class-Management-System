package com.cloud.Class.Management.System.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassSchedule {
    private String scheduleId;
    private String batchId;
    private String courseId;
    private String teacherId;
    private String classroomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String recurrenceRule;
}

