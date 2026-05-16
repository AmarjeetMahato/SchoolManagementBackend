package com.schoolManagementDB.domain.Address.dtos;


import com.schoolManagementDB.domain.Address.enums.AddressOwnerType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressUpdateDto {

    @Size(min = 3, max = 200, message = "Address line 1 must be between 3 and 200 characters")
    private String addressLine1;

    @Size(max = 200, message = "Address line 2 must be max 200 characters")
    private String addressLine2;

    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    private String city;

    @Size(min = 2, max = 100, message = "State must be between 2 and 100 characters")
    private String state;

    @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
    private String country;

    private AddressOwnerType ownerType;

    @Pattern(regexp = "^[0-9]{4,10}$", message = "Postal code must be numeric and 4-10 digits")
    private String postalCode;

    @Size(min = 2, max = 50, message = "Address type must be between 2 and 50 characters")
    private String addressType;
}