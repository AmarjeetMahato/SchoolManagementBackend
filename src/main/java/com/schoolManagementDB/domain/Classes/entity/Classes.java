package com.schoolManagementDB.domain.Classes.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.schoolManagementDB.domain.Section.entity.Section;
import com.schoolManagementDB.domain.Students.entity.Student;
import com.schoolManagementDB.domain.Subject.entity.Subject;
import com.schoolManagementDB.domain.Teacher.entity.Teacher;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)  // Add this line
public class Classes {
    @Id
    @Column(nullable = false,updatable = false,unique = true)
    private String  classId;

    @NotBlank(message = "Class name is required !!")
    @Column(nullable = false)
    private  String name;

    @NotBlank(message = "Class code is required !!")
    @Column(nullable = false)
    private  String code;

    private  String description;

    @NotBlank(message = "Status is required")
    private  String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = true)
    @JsonIgnore
    private Teacher teacher;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
    private List<Section> sections;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "classes", cascade = CascadeType.ALL)
    private List<Subject> subjects;


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;


    @PrePersist
    protected void onCreate() {
        if (classId == null) {
            this.classId = UUID.randomUUID().toString();
        }
    }
}
