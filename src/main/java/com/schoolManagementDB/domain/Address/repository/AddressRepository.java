package com.schoolManagementDB.domain.Address.repository;

import com.schoolManagementDB.domain.Address.entity.Address;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByPostalCode(String postalCode);

    List<Address>  findByStateIgnoreCase(String state);

    boolean existsByAddressLine1AndCityAndStateAndPostalCode(@NotBlank(message = "address line 1 is required !!") String addressLine1, @NotBlank(message = "city is required !!") String city, @NotBlank(message = "state is required !!") String state, @NotBlank(message = "postal code is required !!") String postalCode);

    List<Address>  findByCityIgnoreCase(String city);
}
