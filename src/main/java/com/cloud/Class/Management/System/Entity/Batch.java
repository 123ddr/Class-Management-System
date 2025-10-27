package com.cloud.Class.Management.System.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    private String batchId;
    private String courseId;
    private String batchName;
    private String term;
    private int capacity;
}

