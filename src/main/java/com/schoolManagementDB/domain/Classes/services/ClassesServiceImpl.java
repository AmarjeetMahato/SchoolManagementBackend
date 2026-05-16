package com.schoolManagementDB.domain.Classes.services;

import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.domain.Classes.mapper.ClassMapper;
import com.schoolManagementDB.domain.Classes.repository.ClassesRepository;
import com.schoolManagementDB.entities.Classes;
import com.schoolManagementDB.exceptions.InternalServerError;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ClassesServiceImpl implements  IClassesService {

     private  final ClassesRepository classesRepository;
     private  final ClassMapper classMapper;

    @Override
    public Classes createClass(ClassDto dto) {
        if(dto == null){
            throw new ResourceNotFoundException("class data is empty !!");
        }
        try{
            if(classesRepository.findByName(dto.getName()).isPresent()){
                 throw  new ResourceNotFoundException("Class is already created");
            }

            // 2. Map the DTO to the Entity
            Classes classes = classMapper.toEntity(dto);

            return  classesRepository.save(classes);

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
