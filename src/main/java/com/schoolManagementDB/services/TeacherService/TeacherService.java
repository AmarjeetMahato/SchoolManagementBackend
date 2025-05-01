package com.schoolManagementDB.services.TeacherService;

import com.schoolManagementDB.dtos.TeacherDto;
import com.schoolManagementDB.entities.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {

    // Create a teacher
    Teacher createTeacher(TeacherDto teacherDto);

    // Get all teachers
    List<TeacherDto> getAllTeachers();

    // Get teacher by ID
    Teacher getTeacherById(String teacherId);

    // Update teacher details
    Teacher updateTeacher(String teacherId, TeacherDto teacherDto);

    // Delete teacher
    void deleteTeacher(String teacherId);

    // Get teacher by email
    Teacher getTeacherByEmail(String email);

    // Get teacher by phone number
    Teacher getTeacherByPhone(String phone);

    // Get teachers by status (active, inactive, etc.)
    List<TeacherDto> getTeachersByStatus(String status);

    // Search teachers by name (first, middle, or last name)
    List<TeacherDto> searchTeachersByName(String nameKeyword);

    // Get teachers hired after a specific date
    List<TeacherDto> getTeachersHiredAfter(LocalDate date);

    // Update teacher status (active/inactive)
    Teacher updateTeacherStatus(String teacherId, String status);

}
