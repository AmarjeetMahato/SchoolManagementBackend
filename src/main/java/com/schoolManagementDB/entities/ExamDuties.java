package com.schoolManagementDB.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "exam_duties")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExamDuties {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private  String  examDutiesId;


    private  String dutyRole; //enm

    private  String remarks;

    private  LocalDateTime assignedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "examSchedules_id", nullable = false)
    @JsonIgnore
    private ExamSchedules examSchedules;

    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (examDutiesId == null) {
            this.examDutiesId = UUID.randomUUID().toString();
        }
    }
}
