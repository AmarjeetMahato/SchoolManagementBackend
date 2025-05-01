package com.schoolManagementDB.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "teacher_subject_sections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)  // Add this line
public class Teacher_Subject_Section {

    @Id
    @Column(nullable = false,updatable = false,unique = true)
    private  String teacherSubjectId;

    @Column(nullable = false)
    private  String classes;

    private  LocalDateTime assignedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    @JsonIgnore
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnore
    private Section section;


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (teacherSubjectId == null) {
            this.teacherSubjectId = UUID.randomUUID().toString();
        }
    }
}
