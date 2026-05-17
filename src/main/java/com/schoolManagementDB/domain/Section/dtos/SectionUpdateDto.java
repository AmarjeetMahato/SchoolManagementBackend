package com.schoolManagementDB.domain.Section.dtos;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionUpdateDto {

    @Size(min = 2, max = 100, message = "Section name must be between 2 and 100 characters")
    private String name;

    @Size(max = 50, message = "Shift must not exceed 50 characters")
    private String shift;

    @Size(max = 50, message = "Section type must not exceed 50 characters")
    private String classes;

    @Size(max = 20, message = "Room number must not exceed 20 characters")
    private String roomNumber;

    @Pattern(regexp = "ACTIVE|INACTIVE", message = "Status must be ACTIVE or INACTIVE")
    private String status;

    @Pattern(regexp = "^[a-zA-Z0-9\\-]+$", message = "Class ID format is invalid")
    private String classId;
}