package com.schoolManagementDB.domain.Subject.services;

import com.schoolManagementDB.domain.Subject.dtos.SubjectDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectResponseDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectUpdateDto;

import java.util.List;

public interface ISubjectServices {

    SubjectResponseDto createSubject(SubjectDto subjectDto);

    SubjectResponseDto updateSubject(String subjectId, SubjectUpdateDto subjectDto);

    void deleteSubject(String subjectId);

    SubjectResponseDto getSubjectById(String subjectId);

    List<SubjectResponseDto> getAllSubjects();
}
