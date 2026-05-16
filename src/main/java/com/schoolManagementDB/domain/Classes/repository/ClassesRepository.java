package com.schoolManagementDB.domain.Classes.repository;

import com.schoolManagementDB.domain.Classes.entity.Classes;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassesRepository  extends JpaRepository<Classes,String> {
    Optional<Classes> findByName(String name);
}
