package com.schoolManagementDB.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "attendance_summary")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance_Summary {

    @Id
    @Column(nullable = false,unique = true, updatable = false)
    private String  attendanceId;

    private  String month;

    private int totalPresent;

    private  int totalAbsent;

    private  int totalLeave;

    private  int totalHolidays;

    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

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
