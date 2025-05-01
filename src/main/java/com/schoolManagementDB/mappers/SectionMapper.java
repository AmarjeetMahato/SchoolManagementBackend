package com.schoolManagementDB.mappers;


import com.schoolManagementDB.dtos.SectionDto;
import com.schoolManagementDB.entities.Section;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {


    // Mapping from DTO to Entity
    public Section toEntity(SectionDto dto) {
        if (dto == null) {
            return null;
        }
        return Section.builder()
                .name(dto.getName())
                .shift(dto.getShift())
                .classes(dto.getClasses())
                .roomNumber(dto.getRoomNumber())
                .status(dto.getStatus())
                // classEntity will be set separately in service
                .build();
    }

    // Mapping from Entity to DTO
    public SectionDto toDto(Section section) {
        if (section == null) {
            return null;
        }
        return SectionDto.builder()
                .name(section.getName())
                .shift(section.getShift())
                .classes(section.getClasses())
                .roomNumber(section.getRoomNumber())
                .status(section.getStatus())
                .classId(section.getClassEntity() != null ? section.getClassEntity().getClassId() : null)
                .build();
    }
}
