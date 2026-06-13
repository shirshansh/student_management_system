package com.studentmanagement.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {

    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Department ID is required")
    private Integer departmentId;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "Cannot be less than 0")
    @DecimalMax(value = "10.0", message = "Cannot be greater than 10")
    private Double cgpa;

    @NotNull(message = "Issued books count is required")
    @Min(value = 0, message = "Issued books cannot be negative")
    private Integer issuedBooks;

    public CreateStudentRequestDto() {

    }

    public CreateStudentRequestDto(String firstName, String lastName, String email, Integer departmentId, Double cgpa, Integer issuedBooks) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.cgpa = cgpa;
        this.issuedBooks = issuedBooks;
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

    public Integer getDepartmentId() {

        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {

        this.departmentId = departmentId;
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

        return "UpdateStudentRequestDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", departmentId=" + departmentId +
                ", cgpa=" + cgpa +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
