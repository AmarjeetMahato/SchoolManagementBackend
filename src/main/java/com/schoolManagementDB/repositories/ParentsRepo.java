package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParentsRepo extends JpaRepository<Parents,String> {

    @Query("SELECT p FROM Parents p WHERE LOWER(p.fatherFirstname) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(p.motherFirstname) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Parents> searchByParentName(@Param("name") String name);

    Optional<Parents> findByGuardianPhone1OrGuardianPhone2(String phone1, String phone2);

    List<Parents> findByChildrenGreaterThan(int children);

    Optional<Parents> findByFatherEmail(String fatherEmail);

    Optional<Parents> findByGuardianPhone1(String guardianPhone1);

}
