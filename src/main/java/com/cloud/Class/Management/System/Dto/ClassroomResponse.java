package com.cloud.Class.Management.System.Dto;

public record ClassroomResponse(
        Long id,
        String name,
        Long buildingId,
        String buildingNumber,
        Integer capacity
) {}
