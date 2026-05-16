package com.schoolManagementDB.domain.Students.mapper;

import com.schoolManagementDB.domain.Attendance.entity.Attendance;
import com.schoolManagementDB.domain.Students.dtos.StudentResponseDto;
import com.schoolManagementDB.domain.Students.dtos.StudentsDtos;
import com.schoolManagementDB.domain.Students.entity.Student;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class StudentsMapper {

    public Student toEntity(StudentsDtos dto) {
        if (dto == null)  throw new BadRequestException("");

        return Student.builder()
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

    public StudentResponseDto toResponse(Student student) {

        if (student == null) {
            return null;
        }

        return StudentResponseDto.builder()
                .studentId(student.getStudentId())

                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())

                .fullName(
                        student.getFirstName() + " " +
                                (student.getMiddleName() != null
                                        ? student.getMiddleName() + " "
                                        : "") +
                                student.getLastName()
                )

                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .rollNumber(student.getRollNumber())
                .admissionDate(student.getAdmissionDate())
                .isActive(student.isActive())
                // Address
                .addressId(
                        student.getAddress() != null
                                ? student.getAddress().getAddressId()
                                : null
                )

                // Class
                .classId(
                        student.getClasses() != null
                                ? student.getClasses().getClassId()
                                : null
                )
                .className(
                        student.getClasses() != null
                                ? student.getClasses().getName()
                                : null
                )

                // Section
                .sectionId(
                        student.getSection() != null
                                ? student.getSection().getSectionId()
                                : null
                )
                .sectionName(
                        student.getSection() != null
                                ? student.getSection().getName()
                                : null
                )

                // Parent
                .parentId(
                        student.getParent() != null
                                ? student.getParent().getParentId()
                                : null
                )
                .parentName(
                        student.getParent() != null
                                ? student.getParent().getFatherFirstname() + " " +
                                student.getParent().getFatherLastname()
                                : null
                )

                // Attendance
                .attendanceIds(
                        student.getAttendanceList() != null
                                ? student.getAttendanceList()
                                .stream()
                                .map(Attendance::getAttendanceId)
                                .toList()
                                : Collections.emptyList()
                )

                .createdAt(student.getCreatedAt())

                .updatedAt(student.getUpdatedAt())

                .build();
    }

}
