package com.schoolManagementDB.domain.Students.controllers;
import com.schoolManagementDB.domain.Students.dtos.StudentResponseDto;
import com.schoolManagementDB.domain.Students.dtos.StudentUpdateDto;
import com.schoolManagementDB.domain.Students.dtos.StudentsDtos;
import com.schoolManagementDB.domain.Students.services.IStudentsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentsControllers {


    private  final IStudentsService studentService;


    @PostMapping("/create")
    public ResponseEntity<StudentResponseDto> createStudents(@RequestBody @Valid StudentsDtos studentDto){

        StudentResponseDto createStudents = this.studentService.createStudent(studentDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createStudents);
    }

    // Get student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable String studentId) {
        StudentResponseDto student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    // Get all students
    @GetMapping("/all")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get student by roll number
    @GetMapping("/rollno/{rollNo}")
    public ResponseEntity<StudentResponseDto> getStudentByRollNo(@PathVariable int rollNo) {
        StudentResponseDto student = studentService.getStudentByRollNo(rollNo);
        return ResponseEntity.ok(student);
    }

    // Update student
    @PutMapping("/update/{studentId}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable String studentId,
            @RequestBody @Valid StudentUpdateDto  studentDto
    ) {
        StudentResponseDto updatedStudent = studentService.updateStudent(studentId, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete student
    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.noContent().build();
    }

    // Get students by class ID
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByClassId(@PathVariable String classId) {
        List<StudentResponseDto> students = studentService.getStudentsByClassId(classId);
        return ResponseEntity.ok(students);
    }

    // Get students by section ID
    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsBySectionId(@PathVariable String sectionId) {
        List<StudentResponseDto> students = studentService.getStudentsBySectionId(sectionId);
        return ResponseEntity.ok(students);
    }

    // Get students by parent ID
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByParentId(@PathVariable String parentId) {
        List<StudentResponseDto> students = studentService.getStudentsByParentId(parentId);
        return ResponseEntity.ok(students);
    }

    // Update student active/inactive status
    @PatchMapping("/status/{studentId}")
    public ResponseEntity<StudentResponseDto> updateStudentStatus(
            @PathVariable String studentId,
            @RequestParam boolean isActive
    ) {
        StudentResponseDto updated = studentService.updateStudentStatus(studentId, isActive);
        return ResponseEntity.ok(updated);
    }

    // Search students by name
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> searchStudentsByName(@RequestParam String name) {
        List<StudentResponseDto> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    // Get students admitted after a date
    @GetMapping("/admitted-after")
    public ResponseEntity<List<StudentResponseDto>> getStudentsAdmittedAfter(@RequestParam String date) {
        LocalDateTime admissionDate = LocalDateTime.parse(date);
        List<StudentResponseDto> students = studentService.getStudentsAdmittedAfter(admissionDate);
        return ResponseEntity.ok(students);
    }
}
