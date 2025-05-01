package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepo  extends JpaRepository<Classes,String> {
}
