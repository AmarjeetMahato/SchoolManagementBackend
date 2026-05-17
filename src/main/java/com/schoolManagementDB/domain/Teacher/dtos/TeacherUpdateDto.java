package com.schoolManagementDB.domain.Teacher.dtos;

import com.schoolManagementDB.domain.Address.dtos.AddressUpdateDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherUpdateDto {

    @Size(min = 2, max = 100, message = "First name must be between 2 and 100 characters")
    private String firstName;

    @Size(max = 100, message = "Middle name must not exceed 100 characters")
    private String middleName;

    @Size(min = 2, max = 100, message = "Last name must be between 2 and 100 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits"
    )
    private String phone;

    private LocalDate dateOfBirth;

    @Pattern(
            regexp = "MALE|FEMALE|OTHER",
            message = "Gender must be MALE, FEMALE or OTHER"
    )
    private String gender;

    @Size(max = 200, message = "Qualification must not exceed 200 characters")
    private String qualification;

    private String profilePic;

    private LocalDate hireDate;

    @Pattern(
            regexp = "ACTIVE|INACTIVE",
            message = "Status must be ACTIVE or INACTIVE"
    )
    private String status;

    private AddressUpdateDto address;
}