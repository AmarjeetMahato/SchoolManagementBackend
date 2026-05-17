package com.schoolManagementDB.domain.Students.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.schoolManagementDB.domain.Address.dtos.AddressUpdateDto;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentUpdateDto {

    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @Pattern(
            regexp = "MALE|FEMALE|OTHER",
            message = "Gender must be MALE, FEMALE or OTHER"
    )
    private String gender;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfBirth;

    @Positive(message = "Roll number must be greater than 0")
    private Integer rollNumber;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime admissionDate;

    private Boolean isActive;

    // Address Update
    private AddressUpdateDto address;

    // Relations
    private String classId;

    private String sectionId;

    private String parentId;
}