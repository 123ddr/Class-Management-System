package com.cloud.Class.Management.System.DTO;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDTO {
    private String userId;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
