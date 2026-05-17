package com.schoolManagementDB.domain.Section.services;

import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.Classes.repository.ClassesRepository;
import com.schoolManagementDB.domain.Section.dtos.SectionDto;
import com.schoolManagementDB.domain.Section.dtos.SectionResponseDto;
import com.schoolManagementDB.domain.Section.dtos.SectionUpdateDto;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Section.mapper.SectionMapper;
import com.schoolManagementDB.domain.Section.repository.SectionRepository;
import com.schoolManagementDB.exceptions.BadRequestException;
import com.schoolManagementDB.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements  ISectionService {

    private  final SectionRepository sectionRepository;
    private  final SectionMapper sectionMapper;
    private  final ClassesRepository classesRepository;


    @Override
    public SectionResponseDto createSection(SectionDto dto) {
        try{
            Classes classes = classesRepository.findById(dto.getClassId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Class not found ")
                    );

            boolean exists = sectionRepository.existsByNameAndClassEntity_ClassId(
                    dto.getName(),
                    dto.getClassId()
            );

            if (exists) {
                throw new ResourceNotFoundException("Section already exists for this class");
            }

            Section section = sectionMapper.toEntity(dto, classes);

            Section savedSection = sectionRepository.save(section);
            return sectionMapper.toResponse(savedSection);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SectionResponseDto updateSection(String sectionId, SectionUpdateDto dto) {
         if(sectionId == null || sectionId.isEmpty()){
              throw new BadRequestException("Section Id is required !!");
         }

        Classes classes = classesRepository.findById(dto.getClassId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Class not found ")
                );

        Section section = sectionRepository.findById(dto.getClassId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Class not found ")
                );
        sectionMapper.updateEntity(dto, section,classes);

        Section  savedSection   = sectionRepository.save(section);
        return  sectionMapper.toResponse(savedSection);
    }

    @Override
    public void deleteSection(String sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(
                ()-> new ResourceNotFoundException("Section not found")
        );
        sectionRepository.delete(section);
    }

    @Override
    public SectionResponseDto getSectionById(String sectionId) {
        Section section = sectionRepository.findById(sectionId).orElseThrow(
                ()-> new ResourceNotFoundException("Section not found")
        );

        return  sectionMapper.toResponse(section);
    }

    @Override
    public List<SectionResponseDto> getAllSections() {

        List<Section> sectionList = sectionRepository.findAll();
        return  sectionList.stream()
                .map(sectionMapper::toResponse)
                .toList();
    }
}
