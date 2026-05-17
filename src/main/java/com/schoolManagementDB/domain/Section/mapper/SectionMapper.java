package com.schoolManagementDB.domain.Section.mapper;

import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Section.dtos.SectionDto;
import com.schoolManagementDB.domain.Section.dtos.SectionResponseDto;
import com.schoolManagementDB.domain.Section.dtos.SectionUpdateDto;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class SectionMapper {

    // ---------------- CREATE (DTO → ENTITY) ----------------
    public Section toEntity(SectionDto dto, Classes classes) {

        if (dto == null) {
            throw new BadRequestException("Invalid input data");
        }

        return Section.builder()
                .name(dto.getName())
                .shift(dto.getShift())
                .classes(dto.getClasses())
                .roomNumber(dto.getRoomNumber())
                .status(dto.getStatus())
                .classEntity(classes)
                .build();
    }

    // ---------------- UPDATE (PATCH STYLE) ----------------
    public void updateEntity(SectionUpdateDto dto, Section entity, Classes classes) {

        if (dto == null || entity == null) {
            throw new BadRequestException("Invalid update data");
        }

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getShift() != null) {
            entity.setShift(dto.getShift());
        }

        if (dto.getClasses() != null) {
            entity.setClasses(dto.getClasses());
        }

        if (dto.getRoomNumber() != null) {
            entity.setRoomNumber(dto.getRoomNumber());
        }

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        if (classes != null) {
            entity.setClassEntity(classes);
        }
    }

    // ---------------- RESPONSE (ENTITY → DTO) ----------------
    public SectionResponseDto toResponse(Section section) {

        if (section == null) {
            throw new BadRequestException("Section not found");
        }

        return SectionResponseDto.builder()
                .sectionId(section.getSectionId())
                .name(section.getName())
                .shift(section.getShift())
                .classes(section.getClasses())
                .roomNumber(section.getRoomNumber())
                .status(section.getStatus())

                .classId(
                        section.getClassEntity() != null
                                ? section.getClassEntity().getClassId()
                                : null
                )
                .className(
                        section.getClassEntity() != null
                                ? section.getClassEntity().getName()
                                : null
                )

                .totalStudents(
                        section.getStudents() != null
                                ? section.getStudents().size()
                                : 0
                )

                .totalTeachers(
                        section.getTeacherSubjectSections() != null
                                ? section.getTeacherSubjectSections().size()
                                : 0
                )

                .createdAt(section.getCreatedAt())
                .updatedAt(section.getUpdatedAt())
                .build();
    }
}