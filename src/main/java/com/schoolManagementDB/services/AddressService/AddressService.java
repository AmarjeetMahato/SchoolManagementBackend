package com.schoolManagementDB.services.AddressService;

import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.entities.Address;

import java.util.List;

public interface AddressService {

    // Create a new address
    Address createAddress(AddressDto addressDto);

    // Get address by ID
    Address getAddressById(String addressId);

    // Get all addresses
    List<AddressDto> getAllAddresses();

    // Update address by ID
    Address updateAddress(String addressId, AddressDto addressDto);

    // Delete address by ID
    void deleteAddress(String addressId);

    // Get addresses by city
    List<AddressDto> getAddressesByCity(String city);

    // Get addresses by state
    List<AddressDto> getAddressesByState(String state);

    // Get addresses by postal code
    List<AddressDto> getAddressesByPostalCode(String postalCode);
}
