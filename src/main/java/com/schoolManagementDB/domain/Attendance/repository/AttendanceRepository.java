package com.schoolManagementDB.domain.Attendance.repository;

import com.schoolManagementDB.domain.Attendance.entity.Attendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,String> {

    List<Attendance> findByStudent_StudentId(String studentId);

    List<Attendance> findBySection_SectionId(String sectionId);

    List<Attendance> findBySubject_SubjectId(String subjectId);

    List<Attendance> findByAttendanceDate(LocalDate date);

    List<Attendance> findByAttendanceDateBetween(LocalDate startDate, LocalDate endDate);

    Page<Attendance> findByStudent_NameContainingIgnoreCase(String keyword, Pageable pageable);
}
