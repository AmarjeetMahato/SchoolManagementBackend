package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address,String> {


    // Find all addresses by city
    List<Address> findByCityIgnoreCase(String city);

    // Find all addresses by state
    List<Address> findByStateIgnoreCase(String state);

    // Find all addresses by postal code (corrected)
    List<Address> findByPostalCode(String postalCode);

    // Find by address type (corrected)
    List<Address> findByAddressTypeIgnoreCase(String addressType);

    // Optional: find all addresses linked with at least one parent, student, or teacher
    List<Address> findByParentsIsNotEmpty();

    List<Address> findByStudentsIsNotEmpty();

    List<Address> findByTeachersIsNotEmpty();
}
