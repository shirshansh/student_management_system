package com.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Student Updation Request")
public class UpdateStudentRequestDto {

    @Schema(
            description = "First Name",
            example = "Shim"
    )
    @NotBlank(message = "First Name is required")
    private String firstName;

    @Schema(
            description = "Last Name",
            example = "Chan"
    )
    @NotBlank(message = "Last Name is required")
    private String lastName;

    @Schema(
            description = "Email",
            example = "shim.chan@college.com"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(
            description = "Department ID",
            example = "1"
    )
    @NotNull(message = "Department ID is required")
    @Min(value = 1, message = "Department ID cannot be less than 1")
    private Integer departmentId;

    @Schema(
            description = "CGPA",
            example = "8.5"
    )
    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "CGPA cannot be less than 0")
    @DecimalMax(value = "10.0", message = "CGPA cannot be greater than 10")
    private Double cgpa;

    @Schema(
            description = "Library Card ID",
            example = "1"
    )
    @NotNull(message = "Library Card ID is required")
    @Min(value = 1, message = "Library Card ID cannot be less than 1")
    private Integer libraryCardId;

    @Schema(
            description = "Number of Issued Books",
            example = "3"
    )
    @NotNull(message = "Issued books count is required")
    @Min(value = 0, message = "Issued books cannot be negative")
    private Integer issuedBooks;

    public UpdateStudentRequestDto() {

    }

    public UpdateStudentRequestDto(String firstName, String lastName, String email, Integer departmentId, Double cgpa, Integer libraryCardId, Integer issuedBooks) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.libraryCardId = libraryCardId;
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

    public Integer getLibraryCardId() {

        return libraryCardId;
    }

    public void setLibraryCardId(Integer libraryCardId) {

        this.libraryCardId = libraryCardId;
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
                ", libraryCardId=" + libraryCardId +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
