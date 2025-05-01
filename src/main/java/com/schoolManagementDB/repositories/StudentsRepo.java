package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentsRepo extends JpaRepository<Students,String> {

    // Get student by roll number
    Optional<Students> findByRollNumber(Integer rollNumber);

    // Get students by class ID
    List<Students> findByClasses_ClassId(String classId);

    // Get students by section ID
    List<Students> findBySection_SectionId(String sectionId);

    // Get students by parent ID
    List<Students> findByParent_ParentId(String parentId);

    // Search students by first, middle, or last name containing keyword (case-insensitive)
    List<Students> findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstNameKeyword, String middleNameKeyword, String lastNameKeyword);

    // Get students admitted after a specific date
    List<Students> findByAdmissionDateAfter(LocalDateTime date);
}
