package com.schoolManagementDB.controllers;


import com.schoolManagementDB.dtos.SubjectDto;
import com.schoolManagementDB.entities.Subject;
import com.schoolManagementDB.services.SubjectService.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
public class SubjectController {

    private  final SubjectService subjectService;


    @PostMapping("/create")
    public ResponseEntity<Subject> createSubject(@RequestBody SubjectDto subjectDto) {
        Subject createdSubject = subjectService.createSubject(subjectDto);
        return ResponseEntity.ok(createdSubject);
    }

    @PutMapping("/update/{subjectId}")
    public ResponseEntity<Subject> updateSubject(
            @PathVariable String subjectId,
            @RequestBody SubjectDto subjectDto) {
        Subject updatedSubject = subjectService.updateSubject(subjectId, subjectDto);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getSubject/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable String subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @GetMapping("get-all-subject")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> allSubjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(allSubjects);
    }
}
