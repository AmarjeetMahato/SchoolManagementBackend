package com.schoolManagementDB.domain.Parents.mapper;

import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsResponseDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsUpdateDto;
import com.schoolManagementDB.domain.Parents.entity.Parents;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ParentsMapper {

    // -------------------------------------------------------
    // CREATE : DTO -> ENTITY
    // -------------------------------------------------------
    public Parents toEntity(ParentsDto dto) {

        if (dto == null) {
            throw new BadRequestException("Invalid parent input data");
        }

        return Parents.builder()
                .fatherFirstname(dto.getFatherFirstname())
                .fatherMiddleName(dto.getFatherMiddleName())
                .fatherLastname(dto.getFatherLastname())
                .fatherEmail(dto.getFatherEmail())

                .guardianPhone1(dto.getGuardianPhone1())
                .guardianPhone2(dto.getGuardianPhone2())

                .motherFirstname(dto.getMotherFirstname())
                .motherMiddleName(dto.getMotherMiddleName())
                .motherLastname(dto.getMotherLastname())

                .fatherOccupation(dto.getFatherOccupation())
                .motherOccupation(dto.getMotherOccupation())

                .children(dto.getChildren())
                .build();
    }

    // -------------------------------------------------------
    // UPDATE : PATCH STYLE
    // -------------------------------------------------------
    public void updateEntity(ParentsUpdateDto dto, Parents entity) {

        if (dto == null || entity == null) {
            throw new BadRequestException("Invalid parent update data");
        }

        if (dto.getFatherFirstname() != null) {
            entity.setFatherFirstname(dto.getFatherFirstname());
        }

        if (dto.getFatherMiddleName() != null) {
            entity.setFatherMiddleName(dto.getFatherMiddleName());
        }

        if (dto.getFatherLastname() != null) {
            entity.setFatherLastname(dto.getFatherLastname());
        }

        if (dto.getFatherEmail() != null) {
            entity.setFatherEmail(dto.getFatherEmail());
        }

        if (dto.getGuardianPhone1() != null) {
            entity.setGuardianPhone1(dto.getGuardianPhone1());
        }

        if (dto.getGuardianPhone2() != null) {
            entity.setGuardianPhone2(dto.getGuardianPhone2());
        }

        if (dto.getMotherFirstname() != null) {
            entity.setMotherFirstname(dto.getMotherFirstname());
        }

        if (dto.getMotherMiddleName() != null) {
            entity.setMotherMiddleName(dto.getMotherMiddleName());
        }

        if (dto.getMotherLastname() != null) {
            entity.setMotherLastname(dto.getMotherLastname());
        }

        if (dto.getFatherOccupation() != null) {
            entity.setFatherOccupation(dto.getFatherOccupation());
        }

        if (dto.getMotherOccupation() != null) {
            entity.setMotherOccupation(dto.getMotherOccupation());
        }

        if (dto.getChildren() != null) {
            entity.setChildren(dto.getChildren());
        }
    }

    // -------------------------------------------------------
    // RESPONSE : ENTITY -> DTO
    // -------------------------------------------------------
    public ParentsResponseDto toResponse(Parents parent) {

        if (parent == null) {
            throw new BadRequestException("Parent entity cannot be null");
        }

        return ParentsResponseDto.builder()

                .parentId(parent.getParentId())

                // Father Details
                .fatherFirstname(parent.getFatherFirstname())
                .fatherMiddleName(parent.getFatherMiddleName())
                .fatherLastname(parent.getFatherLastname())

                .fatherFullName(
                        parent.getFatherFirstname()
                                + " "
                                + (
                                parent.getFatherMiddleName() != null
                                        ? parent.getFatherMiddleName() + " "
                                        : ""
                        )
                                + parent.getFatherLastname()
                )

                .fatherEmail(parent.getFatherEmail())

                // Guardian Contact
                .guardianPhone1(parent.getGuardianPhone1())
                .guardianPhone2(parent.getGuardianPhone2())

                // Mother Details
                .motherFirstname(parent.getMotherFirstname())
                .motherMiddleName(parent.getMotherMiddleName())
                .motherLastname(parent.getMotherLastname())

                .motherFullName(
                        parent.getMotherFirstname()
                                + " "
                                + (
                                parent.getMotherMiddleName() != null
                                        ? parent.getMotherMiddleName() + " "
                                        : ""
                        )
                                + parent.getMotherLastname()
                )

                // Occupation
                .fatherOccupation(parent.getFatherOccupation())
                .motherOccupation(parent.getMotherOccupation())

                // Children
                .children(parent.getChildren())

                // Address
                .address(
                        parent.getAddress() != null
                                ? AddressResponseDto.builder()
                                .addressId(parent.getAddress().getAddressId())
                                .addressLine1(parent.getAddress().getAddressLine1())
                                .addressLine2(parent.getAddress().getAddressLine2())
                                .city(parent.getAddress().getCity())
                                .state(parent.getAddress().getState())
                                .country(parent.getAddress().getCountry())
                                .postalCode(parent.getAddress().getPostalCode())
                                .addressType(parent.getAddress().getAddressType())
                                .ownerType(parent.getAddress().getOwnerType())
                                .createAt(parent.getAddress().getCreateAt())
                                .updatedAt(parent.getAddress().getUpdatedAt())
                                .build()
                                : null
                )

                // Students
                .studentIds(
                        parent.getStudent() != null
                                ? parent.getStudent()
                                .stream()
                                .map(Student::getStudentId)
                                .toList()
                                : Collections.emptyList()
                )

                .totalStudents(
                        parent.getStudent() != null
                                ? parent.getStudent().size()
                                : 0
                )

                .createdAt(parent.getCreatedAt())
                .updatedAt(parent.getUpdatedAt())

                .build();
    }
}