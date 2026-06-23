package com.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Library Card Response")
public class LibraryCardResponseDto {

    @Schema(
            description = "First Name",
            example = "Shim"
    )
    private String firstName;

    @Schema(
            description = "Last Name",
            example = "Chan"
    )
    private String lastName;

    @Schema(
            description = "Library Card ID",
            example = "1"
    )
    private Integer libraryCardId;

    @Schema(
            description = "Number of Issued Books",
            example = "3"
    )
    private Integer issuedBooks;

    public LibraryCardResponseDto() {

    }

    public LibraryCardResponseDto(String firstName, String lastName, Integer libraryCardId, Integer issuedBooks) {

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

        return "LibraryCardResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", libraryCardId=" + libraryCardId +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
