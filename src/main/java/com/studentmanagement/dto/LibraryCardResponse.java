package com.studentmanagement.dto;

public class LibraryCardResponse {

    private String firstName;

    private String lastName;

    private Integer libraryCardId;

    private Integer issuedBooks;

    public LibraryCardResponse() {

    }

    public LibraryCardResponse(String firstName, String lastName, Integer libraryCardId, Integer issuedBooks) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.libraryCardId = libraryCardId;
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

        return "LibraryCardResponse{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", libraryCardId=" + libraryCardId +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
