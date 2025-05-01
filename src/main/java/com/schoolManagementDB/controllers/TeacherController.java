package com.schoolManagementDB.controllers;

import com.schoolManagementDB.dtos.TeacherDto;
import com.schoolManagementDB.entities.Teacher;
import com.schoolManagementDB.services.TeacherService.TeacherService;
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
public class TeacherController {

    private final TeacherService teacherService;

    // Create a teacher
    @PostMapping("/create")
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid TeacherDto teacherDto) {
        Teacher createdTeacher = teacherService.createTeacher(teacherDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    // Get all teachers
    @GetMapping("/all")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Get teacher by ID
    @GetMapping("/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String teacherId) {
        Teacher teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    // Update teacher details
    @PutMapping("/update/{teacherId}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable String teacherId,
            @RequestBody @Valid TeacherDto teacherDto) {
        Teacher updatedTeacher = teacherService.updateTeacher(teacherId, teacherDto);
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
    public ResponseEntity<Teacher> getTeacherByEmail(@PathVariable String email) {
        Teacher teacher = teacherService.getTeacherByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    // Get teacher by phone number
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Teacher> getTeacherByPhone(@PathVariable String phone) {
        Teacher teacher = teacherService.getTeacherByPhone(phone);
        return ResponseEntity.status(HttpStatus.OK).body(teacher);
    }

    // Get teachers by status (active/inactive)
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TeacherDto>> getTeachersByStatus(@PathVariable String status) {
        List<TeacherDto> teachers = teacherService.getTeachersByStatus(status);
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Search teachers by name (first, middle, or last name)
    @GetMapping("/search/{nameKeyword}")
    public ResponseEntity<List<TeacherDto>> searchTeachersByName(@PathVariable String nameKeyword) {
        List<TeacherDto> teachers = teacherService.searchTeachersByName(nameKeyword);
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Get teachers hired after a specific date
    @GetMapping("/hired-after/{date}")
    public ResponseEntity<List<TeacherDto>> getTeachersHiredAfter(@PathVariable String date) {
        List<TeacherDto> teachers = teacherService.getTeachersHiredAfter(LocalDate.parse(date));
        return ResponseEntity.status(HttpStatus.OK).body(teachers);
    }

    // Update teacher status (active/inactive)
    @PatchMapping("/status/update/{teacherId}")
    public ResponseEntity<Teacher> updateTeacherStatus(
            @PathVariable String teacherId,
            @RequestParam String status) {
        Teacher updatedTeacher = teacherService.updateTeacherStatus(teacherId, status);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTeacher);
    }

}
