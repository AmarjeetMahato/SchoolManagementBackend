package com.schoolManagementDB.domain.Section.controllers;


import com.schoolManagementDB.domain.Section.dtos.SectionDto;
import com.schoolManagementDB.domain.Section.dtos.SectionResponseDto;
import com.schoolManagementDB.domain.Section.dtos.SectionUpdateDto;
import com.schoolManagementDB.domain.Section.services.ISectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
@AllArgsConstructor
public class SectionControllers {

    private final ISectionService sectionService;

    // ✅ Create a Section
    @PostMapping("/create")
    public ResponseEntity<SectionResponseDto> createSection(@RequestBody SectionDto sectionDto) {
        SectionResponseDto createdSection = sectionService.createSection(sectionDto);
        return ResponseEntity.ok(createdSection);
    }

    // ✅ Update a Section
    @PutMapping("/{sectionId}")
    public ResponseEntity<SectionResponseDto> updateSection(@PathVariable String sectionId, @RequestBody SectionUpdateDto sectionDto) {
        SectionResponseDto updatedSection = sectionService.updateSection(sectionId, sectionDto);
        return ResponseEntity.ok(updatedSection);
    }

    // ✅ Delete a Section
    @DeleteMapping("/{sectionId}")
    public ResponseEntity<String> deleteSection(@PathVariable String sectionId) {
        sectionService.deleteSection(sectionId);
        return ResponseEntity.ok("Section deleted successfully!");
    }

    // ✅ Get Single Section
    @GetMapping("/{sectionId}")
    public ResponseEntity<SectionResponseDto> getSection(@PathVariable String sectionId) {
        SectionResponseDto section = sectionService.getSectionById(sectionId);
        return ResponseEntity.ok(section);
    }

    // ✅ Get All Sections
    @GetMapping("/get-all-section")
    public ResponseEntity<List<SectionResponseDto>> getAllSections() {
        List<SectionResponseDto> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }
}
