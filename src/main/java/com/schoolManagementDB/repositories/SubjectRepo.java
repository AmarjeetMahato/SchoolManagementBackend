package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepo extends JpaRepository<Subject,String> {

}
