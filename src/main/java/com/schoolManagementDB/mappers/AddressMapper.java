package com.schoolManagementDB.mappers;

import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.entities.Address;

public class AddressMapper {

    // Convert AddressDto to Address Entity
    public static Address toEntity(AddressDto dto) {
        if (dto == null) return null;

        return Address.builder()
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .postalCode(dto.getPostalCode())
                .ownerType(dto.getOwnerType())          // 🆕 mapping ownerType also
                .addressType(dto.getAddressType())
                .build();
    }

    // Convert Address Entity to AddressDto
    public static AddressDto toDto(Address address) {
        if (address == null) return null;

        return AddressDto.builder()
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .ownerType(address.getOwnerType())          // 🆕 mapping ownerType also
                .addressType(address.getAddressType())
                .build();
    }
}
