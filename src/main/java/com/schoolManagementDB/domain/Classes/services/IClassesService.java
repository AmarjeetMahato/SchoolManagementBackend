package com.schoolManagementDB.domain.Classes.services;

import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassResponseDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassUpdateDto;

import java.util.List;

public interface IClassesService {

    // Create a new class
    ClassResponseDto createClass(ClassDto dto);

    // Update an existing class
    ClassResponseDto updateClass(String classId, ClassUpdateDto dto);

    // Delete a class by ID
    void deleteClass(String classId);

    // Get class details by ID
    ClassResponseDto getClassById(String classId);

    // Get all classes
    List<ClassResponseDto> getAllClasses();

}
