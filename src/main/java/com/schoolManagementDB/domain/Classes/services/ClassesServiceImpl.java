package com.schoolManagementDB.domain.Classes.services;

import com.schoolManagementDB.domain.Classes.dtos.ClassDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassResponseDto;
import com.schoolManagementDB.domain.Classes.dtos.ClassUpdateDto;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Classes.mapper.ClassMapper;
import com.schoolManagementDB.domain.Classes.repository.ClassesRepository;
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
    public ClassResponseDto createClass(ClassDto dto) {

        try{
            if(classesRepository.findByCode(dto.getCode()).isPresent()){
                 throw  new ResourceNotFoundException("Class is already created");
            }

            // 2. Map the DTO to the Entity
            Classes classes = classMapper.toEntity(dto);
            Classes saveedclasses =   classesRepository.save(classes);
            return  classMapper.toResponse(saveedclasses);
        }catch(InternalServerError e){
            throw  new RuntimeException(e.getMessage());
        }
    }

    @Override
    public ClassResponseDto updateClass(String classId, ClassUpdateDto dto) {
        try {
            Classes existingClass = classesRepository.findById(classId)
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));

           classMapper.updateEntity(dto, existingClass);

            Classes classes =  classesRepository.save(existingClass);

            return classMapper.toResponse(classes);
        } catch (InternalServerError e) {
            throw new RuntimeException("Failed to update class", e);
        }
    }

    @Override
    public void deleteClass(String classId) {
        try {
            Classes existing = classesRepository.findById(classId)
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));
            classesRepository.delete(existing);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete class", e);
        }
    }

    @Override
    public ClassResponseDto getClassById(String classId) {
        try {
            Classes classes =  classesRepository.findById(classId)
                    .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + classId));

            return  classMapper.toResponse(classes);

        } catch (Exception e) {
            throw new RuntimeException("Failed to get class", e);
        }    }

    @Override
    public List<ClassResponseDto> getAllClasses() {
        try{
            List<Classes> getAllClass = this.classesRepository.findAll();
            return getAllClass.stream()
                    .map(classMapper::toResponse)
                    .toList();
        }catch (InternalServerError e){
            throw new RuntimeException("Failed to get all classes");
        }
    }
}
