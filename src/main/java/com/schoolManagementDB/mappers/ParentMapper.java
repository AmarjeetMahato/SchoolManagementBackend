package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.ParentDto;
import com.schoolManagementDB.entities.Parents;

public class ParentMapper {

    public static Parents toEntity(ParentDto dto) {

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

    public static ParentDto toDto(Parents parent) {

        return ParentDto.builder()
                .fatherFirstname(parent.getFatherFirstname())
                .fatherMiddleName(parent.getFatherMiddleName())
                .fatherLastname(parent.getFatherLastname())
                .fatherEmail(parent.getFatherEmail())
                .guardianPhone1(parent.getGuardianPhone1())
                .guardianPhone2(parent.getGuardianPhone2())
                .motherFirstname(parent.getMotherFirstname())
                .motherMiddleName(parent.getMotherMiddleName())
                .motherLastname(parent.getMotherLastname())
                .fatherOccupation(parent.getFatherOccupation())
                .motherOccupation(parent.getMotherOccupation())
                .children(parent.getChildren())
                .build();
    }
}
