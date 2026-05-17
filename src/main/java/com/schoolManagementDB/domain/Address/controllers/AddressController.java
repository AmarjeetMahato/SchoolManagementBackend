package com.schoolManagementDB.domain.Address.controllers;


import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Address.dtos.AddressUpdateDto;
import com.schoolManagementDB.domain.Address.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {


    private final IAddressService addressService;

    // Create address
    @PostMapping("/create")
    public ResponseEntity<AddressResponseDto> createAddress(@RequestBody AddressDto addressDto) {
        AddressResponseDto address = addressService.createAddress(addressDto);
        return ResponseEntity.ok(address);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDto> getAddressById(@PathVariable("id") String id) {
        AddressResponseDto address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressResponseDto>> getAllAddresses() {
        List<AddressResponseDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    // Update address
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDto> updateAddress(
            @PathVariable("id") String id,
            @RequestBody AddressUpdateDto addressDto
    ) {
        AddressResponseDto updatedAddress = addressService.updateAddress(id, addressDto);
        return ResponseEntity.ok(updatedAddress);
    }

    // Delete address
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") String id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    // Filter by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByCity(@PathVariable("city") String city) {
        return ResponseEntity.ok(addressService.getAddressesByCity(city));
    }

    // Filter by state
    @GetMapping("/state/{state}")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByState(@PathVariable("state") String state) {
        return ResponseEntity.ok(addressService.getAddressesByState(state));
    }

    // Filter by postal code
    @GetMapping("/postal-code/{postalCode}")
    public ResponseEntity<List<AddressResponseDto>> getAddressesByPostalCode(@PathVariable("postalCode") String postalCode) {
        return ResponseEntity.ok(addressService.getAddressesByPostalCode(postalCode));
    }
}
