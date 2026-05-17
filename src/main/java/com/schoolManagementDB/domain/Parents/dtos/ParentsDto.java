package com.schoolManagementDB.domain.Parents.dtos;

import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentsDto {

    @NotBlank(message = "Father first name is required")
    @Size(min = 2, max = 100, message = "Father first name must be between 2 and 100 characters")
    private String fatherFirstname;

    @Size(max = 100, message = "Father middle name must not exceed 100 characters")
    private String fatherMiddleName;

    @NotBlank(message = "Father last name is required")
    @Size(min = 2, max = 100, message = "Father last name must be between 2 and 100 characters")
    private String fatherLastname;

    @Email(message = "Invalid father email format")
    private String fatherEmail;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Guardian phone 1 must be exactly 10 digits"
    )
    private String guardianPhone1;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Guardian phone 2 must be exactly 10 digits"
    )
    private String guardianPhone2;

    @NotBlank(message = "Mother first name is required")
    @Size(min = 2, max = 100, message = "Mother first name must be between 2 and 100 characters")
    private String motherFirstname;

    @Size(max = 100, message = "Mother middle name must not exceed 100 characters")
    private String motherMiddleName;

    @NotBlank(message = "Mother last name is required")
    @Size(min = 2, max = 100, message = "Mother last name must be between 2 and 100 characters")
    private String motherLastname;

    @Size(max = 100, message = "Father occupation must not exceed 100 characters")
    private String fatherOccupation;

    @Size(max = 100, message = "Mother occupation must not exceed 100 characters")
    private String motherOccupation;

    @Min(value = 0, message = "Children count cannot be negative")
    private int children;

    @NotNull(message = "Address is required")
    private AddressDto address;
}