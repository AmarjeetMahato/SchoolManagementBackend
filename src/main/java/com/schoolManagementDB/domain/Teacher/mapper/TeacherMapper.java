package com.schoolManagementDB.domain.Teacher.mapper;

import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherResponseDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherUpdateDto;
import com.schoolManagementDB.domain.Teacher.entity.Teacher;
import com.schoolManagementDB.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TeacherMapper {

    // -------------------------------------------------------
    // CREATE : DTO -> ENTITY
    // -------------------------------------------------------
    public Teacher toEntity(TeacherDto dto) {

        if (dto == null) {
            throw new BadRequestException("Invalid teacher input data");
        }

        return Teacher.builder()
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .dateOfBirth(dto.getDateOfBirth())
                .gender(dto.getGender())
                .qualification(dto.getQualification())
                .profilePic(dto.getProfilePic())
                .hireDate(dto.getHireDate())
                .status(dto.getStatus())
                .build();
    }

    // -------------------------------------------------------
    // UPDATE : PATCH STYLE
    // -------------------------------------------------------
    public void updateEntity(TeacherUpdateDto dto, Teacher entity) {

        if (dto == null || entity == null) {
            throw new BadRequestException("Invalid teacher update data");
        }

        if (dto.getFirstName() != null) {
            entity.setFirstName(dto.getFirstName());
        }

        if (dto.getMiddleName() != null) {
            entity.setMiddleName(dto.getMiddleName());
        }

        if (dto.getLastName() != null) {
            entity.setLastName(dto.getLastName());
        }

        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }

        if (dto.getPhone() != null) {
            entity.setPhone(dto.getPhone());
        }

        if (dto.getDateOfBirth() != null) {
            entity.setDateOfBirth(dto.getDateOfBirth());
        }

        if (dto.getGender() != null) {
            entity.setGender(dto.getGender());
        }

        if (dto.getQualification() != null) {
            entity.setQualification(dto.getQualification());
        }

        if (dto.getProfilePic() != null) {
            entity.setProfilePic(dto.getProfilePic());
        }

        if (dto.getHireDate() != null) {
            entity.setHireDate(dto.getHireDate());
        }

        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
    }

    // -------------------------------------------------------
    // RESPONSE : ENTITY -> DTO
    // -------------------------------------------------------
    public TeacherResponseDto toResponse(Teacher teacher) {

        if (teacher == null) {
            throw new BadRequestException("Teacher entity cannot be null");
        }

        return TeacherResponseDto.builder()
                // Basic Info

                .teacherId(teacher.getTeacherId())

                .firstName(teacher.getFirstName())
                .middleName(teacher.getMiddleName())
                .lastName(teacher.getLastName())

                .fullName(
                        teacher.getFirstName()
                                + " "
                                + (
                                teacher.getMiddleName() != null
                                        ? teacher.getMiddleName() + " "
                                        : ""
                        )
                                + teacher.getLastName()
                )

                // Contact
                .email(teacher.getEmail())
                .phone(teacher.getPhone())

                // Personal
                .dateOfBirth(teacher.getDateOfBirth())
                .gender(teacher.getGender())
                .qualification(teacher.getQualification())

                // Profile
                .profilePic(teacher.getProfilePic())

                // Employment
                .hireDate(teacher.getHireDate())
                .status(teacher.getStatus())

                // Address
                .address(
                        teacher.getAddress() != null
                                ? AddressResponseDto.builder()
                                .addressId(
                                        teacher.getAddress().getAddressId()
                                )
                                .addressLine1(
                                        teacher.getAddress().getAddressLine1()
                                )
                                .addressLine2(
                                        teacher.getAddress().getAddressLine2()
                                )
                                .city(
                                        teacher.getAddress().getCity()
                                )
                                .state(
                                        teacher.getAddress().getState()
                                )
                                .country(
                                        teacher.getAddress().getCountry()
                                )
                                .postalCode(
                                        teacher.getAddress().getPostalCode()
                                )
                                .addressType(
                                        teacher.getAddress().getAddressType()
                                )
                                .ownerType(
                                        teacher.getAddress().getOwnerType()
                                )
                                .createAt(
                                        teacher.getAddress().getCreateAt()
                                )
                                .updatedAt(
                                        teacher.getAddress().getUpdatedAt()
                                )
                                .build()
                                : null
                )

                // Classes
                .classIds(
                        teacher.getClasses() != null
                                ? teacher.getClasses()
                                .stream()
                                .map(Classes::getClassId)
                                .toList()
                                : Collections.emptyList()
                )

                .totalClasses(
                        teacher.getClasses() != null
                                ? teacher.getClasses().size()
                                : 0
                )

                // Assigned Subjects
                .totalAssignedSubjects(
                        teacher.getAssignedSubjects() != null
                                ? teacher.getAssignedSubjects().size()
                                : 0
                )

                // Exam Duties
                .totalExamDuties(
                        teacher.getExamDuties() != null
                                ? teacher.getExamDuties().size()
                                : 0
                )

                // Audit
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())

                .build();
    }
}