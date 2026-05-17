package com.schoolManagementDB.domain.Parents.services;


import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.domain.Address.mapper.AddressMapper;
import com.schoolManagementDB.domain.Parents.dtos.ParentsDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsResponseDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsUpdateDto;
import com.schoolManagementDB.domain.Parents.entity.Parents;
import com.schoolManagementDB.domain.Parents.mapper.ParentsMapper;
import com.schoolManagementDB.domain.Parents.repository.ParentsRepository;
import com.schoolManagementDB.exceptions.BadRequestException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParentsServicesImpl implements  IParentsService {

    private  final ParentsRepository parentsRepository;
    private final ParentsMapper parentsMapper;
    private  final AddressMapper addressMapper;

    @Transactional
    @Override
    public ParentsResponseDto createParent(ParentsDto parentsDto) {

        try {

            if (parentsDto == null) {
                throw new BadRequestException("Parent data cannot be null");
            }

            if (parentsDto.getFatherEmail() != null &&
                    parentsRepository.existsByFatherEmail(
                            parentsDto.getFatherEmail()
                    )) {

                throw new ResourceNotFoundException(
                        "Parent already exists with this email"
                );
            }

            if (parentsDto.getGuardianPhone1() != null &&
                    parentsRepository.existsByGuardianPhone1(
                            parentsDto.getGuardianPhone1()
                    )) {

                throw new ResourceNotFoundException(
                        "Parent already exists with this phone number"
                );
            }

            Parents parent = parentsMapper.toEntity(parentsDto);

            if (parentsDto.getAddress() != null) {

                Address address =
                        addressMapper.toEntity(parentsDto.getAddress());
                parent.setAddress(address);
            }

            Parents savedParent = parentsRepository.save(parent);
            return parentsMapper.toResponse(savedParent);
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to create parent : " + e.getMessage(),
                    e
            );
        }
    }
    @Override
    public List<ParentsResponseDto> getAllParents() {
         List<Parents> allParents = parentsRepository.findAll();

         return  allParents.stream()
                 .map(parentsMapper::toResponse)
                 .toList();
    }

    @Override
    public ParentsResponseDto getParentById(String parentId) {
        Parents parents = parentsRepository.findById(parentId).orElseThrow(
                () -> new ResourceNotFoundException("Parents not found")
        );
        return  parentsMapper.toResponse(parents);
    }

    @Transactional
    @Override
    public ParentsResponseDto updateParent(String parentId, ParentsUpdateDto parentDto) {

        try {

            if (parentDto == null) {
                throw new BadRequestException("Parent update data cannot be null");
            }

            Parents parent = parentsRepository.findById(parentId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Parent not found")
                    );

            if (parentDto.getFatherEmail() != null &&
                    !parentDto.getFatherEmail()
                            .equals(parent.getFatherEmail())) {

                boolean exists = parentsRepository.existsByFatherEmail(
                                parentDto.getFatherEmail()
                        );

                if (exists) {
                    throw new ResourceNotFoundException(
                            "Another parent already exists with this email"
                    );
                }
            }

            if (parentDto.getGuardianPhone1() != null &&
                    !parentDto.getGuardianPhone1()
                            .equals(parent.getGuardianPhone1())) {

                boolean exists =
                        parentsRepository.existsByGuardianPhone1(
                                parentDto.getGuardianPhone1()
                        );

                if (exists) {
                    throw new ResourceNotFoundException(
                            "Another parent already exists with this phone"
                    );
                }
            }

            parentsMapper.updateEntity(parentDto, parent);

            if (parentDto.getAddress() != null &&
                    parent.getAddress() != null) {

                addressMapper.updateEntity(
                        parentDto.getAddress(),
                        parent.getAddress()
                );
            }

            Parents updatedParent = parentsRepository.save(parent);

            return parentsMapper.toResponse(updatedParent);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to update parent : "
                            + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public void deleteParent(String parentId) {
       Parents parents =  parentsRepository.findById(parentId).orElseThrow(
               ()-> new ResourceNotFoundException("Parents not found")
       );
       parentsRepository.delete(parents);
    }

    @Override
    public List<ParentsResponseDto> searchParentsByName(String keyword) {

        try {

            if (keyword == null || keyword.trim().isEmpty()) {
                throw new BadRequestException("Search keyword is required");
            }

            List<Parents> parentsList =
                    parentsRepository
                            .findByFatherFirstnameContainingIgnoreCaseOrFatherLastnameContainingIgnoreCaseOrMotherFirstnameContainingIgnoreCaseOrMotherLastnameContainingIgnoreCase(
                                    keyword,
                                    keyword,
                                    keyword,
                                    keyword
                            );

            return parentsList.stream()
                    .map(parentsMapper::toResponse)
                    .toList();
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to search parents by name : " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public ParentsResponseDto getParentByPhone(String phone) {

        try {

            if (phone == null || phone.trim().isEmpty()) {
                throw new BadRequestException("Phone number is required");
            }

            Parents parent = parentsRepository.findByGuardianPhone1OrGuardianPhone2(phone,phone)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Parent not found with phone")
                    );

            return parentsMapper.toResponse(parent);
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch parent by phone : " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public List<ParentsResponseDto> getParentsWithMoreThanNChildren(int minChildren) {

        try {

            if (minChildren < 0) {
                throw new BadRequestException("Minimum children count cannot be negative");
            }

            List<Parents> parentsList = parentsRepository.findByChildrenGreaterThan(minChildren);

            return parentsList.stream()
                    .map(parentsMapper::toResponse)
                    .toList();
        } catch (Exception e) {

            throw new RuntimeException("Failed to fetch parents by children count");
        }
    }
}
