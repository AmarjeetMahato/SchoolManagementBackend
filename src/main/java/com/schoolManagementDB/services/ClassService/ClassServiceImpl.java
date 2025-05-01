package com.schoolManagementDB.services.ClassService;

import com.schoolManagementDB.dtos.ClassDto;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import com.schoolManagementDB.mappers.ClassMapper;
import com.schoolManagementDB.repositories.ClassRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements  ClassService {

    private final ClassRepo classRepo;
    private  final  ClassMapper classMapper;

    @Override
    public Classes createClass(ClassDto dto) {
        if(dto == null){
             throw new ResourceNotFoundException("class data is empty !!");
        }
        try{
             Classes entity = classMapper.toEntity(dto);
             return  classRepo.save(entity);
        }catch(InternalServerError e){
             throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Classes updateClass(String classId, ClassDto dto) {
        try {
            Classes existingClass = classRepo.findById(classId)
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));

            existingClass.setName(dto.getName());
            existingClass.setCode(dto.getCode());
            existingClass.setDescription(dto.getDescription());
            existingClass.setStatus(dto.getStatus());

            return classRepo.save(existingClass);
        } catch (InternalServerError e) {
            throw new RuntimeException("Failed to update class", e);
        }
     }

    @Override
    public void deleteClass(String classId) {
        try {
            Classes existing = classRepo.findById(classId)
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));
            classRepo.delete(existing);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete class", e);
        }
    }

    @Override
    public Classes getClassById(String classId) {
        try {
            return classRepo.findById(classId)
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));

        } catch (Exception e) {
            throw new RuntimeException("Failed to get class", e);
        }    }

    @Override
    public List<Classes> getAllClasses() {
        try{
             List<Classes> getAllClass = this.classRepo.findAll();
             return  getAllClass;
        }catch (InternalServerError e){
            throw new RuntimeException("Failed to get all classes");
        }
    }
}
