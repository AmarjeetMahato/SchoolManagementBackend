package com.schoolManagementDB.domain.Parents.repository;

import com.schoolManagementDB.domain.Parents.entity.Parents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface ParentsRepository extends JpaRepository<Parents,String> {
    List<Parents> findByFatherFirstnameContainingIgnoreCaseOrFatherLastnameContainingIgnoreCaseOrMotherFirstnameContainingIgnoreCaseOrMotherLastnameContainingIgnoreCase(String keyword, String keyword1, String keyword2, String keyword3);

    Optional<Parents> findByGuardianPhone1OrGuardianPhone2(
            String guardianPhone1,
            String guardianPhone2
    );

    List<Parents> findByChildrenGreaterThan(int children);

    boolean existsByFatherEmail(String fatherEmail);

    boolean existsByGuardianPhone1(String guardianPhone1);

}
