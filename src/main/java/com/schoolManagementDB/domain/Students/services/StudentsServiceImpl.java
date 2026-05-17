package com.schoolManagementDB.domain.Students.services;

import com.schoolManagementDB.domain.Address.dtos.AddressResponseDto;
import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.domain.Address.repository.AddressRepository;
import com.schoolManagementDB.domain.Address.services.IAddressService;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Classes.repository.ClassesRepository;
import com.schoolManagementDB.domain.Parents.entity.Parents;
import com.schoolManagementDB.domain.Parents.repository.ParentsRepository;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Section.repository.SectionRepository;
import com.schoolManagementDB.domain.Students.dtos.StudentResponseDto;
import com.schoolManagementDB.domain.Students.dtos.StudentUpdateDto;
import com.schoolManagementDB.domain.Students.dtos.StudentsDtos;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Students.mapper.StudentsMapper;
import com.schoolManagementDB.domain.Students.repository.StudentsRepository;
import com.schoolManagementDB.exceptions.BadRequestException;
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

    private final SectionRepository sectionRepository;
    private  final ClassesRepository classesRepository;
    private  final StudentsRepository studentsRepository;
    private  final ParentsRepository parentsRepository;
    private  final StudentsMapper studentMapper;
    private  final AddressRepository addressRepository;
    private final IAddressService addressService;

    @Transactional
    @Override
    public StudentResponseDto createStudent(StudentsDtos studentDto) {
        try {

            boolean exists = studentsRepository.existsByRollNumberAndClasses_ClassId(
                    studentDto.getRollNumber(),
                    studentDto.getClassId()
            );
            // 1. Map DTO to Entity
            if (exists) {
                throw new ResourceAlreadyExistsException(
                        "Student already exists with roll number"
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

            if (studentDto.getClassId() != null && !studentDto.getClassId().isBlank()) {

                Classes classes = classesRepository.findById(studentDto.getClassId()).orElseThrow(
                        ()-> new ResourceNotFoundException("class not found")
                );

                student.setClasses(classes);
            }

          // Section Handling
            if (studentDto.getSectionId() != null && !studentDto.getSectionId().isBlank()) {
                Section section = sectionRepository.findById(studentDto.getSectionId()).orElseThrow(
                        ()-> new ResourceNotFoundException("class not found")
                );
                student.setSection(section);
            }

          // Parent Handling

            if (studentDto.getParentId() != null && !studentDto.getParentId().isBlank()) {
                Parents parent = parentsRepository.findById(studentDto.getParentId())
                        .orElseThrow(()-> new ResourceNotFoundException("Parents not found"));
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

         try{
              Student student = studentsRepository.findById(studentId).orElseThrow(
                      ()-> new ResourceNotFoundException("Student not found")
              );
              return  studentMapper.toResponse(student);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {

        try {
            List<Student> students = studentsRepository.findAll();
            return students.stream()
                    .map(studentMapper::toResponse)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to fetch students: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public StudentResponseDto getStudentByRollNo(int studentRollNo) {

        try {
            if (studentRollNo <= 0) {
                throw new BadRequestException("Roll number must be greater than 0");
            }

            Student student = studentsRepository
                    .findByRollNumber(studentRollNo)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Student not found")
                    );

            return studentMapper.toResponse(student);
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch student by roll number: "
                            + e.getMessage(),
                    e
            );
        }
    }


    @Override
    public StudentResponseDto updateStudent(String studentId, StudentUpdateDto studentDto) {
        return null;
    }

    @Override
    @Transactional
    public void deleteStudent(String studentId) {

        try {

            if (studentId == null || studentId.isBlank()) {
                throw new BadRequestException("Student ID is required");
            }

            Student student = studentsRepository.findById(studentId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Student not found with id: " + studentId
                            )
                    );

            studentsRepository.delete(student);
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to delete student: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public List<StudentResponseDto> getStudentsByClassId(String classId) {

        try {

            if (classId == null || classId.isBlank()) {
                throw new BadRequestException("Class ID is required");
            }

            List<Student> students =
                    studentsRepository.findByClasses_ClassId(classId);

            return students.stream()
                    .map(studentMapper::toResponse)
                    .toList();

        } catch (BadRequestException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch students by class: " + e.getMessage(),
                    e
            );
        }
    }


    @Override
    public List<StudentResponseDto> getStudentsBySectionId(String sectionId) {

        try {

            if (sectionId == null || sectionId.isBlank()) {
                throw new BadRequestException("Section ID is required");
            }

            List<Student> students =
                    studentsRepository.findBySection_SectionId(sectionId);

            return students.stream()
                    .map(studentMapper::toResponse)
                    .toList();
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch students by section: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public List<StudentResponseDto> getStudentsByParentId(String parentId) {

        try {

            if (parentId == null || parentId.isBlank()) {
                throw new BadRequestException("Parent ID is required");
            }

            List<Student> students =
                    studentsRepository.findByParent_ParentId(parentId);

            return students.stream()
                    .map(studentMapper::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to fetch students by parent: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    @Transactional
    public StudentResponseDto updateStudentStatus(String studentId, boolean isActive) {

        try {

            if (studentId == null || studentId.isBlank()) {
                throw new BadRequestException("Student ID is required");
            }

            Student student = studentsRepository.findById(studentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Student not found")
                    );

            student.setActive(isActive);

            Student updated = studentsRepository.save(student);

            return studentMapper.toResponse(updated);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to update student status: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public List<StudentResponseDto> searchStudentsByName(String nameKeyword) {

        try {

            if (nameKeyword == null || nameKeyword.isBlank()) {
                throw new BadRequestException("Search keyword is required");
            }

            List<Student> students =
                    studentsRepository
                            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                                    nameKeyword,
                                    nameKeyword
                            );

            return students.stream()
                    .map(studentMapper::toResponse)
                    .toList();

        } catch (BadRequestException e) {
            throw e;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to search students: " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public List<StudentResponseDto> getStudentsAdmittedAfter(LocalDateTime date) {

        try {
            if (date == null) {
                throw new BadRequestException("Date is required");
            }

            List<Student> students = studentsRepository.findByAdmissionDateAfter(date);

            return students.stream()
                    .map(studentMapper::toResponse)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to fetch students by admission date: " + e.getMessage(),
                    e
            );
        }
    }
}
