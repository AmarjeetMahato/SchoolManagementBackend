package com.schoolManagementDB.domain.Students.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolManagementDB.dtos.AddressDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class StudentsDtos {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Date of birth is required")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  // Add this to ensure the correct format
    private LocalDateTime dateOfBirth;

    @NotNull(message = "Roll number is required")
    private int rollNumber;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")  // Add this to ensure the correct format
    @NotNull(message = "Admission date is required")
    private LocalDateTime admissionDate;

    @NotNull(message = "Student active status is required")
    private Boolean isActive;


    private AddressDto address;

    private String classId;  // List of Class IDs (instead of Classes entity)

    private String sectionId;  // Section ID (instead of Section entity)
}
