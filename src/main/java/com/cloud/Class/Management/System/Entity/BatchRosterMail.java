package com.cloud.Class.Management.System.Entity;



import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


public class BatchRosterMail {
    private String rosterId;

    @NotBlank(message = "Batch ID is required")
    private String batchId;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Full name is required")
    private String fullName;

    // Constructors
    public BatchRosterMail() {}

    // Getters and Setters
    public String getRosterId() { return rosterId; }
    public void setRosterId(String rosterId) { this.rosterId = rosterId; }

    public String getBatchId() { return batchId; }
    public void setBatchId(String batchId) { this.batchId = batchId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
}