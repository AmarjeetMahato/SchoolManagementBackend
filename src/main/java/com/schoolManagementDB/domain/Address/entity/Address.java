package com.schoolManagementDB.domain.Address.entity;

import com.schoolManagementDB.domain.Address.enums.AddressOwnerType;
import com.schoolManagementDB.domain.Parents.entity.Parents;
import com.schoolManagementDB.domain.Students.entity.Student;
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
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {

    @Id
    @Column(nullable = false,updatable = false,unique = true)
    private  String addressId;

    @NotBlank(message = "address id required !!")
    @Column(nullable = false)
    private  String addressLine1;

    private  String addressLine2;

    @NotBlank(message = "city is required !!")
    @Column(nullable = false)
    private  String  city;

    @NotBlank(message = "state is required !!")
    @Column(nullable = false)
    private  String state;

    @NotBlank(message = "country is required !!")
    @Column(nullable = false)
    private  String country;

    // 🆕 NEW FIELD
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressOwnerType ownerType;

    @NotBlank(message = "postal code is required !!")
    @Column(nullable = false)
    private  String postalCode;

    @NotBlank(message = "address type is required !!")
    @Column(nullable = false)
    private  String addressType;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parents> parents;

    @CreatedDate
    @Column(nullable = true, updatable = false)  // Ensure nullable = true
    private LocalDateTime createAt;

    @LastModifiedDate // Automatically updates on modification
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        if (addressId == null) {
            this.addressId = UUID.randomUUID().toString();
        }
    }

}
