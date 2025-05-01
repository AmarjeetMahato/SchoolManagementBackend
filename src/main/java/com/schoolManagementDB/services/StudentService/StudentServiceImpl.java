package com.schoolManagementDB.services.StudentService;

import com.schoolManagementDB.dtos.StudentDto;
import com.schoolManagementDB.entities.Address;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.entities.Section;
import com.schoolManagementDB.entities.Students;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.AddressMapper;
import com.schoolManagementDB.mappers.StudentMapper;
import com.schoolManagementDB.repositories.ClassRepo;
import com.schoolManagementDB.repositories.SectionRepo;
import com.schoolManagementDB.repositories.StudentsRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements  StudentService {

         private  final StudentsRepo studentsRepo;
         private  final SectionRepo sectionRepo;
         private  final ClassRepo classRepo;


    @Transactional
    @Override
    public Students createStudent(StudentDto studentDto) {
        try {
            // 1. Map DTO to Entity
            Students student = StudentMapper.toEntity(studentDto);

            // 2. Handle Address
            if (studentDto.getAddress() != null) {
                Address address = AddressMapper.toEntity(studentDto.getAddress());
                student.setAddress(address);
            }

            Executor executor = Executors.newFixedThreadPool(4);  // A custom thread pool
            CompletableFuture<Classes> classFuture = CompletableFuture.supplyAsync(() -> {
                if (studentDto.getClassId() != null && !studentDto.getClassId().isEmpty()) {
                    return classRepo.findById(studentDto.getClassId())
                            .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
                }
                return null;
            }, executor);
            // 4. Handle Section - Fetch section entity using sectionId asynchronously
            CompletableFuture<Section> sectionFuture = CompletableFuture.supplyAsync(() -> {
                if (studentDto.getSectionId() != null) {
                    return sectionRepo.findById(studentDto.getSectionId())
                            .orElseThrow(() -> new ResourceNotFoundException("Section not found"));
                }
                return null;
            },executor);

            // Wait for both futures to complete
            Classes classes = classFuture.get();  // Blocks and waits for the class fetching task to complete
            Section section = sectionFuture.get();  // Blocks and waits for the section fetching task to complete

            // Set the fetched class and section to the student entity
            if (classes != null) {
                student.setClasses(classes);
            }
            if (section != null) {
                student.setSection(section);
            }

            // 5. Save Student
            return studentsRepo.save(student);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create student: " + e.getMessage(), e);
        }
    }


    @Override
    public Students getStudent(String studentId) {
        return studentsRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + studentId));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        try {
            return studentsRepo.findAll().stream()
                    .map(StudentMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new InternalServerError("Failed to retrieve students");
        }
    }

    @Override
    public Students getStudentByRollNo(int studentRollNo) {
        return studentsRepo.findByRollNumber(studentRollNo)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with Roll No: " + studentRollNo));
    }

    @Override
    public Students updateStudent(String studentId, StudentDto studentDto) {
        Students existingStudent = getStudent(studentId);
        Students updatedStudent = StudentMapper.toEntity(studentDto);
        updatedStudent.setStudentId(existingStudent.getStudentId());

        try {
            return studentsRepo.save(updatedStudent);
        } catch (Exception e) {
            throw new InternalServerError("Failed to update student");
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        Students student = getStudent(studentId);
        studentsRepo.delete(student);
    }

    @Override
    public List<StudentDto> getStudentsByClassId(String classId) {
        return studentsRepo.findByClasses_ClassId(classId).stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsBySectionId(String sectionId) {
        return studentsRepo.findBySection_SectionId(sectionId).stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsByParentId(String parentId) {
        return studentsRepo.findByParent_ParentId(parentId).stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Students updateStudentStatus(String studentId, boolean isActive) {
        Students student = getStudent(studentId);
        student.setActive(isActive);
        return studentsRepo.save(student);
    }

    @Override
    public List<StudentDto> searchStudentsByName(String nameKeyword) {
        return studentsRepo
                .findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        nameKeyword, nameKeyword, nameKeyword)
                .stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getStudentsAdmittedAfter(LocalDateTime date) {
        return studentsRepo.findByAdmissionDateAfter(date).stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }
}
