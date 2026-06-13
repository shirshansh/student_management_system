package com.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Student Response")
public class StudentResponseDto {

    @Schema(
            description = "Student ID",
            example = "1"
    )
    private Integer id;

    @Schema(
            description = "First name",
            example = "Shim"
    )
    private String firstName;

    @Schema(
            description = "Last name",
            example = "Chan"
    )
    private String lastName;

    @Schema(
            description = "Email",
            example = "shim.chan@college.com"
    )
    private String email;

    @Schema(
            description = "Department Name",
            example = "Computer Science and Engineering"
    )
    private String departmentName;

    @Schema(
            description = "CGPA",
            example = "8.5"
    )
    private Double cgpa;

    @Schema(
            description = "Number of Issued Books",
            example = "3"
    )
    private Integer issuedBooks;

    public StudentResponseDto() {

    }

    public StudentResponseDto(Integer id, String firstName, String lastName, String email, String departmentName, Double cgpa, Integer issuedBooks) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
        this.cgpa = cgpa;
        this.issuedBooks = issuedBooks;
    }

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

    public String getDepartmentName() {

        return departmentName;
    }

    public void setDepartmentName(String departmentName) {

        this.departmentName = departmentName;
    }

    public Double getCgpa() {

        return cgpa;
    }

    public void setCgpa(Double cgpa) {

        this.cgpa = cgpa;
    }

    public Integer getIssuedBooks() {

        return issuedBooks;
    }

    public void setIssuedBooks(Integer issuedBooks) {

        this.issuedBooks = issuedBooks;
    }

    @Override
    public String toString() {

        return "StudentResponseDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", cgpa=" + cgpa +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
