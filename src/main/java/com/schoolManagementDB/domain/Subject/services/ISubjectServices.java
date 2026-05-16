package com.schoolManagementDB.domain.Subject.services;

import com.schoolManagementDB.domain.Subject.dtos.SubjectDto;

import java.util.List;

public interface ISubjectServices {

    Subject createSubject(SubjectDto subjectDto);

    Subject updateSubject(String subjectId, SubjectDto subjectDto);

    void deleteSubject(String subjectId);

    Subject getSubjectById(String subjectId);

    List<Subject> getAllSubjects();
}
