package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.ExamSchedules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSchedulesRepo extends JpaRepository<ExamSchedules,String> {
}
