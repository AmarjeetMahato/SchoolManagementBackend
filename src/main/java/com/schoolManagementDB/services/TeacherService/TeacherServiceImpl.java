package com.schoolManagementDB.services.TeacherService;

import com.schoolManagementDB.dtos.TeacherDto;
import com.schoolManagementDB.entities.Address;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.entities.Teacher;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceAlreadyExistsException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.TeacherMapper;
import com.schoolManagementDB.repositories.AddressRepo;
import com.schoolManagementDB.repositories.ClassRepo;
import com.schoolManagementDB.repositories.TeacherRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

     private  final TeacherRepo teacherRepo;
     private  final ClassRepo classRepo;
    private final AddressRepo addressRepo; // 💥 add this



    @Transactional
    public Teacher createTeacher(TeacherDto teacherDto) {
        try {
            // 1. Check if teacher already exists by email
            teacherRepo.findByEmail(teacherDto.getEmail())
                    .ifPresent(t -> {
                        throw new ResourceAlreadyExistsException("Teacher already exists with email: " + teacherDto.getEmail());
                    });
            // Convert AddressDto to Address entity and save it first
            Address address = AddressMapper.toEntity(teacherDto.getAddress());
            addressRepo.save(address);  // Save the address first

            // Convert TeacherDto to Teacher entity
            Teacher teacher = TeacherMapper.toEntity(teacherDto);

            // Assign the saved address to the teacher
            teacher.setAddress(address);

            // Fetch Classes from DB using the provided classIds
            List<Classes> classes = classRepo.findAllById(teacherDto.getClassIds());

            if (classes.size() != teacherDto.getClassIds().size()) {
                throw new ResourceNotFoundException("One or more classes not found for the given class IDs");
            }
            // 6. Set teacher inside each class
            for (Classes cls : classes) {
                cls.setTeacher(teacher);
            }
            // 7. Set classes to teacher
            teacher.setClasses(classes);
            // 8. Finally save Teacher
            return teacherRepo.save(teacher);
        } catch (InternalServerError e) {
            throw new RuntimeException("Error creating teacher: " + e.getMessage());
        }
    }


    @Override
    public List<TeacherDto> getAllTeachers() {
        try {
            return teacherRepo.findAll().stream()
                    .map(TeacherMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all teachers: " + e.getMessage());
        }
    }

    @Override
    public Teacher getTeacherById(String teacherId) {
        try {
            return teacherRepo.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching teacher by ID: " + e.getMessage());
        }
    }

    @Override
    public Teacher updateTeacher(String teacherId, TeacherDto teacherDto) {
        try {
            Teacher existingTeacher = teacherRepo.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));

            // Update fields here from teacherDto to existingTeacher
            Teacher updatedTeacher = TeacherMapper.toEntity(teacherDto);
            updatedTeacher.setTeacherId(teacherId);  // Set the ID to ensure it remains unchanged
            return teacherRepo.save(updatedTeacher);
        } catch (Exception e) {
            throw new RuntimeException("Error updating teacher: " + e.getMessage());
        }
    }

    @Override
    public void deleteTeacher(String teacherId) {
        try {
            Teacher teacher = teacherRepo.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));
            teacherRepo.delete(teacher);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting teacher: " + e.getMessage());
        }
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        try {
            return teacherRepo.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with email: " + email));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching teacher by email: " + e.getMessage());
        }
    }

    @Override
    public Teacher getTeacherByPhone(String phone) {
        try {
            return teacherRepo.findByPhone(phone)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with phone: " + phone));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching teacher by phone: " + e.getMessage());
        }
    }

    @Override
    public List<TeacherDto> getTeachersByStatus(String status) {
        try {
            return teacherRepo.findByStatus(status).stream()
                    .map(TeacherMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching teachers by status: " + e.getMessage());
        }
    }

    @Override
    public List<TeacherDto> searchTeachersByName(String nameKeyword) {
        try {
            return teacherRepo.findByFirstNameContainingIgnoreCaseOrMiddleNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(nameKeyword, nameKeyword, nameKeyword).stream()
                    .map(TeacherMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error searching teachers by name: " + e.getMessage());
        }
    }

    @Override
    public List<TeacherDto> getTeachersHiredAfter(LocalDate date) {
        try {
            return teacherRepo.findByHireDateAfter(date).stream()
                    .map(TeacherMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching teachers hired after the specified date: " + e.getMessage());
        }
    }

    @Override
    public Teacher updateTeacherStatus(String teacherId, String status) {
        try {
            Teacher teacher = teacherRepo.findById(teacherId)
                    .orElseThrow(() -> new RuntimeException("Teacher not found with ID: " + teacherId));
            teacher.setStatus(status);
            return teacherRepo.save(teacher);
        } catch (Exception e) {
            throw new RuntimeException("Error updating teacher status: " + e.getMessage());
        }
    }
}
