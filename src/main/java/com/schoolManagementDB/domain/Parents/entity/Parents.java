package com.schoolManagementDB.domain.Parents.entity;

import com.schoolManagementDB.entities.Address;
import com.schoolManagementDB.entities.Students;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parents")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)  // Add this line
public class Parents {

    @Id
    @Column(nullable = false,updatable = false,unique = true)
    private String  parentId;

    @NotBlank(message = "first name is required !!")
    @Column(nullable = false)
    private String fatherFirstname;


    private  String fatherMiddleName;

    @NotBlank(message = "last name is required !!")
    @Column(nullable = false)
    private  String fatherLastname;

    private  String fatherEmail;


    private  String guardianPhone1;

    private  String guardianPhone2;

    @NotBlank(message = "first name is required!!")
    @Column(nullable = false)
    private  String motherFirstname;

    private  String motherMiddleName;

    @NotBlank(message = "last name is required !!")
    @Column(nullable = false)
    private  String motherLastname;


    private  String fatherOccupation;

    private  String motherOccupation;

    private  int children;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "addressId", nullable = false)
    private Address address;


    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Students> student = new ArrayList<>();


    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createdAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (parentId == null) {
            this.parentId = UUID.randomUUID().toString();
        }
    }
}
