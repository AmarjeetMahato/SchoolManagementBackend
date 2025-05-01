package com.schoolManagementDB.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherSubjectSectionDto {



    @NotBlank(message = "Class name must not be blank")
    private String classes;

    // Optional during creation, can be auto-assigned in service
    private LocalDateTime assignedAt;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    @NotBlank(message = "Teacher ID must not be blank")
    private String teacherId;

    @NotBlank(message = "Subject ID must not be blank")
    private String subjectId;

    @NotBlank(message = "Section ID must not be blank")
    private String sectionId;
}
