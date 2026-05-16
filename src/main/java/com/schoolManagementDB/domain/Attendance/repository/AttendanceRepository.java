package com.schoolManagementDB.domain.Attendance.repository;

import com.schoolManagementDB.domain.Attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,String> {
}
