package com.schoolManagementDB.services.StudentService;

import com.schoolManagementDB.dtos.StudentDto;
import com.schoolManagementDB.entities.Students;

import java.time.LocalDateTime;
import java.util.List;

public interface StudentService {

    // Create
    Students createStudent(StudentDto studentDto);

    // Read (Single)
    Students getStudent(String studentId);

    // Read (All)
    List<StudentDto> getAllStudents();

    // Get by roll number
    Students getStudentByRollNo(int studentRollNo);

    // Update
    Students updateStudent(String studentId, StudentDto studentDto);

    // Delete
    void deleteStudent(String studentId);

    // Get by Class ID
    List<StudentDto> getStudentsByClassId(String classId);

    // Get by Section ID
    List<StudentDto> getStudentsBySectionId(String sectionId);

    // Get by Parent ID
    List<StudentDto> getStudentsByParentId(String parentId);

    // Update status (active/inactive)
    Students updateStudentStatus(String studentId, boolean isActive);

    // Search by name (optional, for admin)
    List<StudentDto> searchStudentsByName(String nameKeyword);

    // Get students admitted after a date
    List<StudentDto> getStudentsAdmittedAfter(LocalDateTime date);

}

