package com.cloud.Class.Management.System.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "classrooms",
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_classroom_name_building", columnNames = {"name", "building_id"})
       })
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Optional label for the room (e.g., "A-201")
    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    // Foreign key reference to Building (per your ERD). Use Long if it’s an ID.
    @NotNull
    @Column(name = "building_id", nullable = false)
    private Long buildingId;

    // Human-facing building number/code (e.g., "B1"). Superadmin wants to view by this.
    @NotBlank
    @Column(name = "building_number", nullable = false, length = 50)
    private String buildingNumber;

    @Min(1)
    @Column(nullable = false)
    private Integer capacity;

    // getters & setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Long getBuildingId() { return buildingId; }
    public void setBuildingId(Long buildingId) { this.buildingId = buildingId; }

    public String getBuildingNumber() { return buildingNumber; }
    public void setBuildingNumber(String buildingNumber) { this.buildingNumber = buildingNumber; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}
