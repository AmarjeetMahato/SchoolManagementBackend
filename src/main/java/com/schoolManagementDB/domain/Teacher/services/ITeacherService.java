package com.schoolManagementDB.domain.Teacher.services;

import com.schoolManagementDB.domain.Teacher.dtos.TeacherDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherResponseDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherUpdateDto;

import java.time.LocalDate;
import java.util.List;

public interface ITeacherService {

    // -------------------------------------------------------
    // CREATE TEACHER
    // Input  : TeacherDto
    // Output : TeacherResponseDto
    // -------------------------------------------------------
    TeacherResponseDto createTeacher(TeacherDto teacherDto);

    // -------------------------------------------------------
    // GET ALL TEACHERS
    // Input  : none
    // Output : List<TeacherResponseDto>
    // -------------------------------------------------------
    List<TeacherResponseDto> getAllTeachers();

    // -------------------------------------------------------
    // GET TEACHER BY ID
    // Input  : teacherId
    // Output : TeacherResponseDto
    // -------------------------------------------------------
    TeacherResponseDto getTeacherById(
            String teacherId
    );

    // -------------------------------------------------------
    // UPDATE TEACHER
    // Input  : teacherId + TeacherUpdateDto
    // Output : TeacherResponseDto
    // -------------------------------------------------------
    TeacherResponseDto updateTeacher(String teacherId, TeacherUpdateDto teacherDto);

    // -------------------------------------------------------
    // DELETE TEACHER
    // Input  : teacherId
    // Output : void
    // -------------------------------------------------------
    void deleteTeacher(
            String teacherId
    );

    // -------------------------------------------------------
    // SEARCH TEACHERS BY NAME
    // Input  : keyword
    // Output : List<TeacherResponseDto>
    // -------------------------------------------------------
    List<TeacherResponseDto> searchTeachersByName(
            String keyword
    );

    // -------------------------------------------------------
    // GET TEACHER BY EMAIL
    // Input  : email
    // Output : TeacherResponseDto
    // -------------------------------------------------------
    TeacherResponseDto getTeacherByEmail(
            String email
    );

    // -------------------------------------------------------
    // GET TEACHER BY PHONE
    // Input  : phone
    // Output : TeacherResponseDto
    // -------------------------------------------------------
    TeacherResponseDto getTeacherByPhone(
            String phone
    );

    // -------------------------------------------------------
    // GET TEACHERS BY STATUS
    // Input  : status
    // Output : List<TeacherResponseDto>
    // -------------------------------------------------------
    List<TeacherResponseDto> getTeachersByStatus(
            String status
    );

    // -------------------------------------------------------
    // GET TEACHERS BY GENDER
    // Input  : gender
    // Output : List<TeacherResponseDto>
    // -------------------------------------------------------
    List<TeacherResponseDto> getTeachersByGender(
            String gender
    );

    // -------------------------------------------------------
    // GET TEACHERS BY QUALIFICATION
    // Input  : qualification
    // Output : List<TeacherResponseDto>
    // -------------------------------------------------------
    List<TeacherResponseDto> getTeachersByQualification(
            String qualification
    );

    // -------------------------------------------------------
    // GET TEACHERS BY CITY
    // Input  : city
    // Output : List<TeacherResponseDto>
    // -------------------------------------------------------
    List<TeacherResponseDto> getTeachersByCity(
            String city
    );

    // -------------------------------------------------------
// GET TEACHERS HIRED AFTER DATE
// -------------------------------------------------------
    List<TeacherResponseDto> getTeachersHiredAfter(LocalDate date);


    // -------------------------------------------------------
// UPDATE TEACHER STATUS
// -------------------------------------------------------
    TeacherResponseDto updateTeacherStatus(
            String teacherId,
            String status
    );
}