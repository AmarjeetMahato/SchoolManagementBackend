package com.schoolManagementDB.domain.Subject.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectResponseDto {

    private String subjectId;

    private String name;

    private String code;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String description;

    private String status;

    // Class Details
    private String classId;

    private String className;

    // Statistics
    private int totalTeacherAssignments;

    private int totalExamSchedules;

    private int totalAttendanceRecords;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
