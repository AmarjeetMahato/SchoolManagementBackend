package com.schoolManagementDB.controllers;

import com.schoolManagementDB.dtos.SectionDto;
import com.schoolManagementDB.entities.Section;
import com.schoolManagementDB.services.SectionService.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/section")
@AllArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    // ✅ Create a Section
    @PostMapping("/create")
    public ResponseEntity<Section> createSection(@RequestBody SectionDto sectionDto) {
        Section createdSection = sectionService.createSection(sectionDto);
        return ResponseEntity.ok(createdSection);
    }

    // ✅ Update a Section
    @PutMapping("/{sectionId}")
    public ResponseEntity<Section> updateSection(@PathVariable String sectionId, @RequestBody SectionDto sectionDto) {
        Section updatedSection = sectionService.updateSection(sectionId, sectionDto);
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
    public ResponseEntity<Section> getSection(@PathVariable String sectionId) {
        Section section = sectionService.getSectionById(sectionId);
        return ResponseEntity.ok(section);
    }

    // ✅ Get All Sections
    @GetMapping("/get-all-section")
    public ResponseEntity<List<Section>> getAllSections() {
        List<Section> sections = sectionService.getAllSections();
        return ResponseEntity.ok(sections);
    }
}
