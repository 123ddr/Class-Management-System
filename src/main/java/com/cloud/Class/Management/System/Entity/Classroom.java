package com.cloud.Class.Management.System.Entity;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Classroom {
    private String classroom_id;
    private String room_code;
    private String building;
    private String capacity;
    private String features;
}

