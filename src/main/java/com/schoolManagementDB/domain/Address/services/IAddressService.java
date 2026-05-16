package com.schoolManagementDB.domain.Address.services;

import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Address.dtos.AddressUpdateDto;

import java.util.List;

public interface IAddressService {
    // Create a new address
    AddressResponseDto createAddress(AddressDto addressDto);

    // Get address by ID
    AddressResponseDto getAddressById(String addressId);

    // Get all addresses
    List<AddressResponseDto> getAllAddresses();

    // Update address by ID
    AddressResponseDto updateAddress(String addressId, AddressUpdateDto addressDto);

    // Delete address by ID
    void deleteAddress(String addressId);

    // Get addresses by city
    List<AddressResponseDto> getAddressesByCity(String city);

    // Get addresses by state
    List<AddressResponseDto> getAddressesByState(String state);

    // Get addresses by postal code
    List<AddressResponseDto> getAddressesByPostalCode(String postalCode);
}
