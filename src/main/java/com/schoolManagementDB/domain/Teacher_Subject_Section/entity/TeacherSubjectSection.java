package com.schoolManagementDB.domain.Teacher_Subject_Section.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.domain.Teacher.entity.Teacher;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "teacher_subject_sections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherSubjectSection {

    @Id
    @Column(nullable = false,updatable = false,unique = true)
    private  String teacherSubjectId;

    @Column(nullable = false)
    private  String classes;

    private LocalDateTime assignedAt;

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
