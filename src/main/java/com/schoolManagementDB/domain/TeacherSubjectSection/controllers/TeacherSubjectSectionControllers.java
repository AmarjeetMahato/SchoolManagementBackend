package com.schoolManagementDB.domain.TeacherSubjectSection.controllers;


import com.schoolManagementDB.dtos.TeacherSubjectSectionDto;
import com.schoolManagementDB.entities.Teacher_Subject_Section;
import com.schoolManagementDB.services.TeacherSubjectSection.TeacherSubjectSection;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher-Subject-Section")
@AllArgsConstructor
public class TeacherSubjectSectionControllers {

    private final TeacherSubjectSection teacherSubjectSection;

    // Create a new TeacherSubjectSection
    @PostMapping("/create")
    public ResponseEntity<Teacher_Subject_Section> createAssignment(@RequestBody TeacherSubjectSectionDto dto) {
        Teacher_Subject_Section createdAssignment = teacherSubjectSection.createAssignment(dto);
        return new ResponseEntity<>(createdAssignment, HttpStatus.CREATED);
    }

    // Get TeacherSubjectSection by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher_Subject_Section> getAssignmentById(@PathVariable String id) {
        Teacher_Subject_Section assignment = teacherSubjectSection.getAssignmentById(id);
        return new ResponseEntity<>(assignment, HttpStatus.OK);
    }

    // Get all TeacherSubjectSections
    @GetMapping("/get-all-teacher-subject-section")
    public ResponseEntity<List<Teacher_Subject_Section>> getAllAssignments() {
        List<Teacher_Subject_Section> assignments = teacherSubjectSection.getAllAssignments();
        return new ResponseEntity<>(assignments, HttpStatus.OK);
    }

    // Update an existing TeacherSubjectSection
    @PutMapping("/{id}")
    public ResponseEntity<Teacher_Subject_Section> updateAssignment(@PathVariable String id, @RequestBody TeacherSubjectSectionDto dto) {
        Teacher_Subject_Section updatedAssignment = teacherSubjectSection.updateAssignment(id, dto);
        return new ResponseEntity<>(updatedAssignment, HttpStatus.OK);
    }

    // Delete a TeacherSubjectSection by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable String id) {
        teacherSubjectSection.deleteAssignment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
