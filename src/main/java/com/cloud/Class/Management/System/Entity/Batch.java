package com.cloud.Class.Management.System.Entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public class Batch {
    private String batchId;

    @NotBlank(message = "Course ID is required")
    private String courseId;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Term is required")
    private String term;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Batch() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTerm() { return term; }
    public void setTerm(String term) { this.term = term; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}