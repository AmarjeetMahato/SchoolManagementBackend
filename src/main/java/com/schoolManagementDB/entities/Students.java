package com.schoolManagementDB.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@EntityListeners(AuditingEntityListener.class)  // Add this line
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Students {

    @Id
    @Column(updatable = false, unique = true, nullable = false)
    private String  studentId;

    @NotBlank(message = "first name is required !!")
    @Column(nullable = false)
    private  String firstName;

    @Column(nullable = false)
    private  String middleName;

    @NotBlank(message = "last name is required !!")
    @Column(nullable = false)
    private  String lastName;

    @NotBlank(message = "gender is required !!")
    @Column(nullable = false)
    private  String gender;

    @NotNull(message = "date of birth is required !!")
    @Column(nullable = false)
    private LocalDateTime dateOfBirth;

    @NotNull(message = "roll number is required !!")
    @Column(nullable = false)
    private int rollNumber ;

    @NotNull(message = "admission date is required !!")
    @Column(nullable = false)
    private LocalDateTime  admissionDate;

    @NotNull(message = "student active or not is required !!")
    @Column(nullable = false)
    private  boolean isActive ;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId", nullable = true)
    @JsonIgnore
    private Address address;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId", nullable = true)
    @JsonIgnore
    private Classes classes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId", nullable = true)
    @JsonIgnore
    private Section section;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId", nullable = true)
    @JsonIgnore
    private Parents parent;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList;

    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (studentId == null) {
            this.studentId = UUID.randomUUID().toString();
        }
    }


}
