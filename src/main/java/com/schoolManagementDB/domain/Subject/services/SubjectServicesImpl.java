package com.schoolManagementDB.domain.Subject.services;

import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Classes.repository.ClassesRepository;
import com.schoolManagementDB.domain.Subject.dtos.SubjectDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectResponseDto;
import com.schoolManagementDB.domain.Subject.dtos.SubjectUpdateDto;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.domain.Subject.mapper.SubjectMapper;
import com.schoolManagementDB.domain.Subject.repository.SubjectRepository;
import com.schoolManagementDB.exceptions.BadRequestException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor

public class SubjectServicesImpl implements ISubjectServices{

    private final SubjectRepository subjectRepository;
    private  final ClassesRepository classesRepository;
    private  final SubjectMapper subjectMapper;


    @Transactional
    @Override
    public SubjectResponseDto createSubject(SubjectDto subjectDto) {

        try {
            if (subjectDto == null) {
                throw new BadRequestException("Subject data cannot be null");
            }

            boolean exists = subjectRepository
                    .existsByCodeAndClasses_ClassId(
                            subjectDto.getCode(),
                            subjectDto.getClassId()
                    );

            if (exists) {
                throw new ResourceNotFoundException(
                        "Subject already exists with this code in the class"
                );
            }

            Classes classes = classesRepository.findById(subjectDto.getClassId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Class not found")
                    );

            Subject subject = subjectMapper.toEntity(subjectDto, classes);

            Subject savedSubject = subjectRepository.save(subject);

            return subjectMapper.toResponse(savedSubject);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to create subject : " + e.getMessage(),
                    e
            );
        }
    }

    @Transactional
    @Override
    public SubjectResponseDto updateSubject(String subjectId, SubjectUpdateDto subjectDto
    ) {

        try {

            if (subjectDto == null) {
                throw new BadRequestException("Subject update data cannot be null");
            }

            Subject subject = subjectRepository.findById(subjectId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Subject not found ")
                    );

            Classes classes = null;

            if (subjectDto.getClassId() != null &&
                    !subjectDto.getClassId().isBlank()) {

                classes = classesRepository.findById(subjectDto.getClassId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Class not found ")
                        );
            }

            if (subjectDto.getCode() != null &&
                    !subjectDto.getCode().equals(subject.getCode())) {

                String classId = classes != null
                        ? classes.getClassId()
                        : subject.getClasses().getClassId();

                boolean exists = subjectRepository
                        .existsByCodeAndClasses_ClassId(
                                subjectDto.getCode(),
                                classId
                        );

                if (exists) {
                    throw new ResourceNotFoundException(
                            "Subject code already exists in this class"
                    );
                }
            }

            subjectMapper.updateEntity(subjectDto, subject, classes);

            Subject updatedSubject = subjectRepository.save(subject);

            return subjectMapper.toResponse(updatedSubject);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to update subject : " + e.getMessage(),
                    e
            );
        }
    }
    @Override
    public void deleteSubject(String subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
                ()-> new ResourceNotFoundException("Subject not found")
        );
        subjectRepository.delete(subject);
    }

    @Override
    public SubjectResponseDto getSubjectById(String subjectId) {

        Subject subject = subjectRepository.findById(subjectId).orElseThrow(
                ()-> new ResourceNotFoundException("Subject not found")
        );
        return  subjectMapper.toResponse(subject);
    }

    @Override
    public List<SubjectResponseDto> getAllSubjects() {

        List<Subject> subjectList = subjectRepository.findAll();

        return  subjectList.stream()
                .map(subjectMapper::toResponse)
                .toList();
    }
}
