package com.schoolManagementDB.services.ClassService;

import com.schoolManagementDB.dtos.ClassDto;
import com.schoolManagementDB.entities.Classes;

import java.util.List;

public interface ClassService {


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
