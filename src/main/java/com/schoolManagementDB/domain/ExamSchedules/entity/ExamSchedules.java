package com.schoolManagementDB.domain.ExamSchedules.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schoolManagementDB.entities.ExamDuties;
import com.schoolManagementDB.entities.Section;
import com.schoolManagementDB.entities.Subject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "exam_schedules")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExamSchedules {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private  String  examSchedulesId;

    @NotBlank(message = "title is required !!")
    @Column(nullable = false)
    private  String title;

    @NotBlank(message = "exam date is required !!")
    @Column(nullable = false)
    private LocalDateTime examDate;

    @NotBlank(message = "start time is required !!")
    @Column(nullable = false)
    private  LocalDateTime startTime;

    @NotBlank(message = "end time is required !!")
    @Column(nullable = false)
    private  LocalDateTime endTime;

    private  String examType;  // Enum

    private  String remarks;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnore
    private Section section;


    @OneToMany(mappedBy = "examSchedules", cascade = CascadeType.ALL)
    private List<ExamDuties> examDuties;


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (examSchedulesId == null) {
            this.examSchedulesId = UUID.randomUUID().toString();
        }
    }
}
