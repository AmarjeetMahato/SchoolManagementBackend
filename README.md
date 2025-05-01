# 🎓 School Management System - Backend (Spring Boot + PostgreSQL)

A robust and modular backend API for managing school operations such as teachers, students, attendance, subjects, and class-section assignments. Built using industry-standard practices with **Spring Boot**, **PostgreSQL**, and **RESTful APIs**.

---

## 📌 Project Highlights

- ✅ Clean, layered architecture (Controller, Service, Repository)
- 📄 DTO-based request/response design with validation
- 🔁 Asynchronous repository operations using `ThreadPoolTaskExecutor`
- 🎯 Entity relationships modeled for real-world school data
- 🛡️ Custom exception handling (`ResourceNotFoundException`, etc.)
- 📆 Attendance tracking per student, per subject, per section
- 🧪 JUnit + Mockito ready for testing

---

## ⚙️ Tech Stack

| Technology       | Description                                |
|------------------|--------------------------------------------|
| Java 21          | Core programming language                  |
| Spring Boot 3+   | Backend framework                          |
| Spring Data JPA  | ORM & database interaction                 |
| PostgreSQL       | Relational database                        |
| Lombok           | Boilerplate reduction                      |
| Bean Validation  | Input validation using annotations         |
| ExecutorService  | Asynchronous entity loading                |

---

📚 Backend API Endpoints


### ✅ ClassController (`/api/v1/classes`)
- `POST   /create` — Create a new class  
- `PUT    /{classId}` — Update class by ID  
- `DELETE /{classId}` — Delete class by ID  
- `GET    /{classId}` — Get class by ID  
- `GET    /get-all-classes` — Get all classes  

---

### ✅ SectionController (`/api/v1/sections`)
- `POST   /create` — Create a new section  
- `PUT    /{sectionId}` — Update section by ID  
- `DELETE /{sectionId}` — Delete section by ID  
- `GET    /{sectionId}` — Get section by ID  
- `GET    /get-all-sections` — Get all sections  

---

### ✅ SubjectController (`/api/v1/subjects`)
- `POST   /create` — Create a new subject  
- `PUT    /{subjectId}` — Update subject by ID  
- `DELETE /{subjectId}` — Delete subject by ID  
- `GET    /{subjectId}` — Get subject by ID  
- `GET    /get-all-subjects` — Get all subjects  

---

### ✅ TeacherController (`/api/v1/teachers`)
- `POST   /create` — Create a new teacher  
- `PUT    /{teacherId}` — Update teacher by ID  
- `DELETE /{teacherId}` — Delete teacher by ID  
- `GET    /{teacherId}` — Get teacher by ID  
- `GET    /get-all-teachers` — Get all teachers  

---

### ✅ StudentController (`/api/v1/students`)
- `POST   /create` — Create a new student  
- `PUT    /{studentId}` — Update student by ID  
- `DELETE /{studentId}` — Delete student by ID  
- `GET    /{studentId}` — Get student by ID  
- `GET    /get-all-students` — Get all students  

---

### ✅ AttendanceController (`/api/v1/attendances`)
- `POST   /create` — Mark attendance  
- `PUT    /{attendanceId}` — Update attendance by ID  
- `DELETE /{attendanceId}` — Delete attendance by ID  
- `GET    /{attendanceId}` — Get attendance by ID  
- `GET    /get-all-attendance` — Get all attendance records  

---

### ✅ TeacherSubjectSectionController (`/api/v1/teacher-subject-section`)
- `POST   /create` — Assign teacher to subject and section  
- `PUT    /{id}` — Update teacher-subject-section assignment  
- `GET    /get-all` — Get all teacher-subject-section mappings  
- `GET    /{id}` — Get a single assignment by ID  
- `DELETE /{id}` — Delete assignment by ID  

---

### ✅ ParentController (`/api/v1/parents`)
- `POST   /create` — Create a new parent with address and student linkage  
- `GET    /get-all-parents` — Get all parents  
- `GET    /{id}` — Get parent by ID  
- `PUT    /{id}` — Update parent by ID  
- `DELETE /{id}` — Delete parent by ID  
- `GET    /search?keyword=` — Search parents by name  
- `GET    /by-phone?phone=` — Get parent by phone number  
- `GET    /children-over?count=` — Get parents with more than N children  

---

### ✅ AddressController (`/api/v1/address`)
- `POST   /create` — Create a new address  
- `GET    /{id}` — Get address by ID  
- `GET    /` — Get all addresses  
- `PUT    /{id}` — Update address by ID  
- `DELETE /{id}` — Delete address by ID  
- `GET    /city/{city}` — Filter addresses by city  
- `GET    /state/{state}` — Filter addresses by state  
- `GET    /postal-code/{postalCode}` — Filter addresses by postal code  









