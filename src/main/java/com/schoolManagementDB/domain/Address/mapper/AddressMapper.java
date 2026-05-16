package com.schoolManagementDB.domain.Address.mapper;

import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Address.dtos.AddressUpdateDto;
import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.exceptions.BadRequestException;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public Address toEntity(AddressDto dto) {

        if (dto == null) {
             throw new BadRequestException("Invalid fields value");
        }

        return Address.builder()
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .country(dto.getCountry())
                .ownerType(dto.getOwnerType())
                .postalCode(dto.getPostalCode())
                .addressType(dto.getAddressType())
                .build();
    }

    public void updateEntity(AddressUpdateDto dto, Address address) {

        if (dto == null || address == null) {
            throw new BadRequestException("Invalid address update data");
        }

        address.setAddressLine1(dto.getAddressLine1());
        address.setAddressLine2(dto.getAddressLine2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setOwnerType(dto.getOwnerType());
        address.setPostalCode(dto.getPostalCode());
        address.setAddressType(dto.getAddressType());
    }

    public AddressResponseDto toResponse(Address address) {

        if (address == null) {
            throw new BadRequestException("Invalid fields value");
        }

        return AddressResponseDto.builder()
                .addressId(address.getAddressId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .ownerType(address.getOwnerType())
                .postalCode(address.getPostalCode())
                .addressType(address.getAddressType())

                .totalTeachers(address.getTeachers() != null ? address.getTeachers().size() : 0)
                .totalStudents(
                        address.getStudents() != null
                                ? address.getStudents().size()
                                : 0
                )
                .totalParents(
                        address.getParents() != null
                                ? address.getParents().size()
                                : 0
                )
                .createAt(address.getCreateAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }


}