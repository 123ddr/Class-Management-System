package com.cloud.Class.Management.System.Dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClassroomUpdateRequest(
        @Min(1) Integer capacity,
        @NotNull Long buildingId,
        @NotBlank String buildingNumber
) {}
