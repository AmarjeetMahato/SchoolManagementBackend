package com.schoolManagementDB.domain.Address.services;

import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Address.dtos.AddressUpdateDto;
import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.domain.Address.mapper.AddressMapper;
import com.schoolManagementDB.domain.Address.repository.AddressRepository;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceAlreadyExistsException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private  final AddressMapper addressMapper;
    private  final AddressRepository addressRepository;

    @Transactional
    @Override
    public AddressResponseDto createAddress(AddressDto addressDto) {
        try {
            boolean exists = addressRepository
                    .existsByAddressLine1AndCityAndStateAndPostalCode(
                            addressDto.getAddressLine1(),
                            addressDto.getCity(),
                            addressDto.getState(),
                            addressDto.getPostalCode()
                    );
            if(exists){
                 throw  new ResourceAlreadyExistsException( "Address already exists with same location details");
            }
            // DTO -> Entity
            Address address =  addressMapper.toEntity(addressDto);
            Address savedAddress = addressRepository.save(address);
            // Entity -> Response
            return  addressMapper.toResponse(savedAddress);
        } catch (InternalServerError e) {
            throw new RuntimeException("Failed to create address", e);
        }
    }

    @Override
    public AddressResponseDto getAddressById(String addressId) {
        Address  address =  addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + addressId));

        return  addressMapper.toResponse(address);
    }

    @Override
    public List<AddressResponseDto> getAllAddresses() {
        try {
            return addressRepository.findAll()
                    .stream()
                    .map(addressMapper::toResponse)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch addresses", e);
        }
    }

    @Override
    public AddressResponseDto updateAddress(String addressId, AddressUpdateDto addressDto) {
        try {
            Address address = addressRepository.findById(addressId).orElseThrow(()->
                    new ResourceNotFoundException("Address not found"));
            // Update using mapper
            addressMapper.updateEntity(addressDto, address);
            // Save updated entity
            Address updatedAddress = addressRepository.save(address);
            return addressMapper.toResponse(updatedAddress);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update address", e);
        }
    }

    @Override
    public void deleteAddress(String addressId) {
        try {
            Address address = addressRepository.findById(addressId).orElseThrow(()->
                    new ResourceNotFoundException("Address not found"));

            addressRepository.delete(address);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete address", e);
        }
    }

    @Override
    public List<AddressResponseDto> getAddressesByCity(String city) {
        try {
            return addressRepository.findByCityIgnoreCase(city)
                    .stream()
                    .map(addressMapper::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get addresses by city", e);
        }
    }

    @Override
    public List<AddressResponseDto> getAddressesByState(String state) {
        try {
            return addressRepository.findByStateIgnoreCase(state)
                    .stream()
                    .map(addressMapper::toResponse)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to get addresses by state", e);
        }
    }

    @Override
    public List<AddressResponseDto> getAddressesByPostalCode(String postalCode) {
        try {
            return addressRepository.findByPostalCode(postalCode)
                    .stream()
                    .map(addressMapper::toResponse)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Failed to get addresses by postal code", e);
        }
    }
}
