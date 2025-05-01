package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.ExamDuties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamDutiesRepo extends JpaRepository<ExamDuties,String> {
}
