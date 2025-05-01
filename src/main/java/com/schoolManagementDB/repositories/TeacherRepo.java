package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TeacherRepo extends JpaRepository<Teacher,String> {

    Optional<Teacher> findByEmail(String email);

    Optional<Teacher> findByPhone(String phone);

    List<Teacher> findByStatus(String status);

    List<Teacher> findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String middleName, String lastName);

    List<Teacher> findByHireDateAfter(LocalDate hireDate);
}
