package com.studentmanagement.dto;

public class StudentResponseDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentName;

    private Double cgpa;

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
