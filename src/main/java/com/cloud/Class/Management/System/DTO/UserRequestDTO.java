package com.cloud.Class.Management.System.DTO;


import lombok.Data;

@Data
public class UserRequestDTO {
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String password; // optional if using Firebase Auth
    private boolean isActive;
}
