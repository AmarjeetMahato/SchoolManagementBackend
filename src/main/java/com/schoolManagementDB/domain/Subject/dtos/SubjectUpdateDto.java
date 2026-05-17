package com.schoolManagementDB.domain.Subject.dtos;

import jakarta.validation.constraints.Pattern;
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
public class SubjectUpdateDto {

    @Size(min = 2, max = 100, message = "Subject name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 20, message = "Subject code must be between 2 and 20 characters")
    private String code;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Pattern(
            regexp = "^[a-zA-Z0-9\\-]+$",
            message = "Invalid class ID format"
    )
    private String classId;

    @Pattern(
            regexp = "ACTIVE|INACTIVE",
            message = "Status must be ACTIVE or INACTIVE"
    )
    private String status;
}