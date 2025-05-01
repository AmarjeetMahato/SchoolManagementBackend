package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.StudentDto;
import com.schoolManagementDB.entities.*;

public class StudentMapper {

    public static Students toEntity(StudentDto dto) {
        if (dto == null) return null;

        return Students.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .gender(dto.getGender())
                .dateOfBirth(dto.getDateOfBirth())
                .rollNumber(dto.getRollNumber())
                .admissionDate(dto.getAdmissionDate())
                .isActive(dto.getIsActive())
                .build();
    }

    public static StudentDto toDto(Students student) {
        if (student == null) return null;

        return StudentDto.builder()
                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .rollNumber(student.getRollNumber())
                .admissionDate(student.getAdmissionDate())
                .isActive(student.isActive())
                .build();
    }
}