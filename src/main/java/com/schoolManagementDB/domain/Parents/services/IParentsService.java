package com.schoolManagementDB.domain.Parents.services;

import com.schoolManagementDB.domain.Address.dtos.AddressDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsResponseDto;
import com.schoolManagementDB.domain.Parents.dtos.ParentsUpdateDto;

import java.util.List;

public interface IParentsService {

    // -------------------------------------------------------
    // CREATE
    // Input  : ParentsDto
    // Output : ParentsResponseDto
    // -------------------------------------------------------
    ParentsResponseDto createParent(ParentsDto parentsDto);

    // -------------------------------------------------------
    // GET ALL
    // Input  : none
    // Output : List<ParentsResponseDto>
    // -------------------------------------------------------
    List<ParentsResponseDto> getAllParents();

    // -------------------------------------------------------
    // GET BY ID
    // Input  : parentId
    // Output : ParentsResponseDto
    // -------------------------------------------------------
    ParentsResponseDto getParentById(String parentId);

    // -------------------------------------------------------
    // UPDATE
    // Input  : parentId + ParentsUpdateDto
    // Output : ParentsResponseDto
    // -------------------------------------------------------
    ParentsResponseDto updateParent(
            String parentId,
            ParentsUpdateDto parentDto
    );

    // -------------------------------------------------------
    // DELETE
    // Input  : parentId
    // Output : void
    // -------------------------------------------------------
    void deleteParent(String parentId);

    // -------------------------------------------------------
    // SEARCH BY NAME
    // Input  : keyword
    // Output : List<ParentsResponseDto>
    // -------------------------------------------------------
    List<ParentsResponseDto> searchParentsByName(
            String keyword
    );

    // -------------------------------------------------------
    // GET BY PHONE
    // Input  : phone number
    // Output : ParentsResponseDto
    // -------------------------------------------------------
    ParentsResponseDto getParentByPhone(
            String phone
    );

    // -------------------------------------------------------
    // FILTER BY CHILDREN COUNT
    // Input  : minimum children count
    // Output : List<ParentsResponseDto>
    // -------------------------------------------------------
    List<ParentsResponseDto> getParentsWithMoreThanNChildren(
            int minChildren
    );
}