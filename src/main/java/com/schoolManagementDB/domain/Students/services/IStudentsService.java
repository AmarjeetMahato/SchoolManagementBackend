package com.schoolManagementDB.domain.Students.services;

import com.schoolManagementDB.domain.Students.dtos.StudentResponseDto;
import com.schoolManagementDB.domain.Students.dtos.StudentsDtos;

import java.time.LocalDateTime;
import java.util.List;

public interface IStudentsService {

    // Create
    StudentResponseDto createStudent(StudentsDtos studentDto);

    // Read (Single)
    StudentResponseDto getStudent(String studentId);

    // Read (All)
    List<StudentResponseDto> getAllStudents();

    // Get by roll number
    StudentResponseDto getStudentByRollNo(int studentRollNo);

    // Update
    StudentResponseDto updateStudent(String studentId, StudentsDtos  studentDto);

    // Delete
    void deleteStudent(String studentId);

    // Get by Class ID
    List<StudentResponseDto> getStudentsByClassId(String classId);

    // Get by Section ID
    List<StudentResponseDto> getStudentsBySectionId(String sectionId);

    // Get by Parent ID
    List<StudentResponseDto> getStudentsByParentId(String parentId);

    // Update status (active/inactive)
    StudentResponseDto updateStudentStatus(String studentId, boolean isActive);

    // Search by name (optional, for admin)
    List<StudentsDtos> searchStudentsByName(String nameKeyword);

    // Get students admitted after a date
    List<StudentsDtos> getStudentsAdmittedAfter(LocalDateTime date);

}
