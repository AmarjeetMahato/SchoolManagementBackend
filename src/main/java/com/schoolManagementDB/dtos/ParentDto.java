package com.schoolManagementDB.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentDto {

    // Father's Info
    @NotBlank(message = "Father's first name is required")
    private String fatherFirstname;

    private String fatherMiddleName;

    @NotBlank(message = "Father's last name is required")
    private String fatherLastname;

    @Email(message = "Email must be valid")
    private String fatherEmail;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String guardianPhone1;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String guardianPhone2;

    // Mother's Info
    @NotBlank(message = "Mother's first name is required")
    private String motherFirstname;

    private String motherMiddleName;

    @NotBlank(message = "Mother's last name is required")
    private String motherLastname;

    private String fatherOccupation;

    private String motherOccupation;

    private Integer children;

}
