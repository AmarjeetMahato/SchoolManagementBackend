package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.SubjectDto;
import com.schoolManagementDB.entities.Subject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class SubjectMapper {

    // Convert SubjectDto to Subject entity
    public Subject toEntity(SubjectDto dto) {
        if (dto == null) {
            return null;
        }

        return Subject.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .startTime(dto.getStartTime())  // Use LocalDateTime directly
                .endTime(dto.getEndTime())      // Use LocalDateTime directly
                .status(dto.getStatus())
                .build();
    }

    // Convert Subject entity to SubjectDto
    public SubjectDto toDto(Subject subject) {
        if (subject == null) {
            return null;
        }

        return SubjectDto.builder()
                .name(subject.getName())
                .code(subject.getCode())
                .description(subject.getDescription())
                .startTime(subject.getStartTime())  // Use LocalDateTime directly
                .endTime(subject.getEndTime())      // Use LocalDateTime directly
                .status(subject.getStatus())
                .classId(subject.getClasses() != null ? subject.getClasses().getClassId() : null)
                .build();
    }
}