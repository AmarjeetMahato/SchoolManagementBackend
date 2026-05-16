package com.schoolManagementDB.domain.Section.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SectionDtos {

    @NotBlank(message = "Class name is required")
    @Size(max = 100, message = "Class name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Shift is required")
    @Size(max = 50, message = "Shift must not exceed 50 characters")
    private String shift;

    @NotBlank(message = "Class section or type is required")
    @Size(max = 50, message = "Class section/type must not exceed 50 characters")
    private String classes; // Represents class section/type (example: "A", "Science")

    @NotBlank(message = "Room number is required")
    @Size(max = 20, message = "Room number must not exceed 20 characters")
    private String roomNumber;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must not exceed 20 characters")
    private String status; // Example: ACTIVE, INACTIVE

    @NotBlank(message = "Class ID is required")
    private String classId; // Reference to the associated Classes entity
}
