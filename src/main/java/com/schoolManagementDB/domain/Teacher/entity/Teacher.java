package com.schoolManagementDB.domain.Teacher.entity;


import com.schoolManagementDB.domain.Address.entity.Address;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.ExamDuties.entity.ExamDuties;
import com.schoolManagementDB.domain.Teacher_Subject_Section.entity.TeacherSubjectSection;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Teacher {

    @Id
    @Column(nullable = false,unique = true, updatable = false)
    private  String teacherId;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "middle_name", length = 100)
    private String middleName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "phone", nullable = true, length = 20)
    private String phone;

    @NotNull(message = "Date of birth is required")
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    @Column(name = "gender", length = 10)
    private String gender;

    @NotBlank(message = "Qualification is required")
    @Column(name = "qualification", length = 200)
    private String qualification;

    @Column(name = "profile_pic")
    private String profilePic;

    @NotNull(message = "Hire date is required")
    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @NotBlank(message = "Status is required")
    @Column(name = "status", nullable = false, length = 20)
    private String status;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Classes> classes;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id", nullable = true)
    private Address address;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<ExamDuties> examDuties;


    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<TeacherSubjectSection> assignedSubjects;


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (teacherId == null) {
            this.teacherId = UUID.randomUUID().toString();
        }
    }
}
