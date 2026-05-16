package com.schoolManagementDB.domain.Attendance.entity;


import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private  String attendanceId;


    @NotBlank(message = "attendance date is required !!")
    private LocalDate attendanceDate;

    private  String Status;

    private  String markedBy;  // Admin id

    private  String remarks;

    // ✅ Student Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // ✅ Section Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    // ✅ Subject Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (attendanceId == null) {
            this.attendanceId = UUID.randomUUID().toString();
        }
    }

}
