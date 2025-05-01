package com.schoolManagementDB.controllers;


import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.entities.Address;
import com.schoolManagementDB.services.AddressService.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {


    private final AddressService addressService;

    // Create address
    @PostMapping("/create")
    public ResponseEntity<Address> createAddress(@RequestBody AddressDto addressDto) {
        Address address = addressService.createAddress(addressDto);
        return ResponseEntity.ok(address);
    }

    // Get address by ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") String id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    // Get all addresses
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<AddressDto> addresses = addressService.getAllAddresses();
        return ResponseEntity.status(HttpStatus.OK).body(addresses);
    }

    // Update address
    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable("id") String id,
            @RequestBody AddressDto addressDto
    ) {
        Address updatedAddress = addressService.updateAddress(id, addressDto);
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
    public ResponseEntity<List<AddressDto>> getAddressesByCity(@PathVariable("city") String city) {
        return ResponseEntity.ok(addressService.getAddressesByCity(city));
    }

    // Filter by state
    @GetMapping("/state/{state}")
    public ResponseEntity<List<AddressDto>> getAddressesByState(@PathVariable("state") String state) {
        return ResponseEntity.ok(addressService.getAddressesByState(state));
    }

    // Filter by postal code
    @GetMapping("/postal-code/{postalCode}")
    public ResponseEntity<List<AddressDto>> getAddressesByPostalCode(@PathVariable("postalCode") String postalCode) {
        return ResponseEntity.ok(addressService.getAddressesByPostalCode(postalCode));
    }
}
