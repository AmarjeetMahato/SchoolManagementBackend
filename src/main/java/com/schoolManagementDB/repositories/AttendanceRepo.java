package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepo extends JpaRepository<Attendance,String> {
}
