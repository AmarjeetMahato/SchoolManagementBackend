package com.schoolManagementDB.domain.Classes.controller;


import com.schoolManagementDB.dtos.ClassDto;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.services.ClassService.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classes")
@RequiredArgsConstructor
public class ClassesControllers {

    private final ClassService classService;

    @PostMapping("/create")
    public ResponseEntity<Classes> createClass(@RequestBody ClassDto dto) {
        Classes createdClass = classService.createClass(dto);
        return ResponseEntity.ok(createdClass);
    }

    @PutMapping("/{classId}")
    public ResponseEntity<Classes> updateClass(
            @PathVariable String classId,
            @RequestBody ClassDto dto) {
        Classes updated = classService.updateClass(classId, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{classId}")
    public ResponseEntity<String> deleteClass(@PathVariable String classId) {
        classService.deleteClass(classId);
        return ResponseEntity.ok("Class deleted successfully!");
    }

    @GetMapping("/{classId}")
    public ResponseEntity<Classes> getClassById(@PathVariable String classId) {
        Classes found = classService.getClassById(classId);
        return ResponseEntity.ok(found);
    }

    @GetMapping("/get-all-classes")
    public ResponseEntity<List<Classes>> getAllClasses() {
        List<Classes> list = classService.getAllClasses();
        return ResponseEntity.ok(list);
    }
}
