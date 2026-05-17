package com.schoolManagementDB.domain.Section.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionResponseDto {

    private String sectionId;

    private String name;

    private String shift;

    private String classes;

    private String roomNumber;

    private String status;

    private String classId;
    private String className;

    private int totalStudents;
    private int totalTeachers;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}