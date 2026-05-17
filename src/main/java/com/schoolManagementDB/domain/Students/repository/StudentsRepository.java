package com.schoolManagementDB.domain.Students.repository;

import com.schoolManagementDB.domain.Students.entity.Student;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Student,String> {
    boolean existsByRollNumberAndClasses_ClassId(
            @NotNull(message = "Roll number is required") int rollNumber, String classId);

    Optional<Student> findByRollNumber(int rollNumber);

    List<Student> findByClasses_ClassId(String classId);

    List<Student> findBySection_SectionId(String sectionId);

    List<Student> findByParent_ParentId(String parentId);

    List<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );

    List<Student> findByAdmissionDateAfter(LocalDateTime date);
}
