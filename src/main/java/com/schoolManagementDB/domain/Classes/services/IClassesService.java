package com.schoolManagementDB.domain.Classes.services;

import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.entities.Classes;

import java.util.List;

public interface IClassesService {

    // Create a new class
    Classes createClass(ClassDto dto);

    // Update an existing class
    Classes updateClass(String classId, ClassDto dto);

    // Delete a class by ID
    void deleteClass(String classId);

    // Get class details by ID
    Classes getClassById(String classId);

    // Get all classes
    List<Classes> getAllClasses();

}
