package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.ClassDto;
import com.schoolManagementDB.entities.Classes;
import org.springframework.stereotype.Component;

@Component
public class ClassMapper {

    public Classes toEntity(ClassDto dto) {
        if (dto == null) return null;

        return Classes.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    public ClassDto toDto(Classes entity) {
        if (entity == null) return null;

        return ClassDto.builder()
                .name(entity.getName())
                .code(entity.getCode())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .build();
    }
}
