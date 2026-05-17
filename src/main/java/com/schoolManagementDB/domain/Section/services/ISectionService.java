package com.schoolManagementDB.domain.Section.services;

import com.schoolManagementDB.domain.Section.dtos.SectionDto;
import com.schoolManagementDB.domain.Section.dtos.SectionResponseDto;
import com.schoolManagementDB.domain.Section.dtos.SectionUpdateDto;

import java.util.List;

public interface ISectionService {

    SectionResponseDto createSection(SectionDto dto);
    SectionResponseDto updateSection(String sectionId, SectionUpdateDto dto);
    void deleteSection(String sectionId);
    SectionResponseDto getSectionById(String sectionId);
    List<SectionResponseDto> getAllSections();

}
