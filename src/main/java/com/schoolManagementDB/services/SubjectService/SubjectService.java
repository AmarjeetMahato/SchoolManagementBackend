package com.schoolManagementDB.services.SubjectService;

import com.schoolManagementDB.dtos.SubjectDto;
import com.schoolManagementDB.entities.Subject;

import java.util.List;

public interface SubjectService {

    Subject createSubject(SubjectDto subjectDto);

    Subject updateSubject(String subjectId, SubjectDto subjectDto);

    void deleteSubject(String subjectId);

    Subject getSubjectById(String subjectId);

    List<Subject> getAllSubjects();
}
