package com.schoolManagementDB.services.SubjectService;

import com.schoolManagementDB.dtos.SubjectDto;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.entities.Subject;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.SubjectMapper;
import com.schoolManagementDB.repositories.ClassRepo;
import com.schoolManagementDB.repositories.SubjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService{

    private final SubjectRepo subjectRepo;
    private final ClassRepo classRepo;
    private final SubjectMapper subjectMapper;

    @Override
    public Subject createSubject(SubjectDto subjectDto) {
        if (subjectDto == null || subjectDto.getClassId() == null || subjectDto.getClassId().isBlank()) {
            throw new ResourceNotFoundException("Class ID is required to create a subject");
        }
        try {
            Classes classEntity = classRepo.findById(subjectDto.getClassId())
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + subjectDto.getClassId()));

            Subject subject = subjectMapper.toEntity(subjectDto);
            subject.setClasses(classEntity);

            return subjectRepo.save(subject);
        } catch (InternalServerError e) {
            throw new RuntimeException("Failed to create subject: " + e.getMessage());
        }
    }

    @Override
    public Subject updateSubject(String subjectId, SubjectDto subjectDto) {
        try {
            // Fetch the existing subject
            Subject existingSubject = subjectRepo.findById(subjectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));

            // Fetch the class entity by classId
            Classes classEntity = classRepo.findById(subjectDto.getClassId())
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + subjectDto.getClassId()));

            // Update the subject with the provided DTO fields
            existingSubject.setName(subjectDto.getName());
            existingSubject.setCode(subjectDto.getCode());
            existingSubject.setDescription(subjectDto.getDescription());
            existingSubject.setStatus(subjectDto.getStatus());
            existingSubject.setClasses(classEntity);

            // Set startTime and endTime based on the SubjectDto
            if (subjectDto.getStartTime() != null) {
                existingSubject.setStartTime(subjectDto.getStartTime()); // Assuming the Subject entity has startTime field
            }
            if (subjectDto.getEndTime() != null) {
                existingSubject.setEndTime(subjectDto.getEndTime()); // Assuming the Subject entity has endTime field
            }

            // Save the updated subject
            return subjectRepo.save(existingSubject);

        } catch (Exception e) {
            throw new InternalServerError("Failed to update subject: " + e.getMessage());
        }
    }

    @Override
    public void deleteSubject(String subjectId) {
        try {
            Subject subject = subjectRepo.findById(subjectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));
            subjectRepo.delete(subject);
        } catch (Exception e) {
            throw new InternalServerError("Failed to delete subject: " + e.getMessage());
        }
    }

    @Override
    public Subject getSubjectById(String subjectId) {
        try {
            return subjectRepo.findById(subjectId)
                    .orElseThrow(() -> new ResourceNotFoundException("Subject not found with id: " + subjectId));
        } catch (Exception e) {
            throw new InternalServerError("Failed to fetch subject: " + e.getMessage());
        }
    }

    @Override
    public List<Subject> getAllSubjects() {
        try {
            List<Subject> allSubjects = subjectRepo.findAll();
            if (allSubjects.isEmpty()) {
                throw new ResourceNotFoundException("No subjects found!");
            }
            return allSubjects.stream()
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerError("Failed to fetch all subjects: " + e.getMessage());
        }
    }

}
