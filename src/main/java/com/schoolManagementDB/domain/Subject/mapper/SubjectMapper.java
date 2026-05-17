package com.schoolManagementDB.domain.Subject.mapper;

import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Subject.dtos.SubjectDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectResponseDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectUpdateDto;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    // -------------------------------------------------------
    // CREATE : DTO -> ENTITY
    // -------------------------------------------------------
    public Subject toEntity(SubjectDto dto, Classes classes) {

        if (dto == null) {
            throw new BadRequestException("Invalid subject input data");
        }

        return Subject.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .classes(classes)
                .build();
    }

    // -------------------------------------------------------
    // UPDATE : PATCH STYLE
    // -------------------------------------------------------
    public void updateEntity(SubjectUpdateDto dto, Subject entity, Classes classes) {

        if (dto == null || entity == null) {
            throw new BadRequestException("Invalid subject update data");
        }

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getCode() != null) {
            entity.setCode(dto.getCode());
        }

        if (dto.getStartTime() != null) {
            entity.setStartTime(dto.getStartTime());
        }

        if (dto.getEndTime() != null) {
            entity.setEndTime(dto.getEndTime());
        }

        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }

        if (classes != null) {
            entity.setClasses(classes);
        }
    }

    // -------------------------------------------------------
    // RESPONSE : ENTITY -> DTO
    // -------------------------------------------------------
    public SubjectResponseDto toResponse(Subject subject) {

        if (subject == null) {
            throw new BadRequestException("Subject entity cannot be null");
        }

        return SubjectResponseDto.builder()
                .subjectId(subject.getSubjectId())
                .name(subject.getName())
                .code(subject.getCode())
                .startTime(subject.getStartTime())
                .endTime(subject.getEndTime())
                .description(subject.getDescription())
                .status(subject.getStatus())

                // Class Details
                .classId(
                        subject.getClasses() != null
                                ? subject.getClasses().getClassId()
                                : null
                )

                .className(
                        subject.getClasses() != null
                                ? subject.getClasses().getName()
                                : null
                )

                // Statistics
                .totalTeacherAssignments(
                        subject.getTeacherAssignments() != null
                                ? subject.getTeacherAssignments().size()
                                : 0
                )

                .totalExamSchedules(
                        subject.getExamSchedules() != null
                                ? subject.getExamSchedules().size()
                                : 0
                )

                .totalAttendanceRecords(
                        subject.getAttendanceList() != null
                                ? subject.getAttendanceList().size()
                                : 0
                )

                .createdAt(subject.getCreatedAt())
                .updatedAt(subject.getUpdatedAt())
                .build();
    }
}