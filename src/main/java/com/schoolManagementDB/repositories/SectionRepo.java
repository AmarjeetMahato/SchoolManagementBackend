package com.schoolManagementDB.repositories;

import com.schoolManagementDB.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SectionRepo  extends JpaRepository<Section, String> {
}
