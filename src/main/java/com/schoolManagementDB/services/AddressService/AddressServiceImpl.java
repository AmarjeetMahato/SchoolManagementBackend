package com.schoolManagementDB.services.AddressService;

import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import com.schoolManagementDB.domain.Address.repository.AddressRepository;
import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.entities.Address;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.repositories.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepo;

    @Override
    public Address createAddress(AddressDto addressDto) {
        try {
            Address address = AddressMapper.toEntity(addressDto);
            return addressRepo.save(address);
        } catch (InternalServerError e) {
            throw new RuntimeException("Failed to create address", e);
        }
    }

    @Override
    public Address getAddressById(String addressId) {
        return addressRepo.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + addressId));
    }

    @Override
    public List<AddressDto> getAllAddresses() {
        try {
            return addressRepo.findAll()
                    .stream()
                    .map(AddressMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch addresses", e);
        }
    }

    @Override
    public Address updateAddress(String addressId, AddressDto addressDto) {
        try {
            Address address = getAddressById(addressId);

            address.setAddressLine1(addressDto.getAddressLine1());
            address.setAddressLine2(addressDto.getAddressLine2());
            address.setCity(addressDto.getCity());
            address.setState(addressDto.getState());
            address.setCountry(addressDto.getCountry());
            address.setPostalCode(addressDto.getPostalCode());
            address.setAddressType(addressDto.getAddressType());

            return addressRepo.save(address);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update address", e);
        }
    }

    @Override
    public void deleteAddress(String addressId) {
        try {
            Address address = getAddressById(addressId);
            addressRepo.delete(address);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete address", e);
        }
    }

    @Override
    public List<AddressDto> getAddressesByCity(String city) {
        try {
            return addressRepo.findByCityIgnoreCase(city)
                    .stream()
                    .map(AddressMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get addresses by city", e);
        }
    }

    @Override
    public List<AddressDto> getAddressesByState(String state) {
        try {
            return addressRepo.findByStateIgnoreCase(state)
                    .stream()
                    .map(AddressMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get addresses by state", e);
        }
    }

    @Override
    public List<AddressDto> getAddressesByPostalCode(String postalCode) {
        try {
            return addressRepo.findByPostalCode(postalCode)
                    .stream()
                    .map(AddressMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get addresses by postal code", e);
        }
    }
}
