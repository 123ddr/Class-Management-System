package com.cloud.Class.Management.System.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomCreateRequest(
        @NotBlank String name,
        @NotNull Long buildingId,
        @NotBlank String buildingNumber,
        @Min(1) Integer capacity
) {}
