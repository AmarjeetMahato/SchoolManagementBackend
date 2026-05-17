package com.schoolManagementDB.domain.Attendance.services;



import com.schoolManagementDB.domain.Attendance.dtos.AttendanceDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceResponseDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceUpdateDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface IAttendanceService {

    // =========================
    // CREATE
    // =========================
    AttendanceResponseDto createAttendance(AttendanceDto dto);

    // =========================
    // UPDATE (PATCH STYLE)
    // =========================
    AttendanceResponseDto updateAttendance(String attendanceId, AttendanceUpdateDto dto);

    // =========================
    // GET BY ID
    // =========================
    AttendanceResponseDto getAttendanceById(String attendanceId);

    // =========================
    // DELETE
    // =========================
    void deleteAttendance(String attendanceId);

    // =========================
    // GET ALL
    // =========================
    List<AttendanceResponseDto> getAllAttendance();

    // =========================
    // FILTERS (REAL-WORLD USEFUL)
    // =========================

    // By Student
    List<AttendanceResponseDto> getAttendanceByStudentId(String studentId);

    // By Section
    List<AttendanceResponseDto> getAttendanceBySectionId(String sectionId);

    // By Subject
    List<AttendanceResponseDto> getAttendanceBySubjectId(String subjectId);

    // By Date
    List<AttendanceResponseDto> getAttendanceByDate(LocalDate date);

    // Date Range (very useful for reports)
    List<AttendanceResponseDto> getAttendanceBetweenDates(LocalDate startDate, LocalDate endDate);

    // =========================
    // STATUS OPERATIONS
    // =========================

    // Update only status (quick action)
    AttendanceResponseDto updateAttendanceStatus(String attendanceId, String status);

    // =========================
    // SEARCH
    // =========================

    List<AttendanceResponseDto> searchAttendanceByStudentName(String keyword, Pageable pageable);
}