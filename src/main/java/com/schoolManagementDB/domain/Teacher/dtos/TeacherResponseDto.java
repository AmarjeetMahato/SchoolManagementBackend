package com.schoolManagementDB.domain.Teacher.dtos;

import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponseDto {

    private String teacherId;

    // Full Name
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;

    // Contact
    private String email;
    private String phone;

    // Personal
    private LocalDate dateOfBirth;
    private String gender;
    private String qualification;

    // Profile
    private String profilePic;

    // Employment
    private LocalDate hireDate;
    private String status;

    // Address
    private AddressResponseDto address;

    // Classes
    private List<String> classIds;
    private int totalClasses;

    // Assigned Subjects
    private int totalAssignedSubjects;

    // Exam Duties
    private int totalExamDuties;

    // Audit
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}