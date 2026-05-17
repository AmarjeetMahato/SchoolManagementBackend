package com.schoolManagementDB.domain.Teacher.services;


import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.domain.Address.mapper.AddressMapper;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherResponseDto;
import com.schoolManagementDB.domain.Teacher.dtos.TeacherUpdateDto;
import com.schoolManagementDB.domain.Teacher.entity.Teacher;
import com.schoolManagementDB.domain.Teacher.mapper.TeacherMapper;
import com.schoolManagementDB.domain.Teacher.repository.TeacherRepository;
import com.schoolManagementDB.exceptions.BadRequestException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements ITeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final AddressMapper addressMapper;

    @Transactional
    @Override
    public TeacherResponseDto createTeacher(TeacherDto teacherDto) {
          try{
              if (teacherDto == null) {
                  throw new BadRequestException("Teacher data cannot be null");
              }

              // Duplicate Email Check
              if (teacherRepository.existsByEmail(teacherDto.getEmail())) {
                  throw new ResourceNotFoundException(
                          "Teacher already exists with email : "
                                  + teacherDto.getEmail()
                  );
              }

              // Duplicate Phone Check
              if (teacherDto.getPhone() != null &&
                      teacherRepository.existsByPhone(teacherDto.getPhone())) {

                  throw new ResourceNotFoundException(
                          "Teacher already exists with phone : "
                                  + teacherDto.getPhone()
                  );
              }


              // DTO -> Entity
              Teacher teacher = teacherMapper.toEntity(teacherDto);

              // Handle Address
              if (teacherDto.getAddress() != null) {

                  Address address = addressMapper.toEntity(
                          teacherDto.getAddress()
                  );

                  teacher.setAddress(address);
              }

              Teacher savedTeacher = teacherRepository.save(teacher);

              return teacherMapper.toResponse(savedTeacher);
          } catch (Exception e) {

              throw new RuntimeException(
                      "Failed to create teacher : " + e.getMessage(),
                      e
              );
          }
    }

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        try {

            List<Teacher> teachers = teacherRepository.findAll();

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teachers : " + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public TeacherResponseDto getTeacherById(String teacherId) {
        try {

            if (teacherId == null || teacherId.isBlank()) {
                throw new BadRequestException("Teacher ID is required");
            }

            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Teacher not found ")
                    );

            return teacherMapper.toResponse(teacher);
        }catch (Exception e) {
                throw new RuntimeException(
                        "Failed to fetch teacher : " + e.getMessage(),
                        e
                );
            }
    }

    @Transactional
    @Override
    public TeacherResponseDto updateTeacher(String teacherId, TeacherUpdateDto teacherDto) {
        try {

            if (teacherDto == null) {
                throw new BadRequestException("Teacher update data cannot be null");
            }

            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Teacher not found")
                    );

            // Email Duplicate Check
            if (teacherDto.getEmail() != null &&
                    !teacherDto.getEmail().equals(teacher.getEmail())) {

                boolean exists = teacherRepository.existsByEmail(
                        teacherDto.getEmail()
                );

                if (exists) {
                    throw new ResourceNotFoundException(
                            "Another teacher already exists with this email"
                    );
                }
            }

            // Phone Duplicate Check
            if (teacherDto.getPhone() != null &&
                    !teacherDto.getPhone().equals(teacher.getPhone())) {

                boolean exists = teacherRepository.existsByPhone(
                        teacherDto.getPhone()
                );

                if (exists) {
                    throw new ResourceNotFoundException(
                            "Another teacher already exists with this phone"
                    );
                }
            }

            // Update Teacher
            teacherMapper.updateEntity(teacherDto, teacher);

            // Update Address
            if (teacherDto.getAddress() != null &&
                    teacher.getAddress() != null) {

                addressMapper.updateEntity(teacherDto.getAddress(), teacher.getAddress());
            }

            Teacher updatedTeacher = teacherRepository.save(teacher);

            return teacherMapper.toResponse(updatedTeacher);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to update teacher : " + e.getMessage(),
                    e
            );
        }
        }

    @Transactional
    @Override
    public void deleteTeacher(String teacherId) {
        try {

            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

            teacherRepository.delete(teacher);

        }catch (Exception e) {

            throw new RuntimeException(
                    "Failed to delete teacher : " + e.getMessage(),
                    e
            );
        }
    }

    // -------------------------------------------------------
// SEARCH TEACHERS BY NAME
// -------------------------------------------------------
    @Override
    public List<TeacherResponseDto> searchTeachersByName(String keyword) {

        try {
            if (keyword == null || keyword.isBlank()) {
                throw new BadRequestException("Search keyword is required");
            }

            List<Teacher> teachers = teacherRepository
                            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                                    keyword,
                                    keyword
                            );

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to search teachers : "
                            + e.getMessage(),
                    e
            );
        }
    }

    // -------------------------------------------------------
// GET TEACHER BY EMAIL
// -------------------------------------------------------
    @Override
    public TeacherResponseDto getTeacherByEmail(String email) {

        try {

            if (email == null || email.isBlank()) {
                throw new BadRequestException("Teacher email is required");
            }

            Teacher teacher = teacherRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with email")
                    );

            return teacherMapper.toResponse(teacher);
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teacher by email : "
                            + e.getMessage(),
                    e
            );
        }
    }


    // -------------------------------------------------------
// GET TEACHER BY PHONE
// -------------------------------------------------------
    @Override
    public TeacherResponseDto getTeacherByPhone(String phone) {

        try {
            if (phone == null || phone.isBlank()) {
                throw new BadRequestException("Teacher phone is required");
            }
            Teacher teacher = teacherRepository
                    .findByPhone(phone)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Teacher not found with phone")
                    );

            return teacherMapper.toResponse(teacher);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teacher by phone : "
                            + e.getMessage(),
                    e
            );
        }
    }


    // -------------------------------------------------------
// GET TEACHERS BY STATUS
// -------------------------------------------------------
    @Override
    public List<TeacherResponseDto> getTeachersByStatus(String status) {

        try {

            if (status == null || status.isBlank()) {
                throw new BadRequestException(
                        "Teacher status is required"
                );
            }

            List<Teacher> teachers =
                    teacherRepository
                            .findByStatusIgnoreCase(status);

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();

        } catch (BadRequestException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teachers by status : "
                            + e.getMessage(),
                    e
            );
        }
    }

    // -------------------------------------------------------
// GET TEACHERS BY GENDER
// -------------------------------------------------------
    @Override
    public List<TeacherResponseDto> getTeachersByGender(String gender) {

        try {

            if (gender == null || gender.isBlank()) {
                throw new BadRequestException(
                        "Teacher gender is required"
                );
            }

            List<Teacher> teachers =
                    teacherRepository
                            .findByGenderIgnoreCase(gender);

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();

        } catch (BadRequestException e) {

            throw e;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teachers by gender : "
                            + e.getMessage(),
                    e
            );
        }
    }


    // -------------------------------------------------------
// GET TEACHERS BY QUALIFICATION
// -------------------------------------------------------
    @Override
    public List<TeacherResponseDto> getTeachersByQualification(String qualification) {

        try {

            if (qualification == null || qualification.isBlank()) {
                throw new BadRequestException("Qualification is required");
            }

            List<Teacher> teachers =
                    teacherRepository
                            .findByQualificationContainingIgnoreCase(
                                    qualification
                            );

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teachers by qualification : "
                            + e.getMessage(),
                    e
            );
        }
    }


    // -------------------------------------------------------
// GET TEACHERS BY CITY
// -------------------------------------------------------
    @Override
    public List<TeacherResponseDto> getTeachersByCity(String city) {

        try {

            if (city == null || city.isBlank()) {
                throw new BadRequestException(
                        "City is required"
                );
            }

            List<Teacher> teachers =
                    teacherRepository.findByAddress_CityIgnoreCase(city);

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teachers by city : "
                            + e.getMessage(),
                    e
            );
        }
    }

    // -------------------------------------------------------
// GET TEACHERS HIRED AFTER DATE
// -------------------------------------------------------
    @Override
    public List<TeacherResponseDto> getTeachersHiredAfter(LocalDate date) {

        try {

            if (date == null) {
                throw new BadRequestException("Hire date is required");
            }

            List<Teacher> teachers = teacherRepository.findByHireDateAfter(date);

            return teachers.stream()
                    .map(teacherMapper::toResponse)
                    .toList();
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to fetch teachers hired after date : "
                            + e.getMessage(),
                    e
            );
        }
    }

    // -------------------------------------------------------
// UPDATE TEACHER STATUS
// -------------------------------------------------------
    @Transactional
    @Override
    public TeacherResponseDto updateTeacherStatus(String teacherId, String status) {

        try {

            if (teacherId == null || teacherId.isBlank()) {
                throw new BadRequestException("Teacher ID is required");
            }

            if (status == null || status.isBlank()) {
                throw new BadRequestException(
                        "Teacher status is required"
                );
            }

            String upperStatus = status.toUpperCase();

            if (!upperStatus.equals("ACTIVE") &&
                    !upperStatus.equals("INACTIVE")) {

                throw new BadRequestException(
                        "Status must be ACTIVE or INACTIVE"
                );
            }

            Teacher teacher = teacherRepository.findById(teacherId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Teacher not found ")
                    );

            teacher.setStatus(upperStatus);

            Teacher updatedTeacher = teacherRepository.save(teacher);

            return teacherMapper.toResponse(updatedTeacher);
        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to update teacher status : "
                            + e.getMessage(),
                    e
            );
        }
    }
}