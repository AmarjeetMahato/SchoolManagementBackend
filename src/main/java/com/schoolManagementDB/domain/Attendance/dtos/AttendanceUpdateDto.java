package com.schoolManagementDB.domain.Attendance.dtos;


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
public class AttendanceUpdateDto {

    private LocalDate attendanceDate;

    @Size(min = 2, max = 20, message = "Status must be between 2 and 20 characters (PRESENT/ABSENT/LATE)")
    private String status;

    @Size(min = 2, max = 50, message = "MarkedBy (Admin ID) must be between 2 and 50 characters")
    private String markedBy;

    @Size(max = 500, message = "Remarks must not exceed 500 characters")
    private String remarks;

    @Size(min = 2, max = 50, message = "Student ID must be between 2 and 50 characters")
    private String studentId;

    @Size(min = 2, max = 50, message = "Section ID must be between 2 and 50 characters")
    private String sectionId;

    @Size(min = 2, max = 50, message = "Subject ID must be between 2 and 50 characters")
    private String subjectId;
}