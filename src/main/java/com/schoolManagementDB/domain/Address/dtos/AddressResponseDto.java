package com.schoolManagementDB.domain.Address.dtos;

import com.schoolManagementDB.domain.Address.enums.AddressOwnerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponseDto {

    private String addressId;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private AddressOwnerType ownerType;

    private String postalCode;

    private String addressType;

    // Relation Counts
    private int totalTeachers;

    private int totalStudents;

    private int totalParents;

    private LocalDateTime createAt;

    private LocalDateTime updatedAt;
}