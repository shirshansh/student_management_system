package com.studentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "library_cards")
public class LibraryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Integer id;

    @Column(name = "issued_books")
    private Integer issuedBooks;

    @OneToOne(mappedBy = "libraryCard")
    private Student student;

    public LibraryCard() {

    }

    public LibraryCard(Integer issuedBooks) {

        this.issuedBooks = issuedBooks;
    }

    public LibraryCard(Integer id, Integer issuedBooks) {

        this.id = id;
        this.issuedBooks = issuedBooks;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public Integer getIssuedBooks() {

        return issuedBooks;
    }

    public void setIssuedBooks(Integer issuedBooks) {

        this.issuedBooks = issuedBooks;
    }

    public Student getStudent() {

        return student;
    }

    public void setStudent(Student student) {

        this.student = student;
    }

    @Override
    public String toString() {

        return "LibraryCard{" +
                "id=" + id +
                ", issuedBooks=" + issuedBooks +
                '}';
    }
}
