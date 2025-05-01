package com.schoolManagementDB.services.SectionService;

import com.schoolManagementDB.dtos.SectionDto;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.entities.Section;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.SectionMapper;
import com.schoolManagementDB.repositories.ClassRepo;
import com.schoolManagementDB.repositories.SectionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements  SectionService {
    private final SectionRepo sectionRepo;
    private final ClassRepo classRepo;
    private final SectionMapper sectionMapper;

    @Override
    public Section createSection(SectionDto dto) {
        if (dto == null || dto.getClassId() == null || dto.getClassId().isBlank()) {
            throw new ResourceNotFoundException("Class ID is required to create a section");
        }
        try {
            // Fetch the Classes entity from DB using classId
            Classes classEntity = classRepo.findById(dto.getClassId())
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + dto.getClassId()));
            // Convert DTO to Section entity
            Section section = sectionMapper.toEntity(dto);
            // Set the class entity relation
            section.setClassEntity(classEntity);
            // Save and return the section
            return sectionRepo.save(section);
        } catch (Exception e) {
            throw new InternalServerError("Failed to create section: " + e.getMessage());
        }
    }

    @Override
    public Section updateSection(String sectionId, SectionDto dto) {
        try {
            Section existingSection = sectionRepo.findById(sectionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + sectionId));

            Classes classEntity = classRepo.findById(dto.getClassId())
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + dto.getClassId()));

            existingSection.setName(dto.getName());
            existingSection.setShift(dto.getShift());
            existingSection.setClasses(dto.getClasses());
            existingSection.setRoomNumber(dto.getRoomNumber());
            existingSection.setStatus(dto.getStatus());
            existingSection.setClassEntity(classEntity);

            return sectionRepo.save(existingSection);
        } catch (Exception e) {
            throw new InternalServerError("Failed to update section: " + e.getMessage());
        }
    }

    @Override
    public void deleteSection(String sectionId) {
        try {
            Section section = sectionRepo.findById(sectionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + sectionId));
            sectionRepo.delete(section);
        } catch (Exception e) {
            throw new InternalServerError("Failed to delete section: " + e.getMessage());
        }
    }

    @Override
    public Section getSectionById(String sectionId) {
        try {
            return sectionRepo.findById(sectionId)
                    .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + sectionId));
        } catch (Exception e) {
            throw new InternalServerError("Failed to fetch section: " + e.getMessage());
        }
    }


        @Override
        public List<Section> getAllSections() {
            try {
                List<Section> allSection =  sectionRepo.findAll();
                if(allSection.isEmpty()){
                    throw  new ResourceNotFoundException("No Section found !!");
                }
                return  allSection;
            } catch (Exception e) {
                throw new InternalServerError("Failed to fetch all sections: " + e.getMessage());
            }
        }

}
