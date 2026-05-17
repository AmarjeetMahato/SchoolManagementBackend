package com.schoolManagementDB.domain.Subject.controllers;

import com.schoolManagementDB.domain.Subject.dtos.SubjectDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectResponseDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectUpdateDto;
import com.schoolManagementDB.domain.Subject.services.ISubjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subject")
@RequiredArgsConstructor
public class SubjectControllers {


    private  final ISubjectServices subjectService;


    @PostMapping("/create")
    public ResponseEntity<SubjectResponseDto> createSubject(@RequestBody SubjectDto subjectDto) {
        SubjectResponseDto createdSubject = subjectService.createSubject(subjectDto);
        return ResponseEntity.ok(createdSubject);
    }

    @PutMapping("/update/{subjectId}")
    public ResponseEntity<SubjectResponseDto> updateSubject(
            @PathVariable String subjectId,
            @RequestBody SubjectUpdateDto subjectDto) {
        SubjectResponseDto updatedSubject = subjectService.updateSubject(subjectId, subjectDto);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/delete/{subjectId}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("getSubject/{subjectId}")
    public ResponseEntity<SubjectResponseDto> getSubjectById(@PathVariable String subjectId) {
        SubjectResponseDto subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok(subject);
    }

    @GetMapping("get-all-subject")
    public ResponseEntity<List<SubjectResponseDto>> getAllSubjects() {
        List<SubjectResponseDto> allSubjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(allSubjects);
    }
}
