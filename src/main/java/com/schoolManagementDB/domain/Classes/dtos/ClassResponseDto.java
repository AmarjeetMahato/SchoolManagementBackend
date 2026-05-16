package com.schoolManagementDB.domain.Classes.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassResponseDto {
    private String classId;
    private String name;
    private String code;
    private String description;
    private String status;

    // Return the teacher's ID and name instead of the whole entity object
    private String teacherId;
    private String teacherName;

    // Return lists of IDs or simple names for the related entities
    private List<String> sectionIds;
    private List<String> studentIds;
    private List<String> subjectIds;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
