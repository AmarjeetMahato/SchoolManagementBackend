package com.schoolManagementDB.domain.Classes.controller;


import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassResponseDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassUpdateDto;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Classes.services.IClassesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassesControllers {

    private final IClassesService  classService;

    @PostMapping("/create")
    public ResponseEntity<ClassResponseDto> createClass(@RequestBody ClassDto dto) {
        ClassResponseDto createdClass = classService.createClass(dto);
        return ResponseEntity.ok(createdClass);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<ClassResponseDto> updateClass(
            @PathVariable String classId,
            @RequestBody ClassUpdateDto dto) {
        ClassResponseDto updated = classService.updateClass(classId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<String> deleteClass(@PathVariable String classId) {
        classService.deleteClass(classId);
        return ResponseEntity.ok("Class deleted successfully!");
    }

    @GetMapping("/{classId}")
    public ResponseEntity<ClassResponseDto> getClassById(@PathVariable String classId) {
        ClassResponseDto found = classService.getClassById(classId);
        return ResponseEntity.ok(found);
    }

    @GetMapping("/get-all-classes")
    public ResponseEntity<List<ClassResponseDto>> getAllClasses() {
        List<ClassResponseDto> list = classService.getAllClasses();
        return ResponseEntity.ok(list);
    }
}
