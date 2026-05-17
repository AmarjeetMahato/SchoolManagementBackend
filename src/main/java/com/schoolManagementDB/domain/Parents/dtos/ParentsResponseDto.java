package com.schoolManagementDB.domain.Parents.dtos;

import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentsResponseDto {

    private String parentId;

    // Father Details
    private String fatherFirstname;
    private String fatherMiddleName;
    private String fatherLastname;
    private String fatherFullName;

    private String fatherEmail;

    // Guardian Contact
    private String guardianPhone1;
    private String guardianPhone2;

    // Mother Details
    private String motherFirstname;
    private String motherMiddleName;
    private String motherLastname;
    private String motherFullName;

    // Occupation
    private String fatherOccupation;
    private String motherOccupation;

    // Children
    private int children;

    // Address
    private AddressResponseDto address;

    // Student Details
    private List<String> studentIds;

    private int totalStudents;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}