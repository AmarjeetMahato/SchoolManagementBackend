package com.schoolManagementDB.domain.Teacher.controllers;


import com.schoolManagementDB.domain.Teacher.dtos.TeacherDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherResponseDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherUpdateDto;
import com.schoolManagementDB.domain.Teacher.services.ITeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
@AllArgsConstructor
public class TeacherControllers {


    private final ITeacherService  teacherService;

    // Create a teacher
    @PostMapping("/create")
    public ResponseEntity<TeacherResponseDto> createTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        TeacherResponseDto createdTeacher = teacherService.createTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    // Get all teachers
    @GetMapping("/all")
    public ResponseEntity<List<TeacherResponseDto>> getAllTeachers() {
        List<TeacherResponseDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Get teacher by ID
    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherResponseDto> getTeacherById(@PathVariable String teacherId) {
        TeacherResponseDto teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    // Update teacher details
    @PutMapping("/update/{teacherId}")
    public ResponseEntity<TeacherResponseDto> updateTeacher(
            @PathVariable String teacherId,
            @RequestBody @Valid TeacherUpdateDto teacherDto) {
        TeacherResponseDto updatedTeacher = teacherService.updateTeacher(teacherId, teacherDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
    }

    // Delete teacher
    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String teacherId) {
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Get teacher by email
    @GetMapping("/email/{email}")
    public ResponseEntity<TeacherResponseDto> getTeacherByEmail(@PathVariable String email) {
        TeacherResponseDto teacher = teacherService.getTeacherByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    // Get teacher by phone number
    @GetMapping("/phone/{phone}")
    public ResponseEntity<TeacherResponseDto> getTeacherByPhone(@PathVariable String phone) {
        TeacherResponseDto teacher = teacherService.getTeacherByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    // Get teachers by status (active/inactive)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TeacherResponseDto>> getTeachersByStatus(@PathVariable String status) {
        List<TeacherResponseDto> teachers = teacherService.getTeachersByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Search teachers by name (first, middle, or last name)
    @GetMapping("/search/{nameKeyword}")
    public ResponseEntity<List<TeacherResponseDto>> searchTeachersByName(@PathVariable String nameKeyword) {
        List<TeacherResponseDto> teachers = teacherService.searchTeachersByName(nameKeyword);
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Get teachers hired after a specific date
    @GetMapping("/hired-after/{date}")
    public ResponseEntity<List<TeacherResponseDto>> getTeachersHiredAfter(@PathVariable String date) {
        List<TeacherResponseDto> teachers = teacherService.getTeachersHiredAfter(LocalDate.parse(date));
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Update teacher status (active/inactive)
    @PatchMapping("/status/update/{teacherId}")
    public ResponseEntity<TeacherResponseDto> updateTeacherStatus(
            @PathVariable String teacherId,
            @RequestParam String status
    ) {

        TeacherResponseDto updatedTeacher = teacherService.updateTeacherStatus(teacherId, status);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
    }
}
