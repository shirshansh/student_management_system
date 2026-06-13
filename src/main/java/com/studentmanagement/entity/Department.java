package com.studentmanagement.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private Integer id;

    @Column(name = "department")
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Student> students = new ArrayList<>();

    public Department() {

    }

    public Department(String name) {

        this.name = name;
    }

    public Department(Integer id, String name) {

        this.id = id;
        this.name = name;
    }

    public void addStudent(Student student) {

        students.add(student);
        student.setDepartment(this);
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public List<Student> getStudents() {

        return students;
    }

    public void setStudents(List<Student> students) {

        this.students = students;
    }

    @Override
    public String toString() {

        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
