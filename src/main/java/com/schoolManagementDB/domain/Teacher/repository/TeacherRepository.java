package com.schoolManagementDB.domain.Teacher.repository;


import com.schoolManagementDB.domain.Teacher.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,String> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByPhone(String phone);

    List<Teacher> findByStatusIgnoreCase(String status);

    List<Teacher> findByGenderIgnoreCase(String gender);

    List<Teacher> findByQualificationContainingIgnoreCase(String qualification);

    List<Teacher> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );

    List<Teacher> findByAddress_CityIgnoreCase(String city);

    List<Teacher> findByHireDateAfter(LocalDate hireDate);
}
