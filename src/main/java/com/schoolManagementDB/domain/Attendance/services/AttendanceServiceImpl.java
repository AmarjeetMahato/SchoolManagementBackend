package com.schoolManagementDB.domain.Attendance.services;


import com.schoolManagementDB.domain.Attendance.dtos.AttendanceDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceResponseDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceUpdateDto;
import com.schoolManagementDB.domain.Attendance.entity.Attendance;
import com.schoolManagementDB.domain.Attendance.mapper.AttendanceMapper;
import com.schoolManagementDB.domain.Attendance.repository.AttendanceRepository;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Section.repository.SectionRepository;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Students.repository.StudentsRepository;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.domain.Subject.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements  IAttendanceService {

    private  final AttendanceRepository attendanceRepository;
    private  final SectionRepository sectionRepository;
    private  final StudentsRepository studentsRepository;
    private  final SubjectRepository subjectRepository;
    private  final AttendanceMapper attendanceMapper;

    @Transactional
    @Override
    public AttendanceResponseDto createAttendance(AttendanceDto dto) {

        // 1. Fetch related entities
        Student student = studentsRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));

        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new RuntimeException("Section not found with id: " + dto.getSectionId()));

        Subject subject = subjectRepository.findById(dto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + dto.getSubjectId()));

        // 2. Map DTO → Entity
        Attendance attendance = attendanceMapper.toEntity(dto, student, section, subject);

        // 3. Save entity
        Attendance savedAttendance = attendanceRepository.save(attendance);

        // 4. Convert Entity → Response DTO
        return attendanceMapper.toResponseDto(savedAttendance);
    }

    @Transactional
    @Override
    public AttendanceResponseDto updateAttendance(String attendanceId, AttendanceUpdateDto dto) {

        // 1. Fetch existing attendance
        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException("Attendance not found with id: " + attendanceId));

        // 2. Fetch related entities only if IDs are provided
        Student student = null;
        Section section = null;
        Subject subject = null;

        if (dto.getStudentId() != null) {
            student = studentsRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found with id: " + dto.getStudentId()));
        }

        if (dto.getSectionId() != null) {
            section = sectionRepository.findById(dto.getSectionId())
                    .orElseThrow(() -> new RuntimeException("Section not found with id: " + dto.getSectionId()));
        }

        if (dto.getSubjectId() != null) {
            subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found with id: " + dto.getSubjectId()));
        }

        // 3. Patch update using mapper
        attendanceMapper.updateEntity(attendance, dto, student, section, subject);

        // 4. Save updated entity
        Attendance updatedAttendance = attendanceRepository.save(attendance);

        // 5. Convert to Response DTO
        return attendanceMapper.toResponseDto(updatedAttendance);
    }

    @Override
    public AttendanceResponseDto getAttendanceById(String attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException(
                        "Attendance not found with id: " + attendanceId));

        return attendanceMapper.toResponseDto(attendance);
    }

    @Override
    public void deleteAttendance(String attendanceId) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException(
                        "Attendance not found with id: " + attendanceId));

        attendanceRepository.delete(attendance);
    }


    @Override
    public List<AttendanceResponseDto> getAllAttendance() {

        List<Attendance> attendances = attendanceRepository.findAll();

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }


    @Override
    public List<AttendanceResponseDto> getAttendanceByStudentId(String studentId) {

        List<Attendance> attendances = attendanceRepository.findByStudent_StudentId(studentId);

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceBySectionId(String sectionId) {

        List<Attendance> attendances = attendanceRepository.findBySection_SectionId(sectionId);

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }


    @Override
    public List<AttendanceResponseDto> getAttendanceBySubjectId(String subjectId) {

        List<Attendance> attendances = attendanceRepository.findBySubject_SubjectId(subjectId);

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceByDate(LocalDate date) {

        List<Attendance> attendances = attendanceRepository.findByAttendanceDate(date);

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<AttendanceResponseDto> getAttendanceBetweenDates(LocalDate startDate, LocalDate endDate) {

        List<Attendance> attendances =
                attendanceRepository.findByAttendanceDateBetween(startDate, endDate);

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }


    @Override
    public AttendanceResponseDto updateAttendanceStatus(String attendanceId, String status) {

        Attendance attendance = attendanceRepository.findById(attendanceId)
                .orElseThrow(() -> new RuntimeException(
                        "Attendance not found with id: " + attendanceId));

        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be empty");
        }

        if (!status.matches("PRESENT|ABSENT|LATE")) {
            throw new IllegalArgumentException("Status must be PRESENT, ABSENT, or LATE");
        }

        attendance.setStatus(status);

        Attendance updated = attendanceRepository.save(attendance);

        return attendanceMapper.toResponseDto(updated);
    }

    @Override
    public List<AttendanceResponseDto> searchAttendanceByStudentName(String keyword, Pageable pageable) {

        Page<Attendance> attendances =
                attendanceRepository.findByStudent_NameContainingIgnoreCase(keyword, pageable);

        return attendances.stream()
                .map(attendanceMapper::toResponseDto)
                .toList();
    }
}
