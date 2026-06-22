package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "cgpa")
    private Double cgpa;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library_card_id")
    private LibraryCard libraryCard;

    // Constructors
    public Student() {

    }

    public Student(String firstName, String lastName, String email, Department department, Double cgpa, LibraryCard libraryCard) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.cgpa = cgpa;
        this.libraryCard = libraryCard;
    }

    public Student(Integer id, String firstName, String lastName, String email, Department department, Double cgpa, LibraryCard libraryCard) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.cgpa = cgpa;
        this.libraryCard = libraryCard;
    }

    public void assignLibraryCard(LibraryCard libraryCard) {

        this.libraryCard = libraryCard;
        libraryCard.setStudent(this);
    }

    // Getters and Setters
    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public Department getDepartment() {

        return department;
    }

    public void setDepartment(Department department) {

        this.department = department;
    }

    public Double getCgpa() {

        return cgpa;
    }

    public void setCgpa(Double cgpa) {

        this.cgpa = cgpa;
    }

    public LibraryCard getLibraryCard() {

        return libraryCard;
    }

    public void setLibraryCard(LibraryCard libraryCard) {

        this.libraryCard = libraryCard;
    }

    @Override
    public String toString() {

        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", department=" + department +
                ", cgpa=" + cgpa +
                ", libraryCard=" + libraryCard +
                '}';
    }
}
