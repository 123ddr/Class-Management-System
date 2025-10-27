package com.cloud.Class.Management.System.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchRosterEmail {
    private String rosterId;
    private String batchId;
    private String email;
    private String fullName;
}

