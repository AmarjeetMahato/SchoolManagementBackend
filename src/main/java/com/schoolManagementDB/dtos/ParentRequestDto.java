package com.schoolManagementDB.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentRequestDto {

    private ParentDto parentDto;
    private AddressDto addressDto;
    private List<String> studentIds;
}
