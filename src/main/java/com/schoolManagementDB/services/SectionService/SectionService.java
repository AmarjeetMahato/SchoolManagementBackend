package com.schoolManagementDB.services.SectionService;

import com.schoolManagementDB.dtos.SectionDto;
import com.schoolManagementDB.entities.Section;

import java.util.List;

public interface SectionService {

    Section createSection(SectionDto dto);
    Section updateSection(String sectionId, SectionDto dto);
    void deleteSection(String sectionId);
    Section getSectionById(String sectionId);
    List<Section> getAllSections();

}
