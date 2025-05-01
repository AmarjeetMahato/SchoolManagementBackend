package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Attendance_Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Attendance_SummaryRepo  extends JpaRepository<Attendance_Summary,String> {
}
