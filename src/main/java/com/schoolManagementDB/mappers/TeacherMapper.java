package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.TeacherDto;
import com.schoolManagementDB.entities.Teacher;

public class TeacherMapper {

    // Convert Teacher entity to TeacherDto
    public static TeacherDto toDto(Teacher teacher) {
        return TeacherDto.builder()
                .firstName(teacher.getFirstName())
                .middleName(teacher.getMiddleName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .phone(teacher.getPhone())
                .dateOfBirth(teacher.getDateOfBirth())
                .gender(teacher.getGender())
                .qualification(teacher.getQualification())
                .profilePic(teacher.getProfilePic())
                .hireDate(teacher.getHireDate())
                .status(teacher.getStatus())
                .build();
    }

    // Convert TeacherDto to Teacher entity
    public static Teacher toEntity(TeacherDto teacherDto) {
        return Teacher.builder()
                .firstName(teacherDto.getFirstName())
                .middleName(teacherDto.getMiddleName())
                .lastName(teacherDto.getLastName())
                .email(teacherDto.getEmail())
                .phone(teacherDto.getPhone())
                .dateOfBirth(teacherDto.getDateOfBirth())
                .gender(teacherDto.getGender())
                .qualification(teacherDto.getQualification())
                .profilePic(teacherDto.getProfilePic())
                .hireDate(teacherDto.getHireDate())
                .status(teacherDto.getStatus())
                .build();
    }
}
