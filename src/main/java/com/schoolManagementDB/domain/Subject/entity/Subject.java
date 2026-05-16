package com.schoolManagementDB.domain.Subject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schoolManagementDB.domain.Attendance.entity.Attendance;
import com.schoolManagementDB.domain.Classes.entity.Classes;
import com.schoolManagementDB.domain.ExamSchedules.entity.ExamSchedules;
import com.schoolManagementDB.services.TeacherSubjectSection.TeacherSubjectSection;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Subject {


    @Id
    @Column(nullable = false,unique = true, updatable = false)
    private  String subjectId;

    @NotBlank(message = "Class name is required !!")
    @Column(nullable = false)
    private  String name;

    @NotBlank(message = "Class code is required !!")
    @Column(nullable = false)
    private  String code;


    @Column(nullable = false)
    @NotNull(message = "Starting time is required !!")
    private LocalDateTime startTime;  // Start time for the period

    @Column(nullable = false)
    @NotNull(message = "Ending time is required !!")
    private LocalDateTime endTime;

    private  String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id", nullable = false)
    @JsonIgnore
    private Classes classes;

    @NotBlank(message = "Status is required")
    @Size(max = 20, message = "Status must not exceed 20 characters")
    private  String status;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ExamSchedules> examSchedules;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<TeacherSubjectSection> teacherAssignments;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Attendance> attendanceList;


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (subjectId == null) {
            this.subjectId = UUID.randomUUID().toString();
        }
    }
}
