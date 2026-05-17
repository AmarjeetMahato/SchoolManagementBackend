package com.schoolManagementDB.domain.Attendance.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponseDto {

    private String attendanceId;

    private LocalDate attendanceDate;

    private String status;

    private String markedBy;

    private String remarks;

    private String studentId;

    private String firstName;

    private  String lastName;

    private String sectionId;

    private String sectionName;

    private String subjectId;

    private String subjectName;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;

    // getters and setters
}