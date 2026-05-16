package com.schoolManagementDB.services.ParentService;

import com.schoolManagementDB.dtos.AddressDto;
import com.schoolManagementDB.dtos.ParentDto;
import com.schoolManagementDB.entities.Address;
import com.schoolManagementDB.entities.Parents;
import com.schoolManagementDB.entities.Students;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceAlreadyExistsException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.ParentMapper;
import com.schoolManagementDB.repositories.AddressRepo;
import com.schoolManagementDB.repositories.ParentsRepo;
import com.schoolManagementDB.repositories.StudentsRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {


    private final ParentsRepo parentsRepo;
    private final AddressRepo addressRepo;
    private  final StudentsRepo studentsRepo;


    @Transactional
    public Parents createParent(ParentDto parentDto, AddressDto addressDto, List<String> studentIds) {
        try {
            // 1. Check if email already exists (only if fatherEmail is provided)
            if (parentDto.getFatherEmail() != null && !parentDto.getFatherEmail().isEmpty()) {
                parentsRepo.findByFatherEmail(parentDto.getFatherEmail())
                        .ifPresent(p -> {
                            throw new ResourceAlreadyExistsException("Parent already exists with email: " + parentDto.getFatherEmail());
                        });
            }

            // 2. Check if guardianPhone1 already exists
            if (parentDto.getGuardianPhone1() != null && !parentDto.getGuardianPhone1().isEmpty()) {
                parentsRepo.findByGuardianPhone1(parentDto.getGuardianPhone1())
                        .ifPresent(p -> {
                            throw new ResourceAlreadyExistsException("Parent already exists with phone number: " + parentDto.getGuardianPhone1());
                        });
            }

            // 3. Save Address
            Address address = AddressMapper.toEntity(addressDto);
            addressRepo.save(address);

            // 4. Convert ParentDto to Parent Entity
            Parents parent = ParentMapper.toEntity(parentDto);

            // 5. Set Address to Parent
            parent.setAddress(address);

            // 6. Fetch students by IDs
            List<Students> students = studentsRepo.findAllById(studentIds);
            if (students.size() != studentIds.size()) {
                throw new ResourceNotFoundException("One or more students not found for the given IDs");
            }

            // 7. Set parent inside each student
            students.forEach(student -> student.setParent(parent));

            // 8. Set students to parent
            parent.setStudent(students);

            // 9. Save Parent
            return parentsRepo.save(parent);

        } catch (InternalServerError e) {
            throw new RuntimeException("Error creating parent: " + e.getMessage());
        }
    }

    @Override
    public List<ParentDto> getAllParents() {
        try {
            return parentsRepo.findAll().stream()
                    .map(ParentMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching parents: " + e.getMessage());
        }
    }

    @Override
    public Parents getParentById(String parentId) {
        return parentsRepo.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found with ID: " + parentId));
    }

    @Override
    public Parents updateParent(String parentId, ParentDto parentDto) {
        try {
            Parents existing = getParentById(parentId);
            Parents updatedParent = ParentMapper.toEntity(parentDto);
            return parentsRepo.save(updatedParent);
        } catch (Exception e) {
            throw new RuntimeException("Error updating parent: " + e.getMessage());
        }
    }

    @Override
    public void deleteParent(String parentId) {
        try {
            Parents parent = getParentById(parentId);
            parentsRepo.delete(parent);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting parent: " + e.getMessage());
        }
    }

    @Override
    public List<ParentDto> searchParentsByName(String nameKeyword) {
        try {
            return parentsRepo.searchByParentName(nameKeyword).stream()
                    .map(ParentMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error searching parents: " + e.getMessage());
        }
    }

    @Override
    public Parents getParentByPhone(String phone) {
        try {
            return parentsRepo.findByGuardianPhone1OrGuardianPhone2(phone, phone)
                    .orElseThrow(() -> new ResourceNotFoundException("Parent not found with phone: " + phone));
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch parent by phone", e);
        }
    }

    @Override
    public List<ParentDto> getParentsWithMoreThanNChildren(int minChildren) {
        try {
            return parentsRepo.findByChildrenGreaterThan(minChildren)
                    .stream()
                    .map(ParentMapper::toDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch parents with more than " + minChildren + " children", e);
        }
    }
}
