package com.schoolManagementDB.domain.Classes.mapper;

import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassResponseDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassUpdateDto;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ClassMapper {


    public Classes toEntity(ClassDto dto) {
        if (dto == null){
            throw new BadRequestException("Invalid Input data");

        }

        return Classes.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }

    public void updateEntity(ClassUpdateDto dto, Classes entity) {

        if (dto == null || entity == null) {
            throw new BadRequestException("Invalid update data");
        }

        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }

        if (dto.getCode() != null) {
            entity.setCode(dto.getCode());
        }

        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
    }


    public ClassResponseDto toResponse(Classes classes) {
        if (classes == null) {
            throw new BadRequestException("Invalid entity data");

        }

        return ClassResponseDto.builder()
                .classId(classes.getClassId())
                .name(classes.getName())
                .code(classes.getCode())
                .description(classes.getDescription())
                .status(classes.getStatus())

                // Teacher details
                .teacherId(
                        classes.getTeacher() != null
                                ? classes.getTeacher().getTeacherId()
                                : null
                )
                .teacherName(
                        classes.getTeacher() != null
                                ? classes.getTeacher().getFirstName() + " " +
                                classes.getTeacher().getLastName()
                                : null
                )

                // Section IDs
                .sectionIds(
                        classes.getSections() != null
                                ? classes.getSections()
                                .stream()
                                .map(Section::getSectionId)
                                .toList()
                                : Collections.emptyList()
                )

                // Student IDs
                .studentIds(
                        classes.getStudents() != null
                                ? classes.getStudents()
                                .stream()
                                .map(Student::getStudentId)
                                .toList()
                                : Collections.emptyList()
                )

                // Subject IDs
                .subjectIds(
                        classes.getSubjects() != null
                                ? classes.getSubjects()
                                .stream()
                                .map(Subject::getSubjectId)
                                .toList()
                                : Collections.emptyList()
                )

                .createdAt(classes.getCreatedAt())
                .updatedAt(classes.getUpdatedAt())
                .build();
    }

}
