package com.schoolManagementDB.domain.Subject.repository;

import com.schoolManagementDB.domain.Subject.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,String> {

    boolean existsByCodeAndClasses_ClassId(
            String code,
            String classId
    );
}
