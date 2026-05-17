package com.schoolManagementDB.domain.Attendance.mapper;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceResponseDto;
import com.schoolManagementDB.domain.Attendance.dtos.AttendanceUpdateDto;
import com.schoolManagementDB.domain.Attendance.entity.Attendance;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import org.springframework.stereotype.Component;


@Component
public class AttendanceMapper {

    // =========================
    // CREATE DTO → ENTITY
    // =========================
    public Attendance toEntity(AttendanceDto dto, Student student, Section section, Subject subject) {

        if (dto == null) return null;

        Attendance attendance = new Attendance();

        attendance.setAttendanceDate(dto.getAttendanceDate());
        attendance.setStatus(dto.getStatus());
        attendance.setMarkedBy(dto.getMarkedBy());
        attendance.setRemarks(dto.getRemarks());

        attendance.setStudent(student);
        attendance.setSection(section);
        attendance.setSubject(subject);

        return attendance;
    }

    // =========================
    // UPDATE DTO → ENTITY (PATCH STYLE)
    // =========================
    public void updateEntity(Attendance attendance, AttendanceUpdateDto dto,
                             Student student,
                             Section section,
                             Subject subject) {

        if (dto == null || attendance == null) return;

        if (dto.getAttendanceDate() != null) {
            attendance.setAttendanceDate(dto.getAttendanceDate());
        }

        if (dto.getStatus() != null) {
            attendance.setStatus(dto.getStatus());
        }

        if (dto.getMarkedBy() != null) {
            attendance.setMarkedBy(dto.getMarkedBy());
        }

        if (dto.getRemarks() != null) {
            attendance.setRemarks(dto.getRemarks());
        }

        if (student != null) {
            attendance.setStudent(student);
        }

        if (section != null) {
            attendance.setSection(section);
        }

        if (subject != null) {
            attendance.setSubject(subject);
        }
    }

    // =========================
    // ENTITY → RESPONSE DTO
    // =========================
    public AttendanceResponseDto toResponseDto(Attendance attendance) {

        if (attendance == null) return null;

        AttendanceResponseDto dto = new AttendanceResponseDto();

        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setAttendanceDate(attendance.getAttendanceDate());
        dto.setStatus(attendance.getStatus());
        dto.setMarkedBy(attendance.getMarkedBy());
        dto.setRemarks(attendance.getRemarks());

        if (attendance.getStudent() != null) {
            dto.setStudentId(attendance.getStudent().getStudentId());
            dto.setFirstName(attendance.getStudent().getFirstName());
            dto.setLastName(attendance.getStudent().getLastName());
        }

        if (attendance.getSection() != null) {
            dto.setSectionId(attendance.getSection().getSectionId());
            dto.setSectionName(attendance.getSection().getName());
        }

        if (attendance.getSubject() != null) {
            dto.setSubjectId(attendance.getSubject().getSubjectId());
            dto.setSubjectName(attendance.getSubject().getName());
        }

        dto.setCreateAt(attendance.getCreateAt());
        dto.setUpdatedAt(attendance.getUpdatedAt());

        return dto;
    }


}