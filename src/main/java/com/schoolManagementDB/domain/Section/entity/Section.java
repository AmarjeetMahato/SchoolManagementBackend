package com.schoolManagementDB.domain.Section.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schoolManagementDB.entities.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "sections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)  // Add this line
public class Section {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private  String sectionId;

    @NotBlank(message = "Class name is required")
    @Size(max = 100, message = "Class name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Shift is required")
    @Size(max = 50, message = "Shift must not exceed 50 characters")
    private String shift;

    @NotBlank(message = "Class section or type is required")
    @Size(max = 50, message = "Class section/type must not exceed 50 characters")
    private String classes;

    @NotBlank(message = "Room number is required")
    @Size(max = 20, message = "Room number must not exceed 20 characters")
    private String roomNumber;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must not exceed 20 characters")
    private String status; // Example: ACTIVE, INACTIVE

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classId", nullable = false)
    @JsonIgnore
    private Classes classEntity;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Students> students;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamSchedules> examSchedules;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Teacher_Subject_Section> teacherSubjectSections;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList;


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (sectionId == null) {
            this.sectionId = UUID.randomUUID().toString();
        }
    }

}
