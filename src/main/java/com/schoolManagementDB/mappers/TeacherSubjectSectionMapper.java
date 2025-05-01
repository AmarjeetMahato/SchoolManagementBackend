package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.TeacherSubjectSectionDto;
import com.schoolManagementDB.entities.Teacher_Subject_Section;

public class TeacherSubjectSectionMapper {


    public static Teacher_Subject_Section toEntity(TeacherSubjectSectionDto dto) {
        return Teacher_Subject_Section.builder()
                .classes(dto.getClasses())
                .assignedAt(dto.getAssignedAt())
                .build();
    }
}
