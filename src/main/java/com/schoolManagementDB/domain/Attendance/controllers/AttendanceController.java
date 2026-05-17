package com.schoolManagementDB.domain.Attendance.controllers;


import com.schoolManagementDB.domain.Attendance.dtos.AttendanceDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceResponseDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceUpdateDto;
import com.schoolManagementDB.domain.Attendance.services.IAttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final IAttendanceService attendanceService;

    // =========================
    // CREATE ATTENDANCE
    // =========================
    @PostMapping("/create")
    public ResponseEntity<AttendanceResponseDto> createAttendance(@Valid @RequestBody AttendanceDto dto) {
        AttendanceResponseDto attendanceResponseDto = attendanceService.createAttendance(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(attendanceResponseDto);
    }

    // =========================
    // UPDATE ATTENDANCE (PATCH)
    // =========================
    @PutMapping("/{attendanceId}")
    public ResponseEntity<AttendanceResponseDto>  updateAttendance(
            @PathVariable String attendanceId,
            @RequestBody AttendanceUpdateDto dto) {
        AttendanceResponseDto attendanceResponse = attendanceService.updateAttendance(attendanceId,dto);
        return ResponseEntity.status(HttpStatus.OK).body(attendanceResponse);
    }

    // =========================
    // GET BY ID
    // =========================
    @GetMapping("/{attendanceId}")
    public ResponseEntity<AttendanceResponseDto>  getById(@PathVariable String attendanceId) {
        AttendanceResponseDto attendanceResponse = attendanceService.getAttendanceById(attendanceId);
        return ResponseEntity.status(HttpStatus.OK).body(attendanceResponse);
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{attendanceId}")
    public void delete(@PathVariable String attendanceId) {

        attendanceService.deleteAttendance(attendanceId);
         ResponseEntity.status(HttpStatus.NO_CONTENT).body("Attendance is deleted");
    }

    // =========================
    // GET ALL (PAGINATION OPTIONAL)
    // =========================
    @GetMapping
    public ResponseEntity<List<AttendanceResponseDto>>  getAll() {
       List<AttendanceResponseDto> responseDto =  attendanceService.getAllAttendance();
       return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // =========================
    // FILTER BY STUDENT
    // =========================
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDto>> getByStudent(@PathVariable String studentId) {
        List<AttendanceResponseDto> attendance = attendanceService.getAttendanceByStudentId(studentId);
        return ResponseEntity.status(HttpStatus.OK).body(attendance);
    }

    // =========================
    // FILTER BY SECTION
    // =========================
    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<AttendanceResponseDto>> getBySection(@PathVariable String sectionId) {
        List<AttendanceResponseDto> responseDto = attendanceService.getAttendanceBySectionId(sectionId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // =========================
    // FILTER BY SUBJECT
    // =========================
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<AttendanceResponseDto>> getBySubject(@PathVariable String subjectId) {
        List<AttendanceResponseDto> response = attendanceService.getAttendanceBySubjectId(subjectId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // =========================
    // FILTER BY DATE
    // =========================
    @GetMapping("/date")
    public ResponseEntity<List<AttendanceResponseDto>> getByDate(@RequestParam LocalDate date) {
        List<AttendanceResponseDto> responseDtos  =attendanceService.getAttendanceByDate(date);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

    // =========================
    // FILTER BY DATE RANGE
    // =========================
    @GetMapping("/range")
    public ResponseEntity<List<AttendanceResponseDto>>  getByDateRange(@RequestParam LocalDate startDate,
                                                                 @RequestParam LocalDate endDate) {

        List<AttendanceResponseDto> response = attendanceService.getAttendanceBetweenDates(startDate, endDate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // =========================
    // UPDATE STATUS ONLY
    // =========================
    @PatchMapping("/{attendanceId}/status")
    public ResponseEntity<AttendanceResponseDto> updateStatus(
            @PathVariable String attendanceId,
            @RequestParam String status) {

        AttendanceResponseDto response = attendanceService.updateAttendanceStatus(attendanceId,status);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // =========================
    // SEARCH BY STUDENT NAME (PAGINATION READY)
    // =========================
//    @GetMapping("/search")
//    public Page<AttendanceResponseDto> searchByStudentName(
//            @RequestParam String keyword,
//            Pageable pageable) {
//
//        return attendanceService.searchAttendanceByStudentName(keyword, pageable);
//    }
}
