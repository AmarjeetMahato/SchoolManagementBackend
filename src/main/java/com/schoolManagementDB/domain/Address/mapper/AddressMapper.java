package com.schoolManagementDB.domain.Address.mapper;

import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.entities.Address;
import org.springframework.stereotype.Component;

@Component
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
}
