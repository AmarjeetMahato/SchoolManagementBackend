package com.schoolManagementDB.domain.Classes.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDto {
    @NotBlank(message = "Class name is required !!")
    private String name;

    @NotBlank(message = "Class code is required !!")
    private String code;

    private String description;

    @NotBlank(message = "Status is required !!")
    private String status;

}
