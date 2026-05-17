package com.schoolManagementDB.domain.Section.repository;

import com.schoolManagementDB.domain.Section.entity.Section;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SectionRepository extends JpaRepository<Section , String> {
    boolean existsByNameAndClassEntity_ClassId(@NotBlank(message = "Class name is required") @Size(max = 100, message = "Class name must not exceed 100 characters") String name, @NotBlank(message = "Class ID is required") String classId);
}
