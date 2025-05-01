package com.schoolManagementDB.services.TeacherSubjectSection;

import com.schoolManagementDB.dtos.TeacherSubjectSectionDto;
import com.schoolManagementDB.entities.Teacher_Subject_Section;

import java.util.List;

public interface TeacherSubjectSection {
    Teacher_Subject_Section createAssignment(TeacherSubjectSectionDto dto);
    Teacher_Subject_Section getAssignmentById(String id);
    List<Teacher_Subject_Section> getAllAssignments();
    Teacher_Subject_Section updateAssignment(String id, TeacherSubjectSectionDto dto);
    void deleteAssignment(String id);
}
