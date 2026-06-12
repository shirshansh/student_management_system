package com.studentmanagement.repository;

import com.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findByEmail(String email);

    List<Student> findByDepartment(String department);

    List<Student> findByFirstNameContainingIgnoreCase(String keyword);

    List<Student> findByCgpaGreaterThan(Double cgpa);

    List<Student> findByCgpa(Double cgpa);

    List<Student> findByCgpaLessThan(Double cgpa);

    List<Student> findByDepartmentAndCgpaGreaterThan(String department, Double cgpa);
}
