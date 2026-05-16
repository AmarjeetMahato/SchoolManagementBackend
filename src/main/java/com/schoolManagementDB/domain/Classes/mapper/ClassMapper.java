package com.schoolManagementDB.domain.Classes.mapper;

import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassResponseDto;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.entities.Classes;
import org.springframework.stereotype.Component;

import java.util.Collections;

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

    public ClassResponseDto toResponse(Classes classes) {
        if (classes == null) {
            return null;
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
