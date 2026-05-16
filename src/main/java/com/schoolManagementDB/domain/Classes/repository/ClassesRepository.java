package com.schoolManagementDB.domain.Classes.repository;

import com.schoolManagementDB.domain.Classes.entity.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository  extends JpaRepository<Classes,String> {
}
