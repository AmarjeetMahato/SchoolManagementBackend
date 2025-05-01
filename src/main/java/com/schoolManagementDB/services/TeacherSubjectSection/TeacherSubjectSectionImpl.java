package com.schoolManagementDB.services.TeacherSubjectSection;

import com.schoolManagementDB.dtos.TeacherSubjectSectionDto;
import com.schoolManagementDB.entities.Section;
import com.schoolManagementDB.entities.Subject;
import com.schoolManagementDB.entities.Teacher;
import com.schoolManagementDB.entities.Teacher_Subject_Section;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.TeacherSubjectSectionMapper;
import com.schoolManagementDB.repositories.SectionRepo;
import com.schoolManagementDB.repositories.SubjectRepo;
import com.schoolManagementDB.repositories.TeacherRepo;
import com.schoolManagementDB.repositories.TeacherSubjectSectionRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

@Service
@AllArgsConstructor
public class TeacherSubjectSectionImpl implements  TeacherSubjectSection{
    // Use Lombok to generate all-args constructor
    @Qualifier("dbExecutor")
    private final Executor dbExecutor;

    private final TeacherRepo teacherRepository;
    private final SubjectRepo subjectRepository;
    private final SectionRepo sectionRepository;
    private final TeacherSubjectSectionRepo teacherSubjectSectionRepository;

    @Override
    @Transactional
    public Teacher_Subject_Section createAssignment(TeacherSubjectSectionDto dto) {
        try {
            CompletableFuture<Teacher> teacherFuture = CompletableFuture.supplyAsync(() ->
                    teacherRepository.findById(dto.getTeacherId())
                            .orElseThrow(() -> new ResourceNotFoundException
                                    ("Teacher not found with ID: " + dto.getTeacherId())), dbExecutor);

            CompletableFuture<Subject> subjectFuture = CompletableFuture.supplyAsync(() ->
                    subjectRepository.findById(dto.getSubjectId())
                            .orElseThrow(() -> new ResourceNotFoundException
                                    ("Subject not found with ID: " + dto.getSubjectId())), dbExecutor);

            CompletableFuture<Section> sectionFuture = CompletableFuture.supplyAsync(() ->
                    sectionRepository.findById(dto.getSectionId())
                            .orElseThrow(() -> new ResourceNotFoundException
                                    ("Section not found with ID: " + dto.getSectionId())), dbExecutor);

            CompletableFuture.allOf(teacherFuture, subjectFuture, sectionFuture).join();

            Teacher teacher = teacherFuture.join();
            Subject subject = subjectFuture.join();
            Section section = sectionFuture.join();

            Teacher_Subject_Section assignment = TeacherSubjectSectionMapper.toEntity(dto);
            assignment.setTeacher(teacher);
            assignment.setSubject(subject);
            assignment.setSection(section);
            assignment.setAssignedAt(dto.getAssignedAt() != null ? dto.getAssignedAt() : LocalDateTime.now());

            return teacherSubjectSectionRepository.save(assignment);
        } catch (CompletionException e) {
            throw new RuntimeException("Assignment creation failed: " + e.getCause().getMessage(), e.getCause());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during assignment creation: " + e.getMessage(), e);
        }
    }

    @Override
    public Teacher_Subject_Section getAssignmentById(String id) {
        return teacherSubjectSectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + id));
    }

    @Override
    public List<Teacher_Subject_Section> getAllAssignments() {
        return teacherSubjectSectionRepository.findAll();
    }

    @Override
    @Transactional
    public Teacher_Subject_Section updateAssignment(String id, TeacherSubjectSectionDto dto) {
        try {
            // Fetch existing assignment asynchronously
            Teacher_Subject_Section existing = teacherSubjectSectionRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + id));

            // Use threads for concurrent fetching of Teacher, Subject, and Section
            CompletableFuture<Teacher> teacherFuture = CompletableFuture.supplyAsync(() ->
                    teacherRepository.findById(dto.getTeacherId())
                            .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with ID: " + dto.getTeacherId())), dbExecutor);

            CompletableFuture<Subject> subjectFuture = CompletableFuture.supplyAsync(() ->
                    subjectRepository.findById(dto.getSubjectId())
                            .orElseThrow(() -> new ResourceNotFoundException("Subject not found with ID: " + dto.getSubjectId())), dbExecutor);

            CompletableFuture<Section> sectionFuture = CompletableFuture.supplyAsync(() ->
                    sectionRepository.findById(dto.getSectionId())
                            .orElseThrow(() -> new ResourceNotFoundException("Section not found with ID: " + dto.getSectionId())), dbExecutor);

            // Wait for all futures to complete
            CompletableFuture.allOf(teacherFuture, subjectFuture, sectionFuture).join();

            // Retrieve the results after the tasks are completed
            Teacher teacher = teacherFuture.join();
            Subject subject = subjectFuture.join();
            Section section = sectionFuture.join();

            // Update the existing assignment with new values
            existing.setTeacher(teacher);
            existing.setSubject(subject);
            existing.setSection(section);
            existing.setClasses(dto.getClasses());
            existing.setAssignedAt(dto.getAssignedAt() != null ? dto.getAssignedAt() : existing.getAssignedAt());

            // Save and return the updated assignment
            return teacherSubjectSectionRepository.save(existing);

        } catch (CompletionException e) {
            throw new RuntimeException("Error during updating assignment: " + e.getCause().getMessage(), e.getCause());
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error during assignment update: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void deleteAssignment(String id) {
        Teacher_Subject_Section assignment = teacherSubjectSectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment not found with ID: " + id));
        teacherSubjectSectionRepository.delete(assignment);
    }
}
