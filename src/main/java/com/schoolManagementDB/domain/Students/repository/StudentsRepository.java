package com.schoolManagementDB.domain.Students.repository;

import jakarta.validation.constraints.NotNull;

public interface StudentsRepository {
    boolean existsByRollNumberAndClasses_ClassId(
            @NotNull(message = "Roll number is required") int rollNumber, String classId);
}
