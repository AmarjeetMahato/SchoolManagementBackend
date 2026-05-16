package com.schoolManagementDB.domain.Address.dtos;


import com.schoolManagementDB.enums.AddressOwnerType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {


    @NotBlank(message = "Address Line 1 is required !!")
    private String addressLine1;

    private String addressLine2;

    @NotBlank(message = "City is required !!")
    private String city;

    @NotBlank(message = "State is required !!")
    private String state;

    @NotBlank(message = "Country is required !!")
    private String country;

    @NotBlank(message = "Postal code is required !!")
    private String postalCode;

    @NotBlank(message = "Address type is required !!")
    private String addressType;

    @NotBlank(message = "Address type is required !!")
    private AddressOwnerType ownerType;

}
