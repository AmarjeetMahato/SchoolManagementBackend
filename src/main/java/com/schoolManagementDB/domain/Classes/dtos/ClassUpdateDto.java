package com.schoolManagementDB.domain.Classes.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassUpdateDto {

    @Size(min = 2, max = 100, message = "Class name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 20, message = "Class code must be between 2 and 20 characters")
    private String code;

    @Size(max = 500, message = "Description must be max 500 characters")
    private String description;

    @Size(min = 2, max = 20, message = "Status must be between 2 and 20 characters")
    private String status;
}