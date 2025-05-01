package com.schoolManagementDB.dtos;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDto {

    @NotBlank(message = "First name is required")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits")
    private String phone;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    private String profilePic;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotEmpty(message = "At least one class must be assigned to the teacher")
    @Size(min = 1, message = "At least one class must be assigned to the teacher")
    private List<String> classIds;

    // 💥 NEW field: AddressDto inside TeacherDto
    @NotNull(message = "Address is required for teacher")
    private AddressDto address;


}
