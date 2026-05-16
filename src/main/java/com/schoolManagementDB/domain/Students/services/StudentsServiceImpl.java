package com.schoolManagementDB.domain.Students.services;

import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.domain.Address.repository.AddressRepository;
import com.schoolManagementDB.domain.Parents.entity.Parents;
import com.schoolManagementDB.domain.Students.dtos.StudentResponseDto;
import com.schoolManagementDB.domain.Students.dtos.StudentsDtos;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Students.mapper.StudentsMapper;
import com.schoolManagementDB.domain.Students.repository.StudentsRepository;
import com.schoolManagementDB.exceptions.ResourceAlreadyExistsException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements  IStudentsService {

    private  final StudentsRepository studentsRepository;
    private  final StudentsMapper studentMapper;
    private  final AddressRepository addressRepository;

    @Transactional
    @Override
    public StudentResponseDto createStudent(StudentsDtos studentDto) {
        try {

            // DuplicateCheck
            // Duplicate Check
            boolean exists = studentsRepository.existsByRollNumberAndClasses_ClassId(
                    studentDto.getRollNumber(),
                    studentDto.getClassId()
            );

            // 1. Map DTO to Entity
            if (exists) {
                throw new ResourceAlreadyExistsException(
                        "Student already exists with roll number: "
                                + studentDto.getRollNumber()
                );
            }

            // 1. DTO -> Entity
            Student student = studentMapper.toEntity(studentDto);


            // 2. Handle Address
            if (studentDto.getAddress() != null) {

                AddressResponseDto savedAddress =
                        addressService.createAddress(studentDto.getAddress());

                Address address = addressRepository.findById(savedAddress.getAddressId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Address not found"));

                student.setAddress(address);
            }

             /*
         |--------------------------------------------------------------------------
         | Class Handling
         |--------------------------------------------------------------------------
         */
            if (studentDto.getClassId() != null &&
                    !studentDto.getClassId().isBlank()) {

                Classes classes = classService.getClassEntityById(
                        studentDto.getClassId()
                );

                student.setClasses(classes);
            }

             /*
         |--------------------------------------------------------------------------
         | Section Handling
         |--------------------------------------------------------------------------
         */
            if (studentDto.getSectionId() != null &&
                    !studentDto.getSectionId().isBlank()) {

                Section section = sectionService.getSectionEntityById(
                        studentDto.getSectionId()
                );

                student.setSection(section);
            }

        /*
         |--------------------------------------------------------------------------
         | Parent Handling
         |--------------------------------------------------------------------------
         */
            if (studentDto.getParentId() != null &&
                    !studentDto.getParentId().isBlank()) {

                Parents parent = parentService.getParentEntityById(
                        studentDto.getParentId()
                );

                student.setParent(parent);
            }

            // Save Student
            Student savedStudent = studentsRepository.save(student);

            // Response
            return studentMapper.toResponse(savedStudent);

        } catch (Exception e) {
            throw new RuntimeException("Failed to create student: " + e.getMessage(), e);
        }
    }

    @Override
    public StudentResponseDto getStudent(String studentId) {
        return null;
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return List.of();
    }

    @Override
    public StudentResponseDto getStudentByRollNo(int studentRollNo) {
        return null;
    }

    @Override
    public StudentResponseDto updateStudent(String studentId, StudentsDtos studentDto) {
        return null;
    }

    @Override
    public void deleteStudent(String studentId) {

    }

    @Override
    public List<StudentResponseDto> getStudentsByClassId(String classId) {
        return List.of();
    }

    @Override
    public List<StudentResponseDto> getStudentsBySectionId(String sectionId) {
        return List.of();
    }

    @Override
    public List<StudentResponseDto> getStudentsByParentId(String parentId) {
        return List.of();
    }

    @Override
    public StudentResponseDto updateStudentStatus(String studentId, boolean isActive) {
        return null;
    }

    @Override
    public List<StudentsDtos> searchStudentsByName(String nameKeyword) {
        return List.of();
    }

    @Override
    public List<StudentsDtos> getStudentsAdmittedAfter(LocalDateTime date) {
        return List.of();
    }
}
