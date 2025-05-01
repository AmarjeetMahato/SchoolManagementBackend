package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Teacher_Subject_Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherSubjectSectionRepo extends JpaRepository<Teacher_Subject_Section,String> {
}
