package com.schoolManagementDB.domain.Students.dtos;

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
public class StudentResponseDto {

    private String studentId;

    private String firstName;
    private String middleName;
    private String lastName;

    private String fullName;

    private String gender;

    private LocalDateTime dateOfBirth;

    private int rollNumber;

    private LocalDateTime admissionDate;

    private boolean isActive;

    // Address Details
    private String addressId;

    // Class Details
    private String classId;
    private String className;

    // Section Details
    private String sectionId;
    private String sectionName;

    // Parent Details
    private String parentId;
    private String parentName;

    // Attendance IDs
    private List<String> attendanceIds;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}