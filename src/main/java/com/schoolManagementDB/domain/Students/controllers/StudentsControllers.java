package com.schoolManagementDB.domain.Students.controllers;
import com.schoolManagementDB.dtos.StudentDto;
import com.schoolManagementDB.entities.Students;
import com.schoolManagementDB.services.StudentService.StudentService;
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


    private  final StudentService studentService;


    @PostMapping("/create")
    public ResponseEntity<?> createStudents(@RequestBody @Valid StudentDto studentDto){

        Students createStudents = this.studentService.createStudent(studentDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createStudents);
    }

    // Get student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<Students> getStudent(@PathVariable String studentId) {
        Students student = studentService.getStudent(studentId);
        return ResponseEntity.ok(student);
    }

    // Get all students
    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Get student by roll number
    @GetMapping("/rollno/{rollNo}")
    public ResponseEntity<Students> getStudentByRollNo(@PathVariable int rollNo) {
        Students student = studentService.getStudentByRollNo(rollNo);
        return ResponseEntity.ok(student);
    }

    // Update student
    @PutMapping("/update/{studentId}")
    public ResponseEntity<Students> updateStudent(
            @PathVariable String studentId,
            @RequestBody @Valid StudentDto studentDto
    ) {
        Students updatedStudent = studentService.updateStudent(studentId, studentDto);
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
    public ResponseEntity<List<StudentDto>> getStudentsByClassId(@PathVariable String classId) {
        List<StudentDto> students = studentService.getStudentsByClassId(classId);
        return ResponseEntity.ok(students);
    }

    // Get students by section ID
    @GetMapping("/section/{sectionId}")
    public ResponseEntity<List<StudentDto>> getStudentsBySectionId(@PathVariable String sectionId) {
        List<StudentDto> students = studentService.getStudentsBySectionId(sectionId);
        return ResponseEntity.ok(students);
    }

    // Get students by parent ID
    @GetMapping("/parent/{parentId}")
    public ResponseEntity<List<StudentDto>> getStudentsByParentId(@PathVariable String parentId) {
        List<StudentDto> students = studentService.getStudentsByParentId(parentId);
        return ResponseEntity.ok(students);
    }

    // Update student active/inactive status
    @PatchMapping("/status/{studentId}")
    public ResponseEntity<Students> updateStudentStatus(
            @PathVariable String studentId,
            @RequestParam boolean isActive
    ) {
        Students updated = studentService.updateStudentStatus(studentId, isActive);
        return ResponseEntity.ok(updated);
    }

    // Search students by name
    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchStudentsByName(@RequestParam String name) {
        List<StudentDto> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    // Get students admitted after a date
    @GetMapping("/admitted-after")
    public ResponseEntity<List<StudentDto>> getStudentsAdmittedAfter(@RequestParam String date) {
        LocalDateTime admissionDate = LocalDateTime.parse(date);
        List<StudentDto> students = studentService.getStudentsAdmittedAfter(admissionDate);
        return ResponseEntity.ok(students);
    }
}
