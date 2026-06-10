package com.studentmanagement.controller;

import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /api/students
    @GetMapping
    public List<Student> getStudents() {
        return studentService.findAll();
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    // POST /api/students
    @PostMapping
    public Student saveStudent(@Valid @RequestBody Student student) {
        // Force id to null so that save() performs insert
        student.setId(null);

        return studentService.save(student);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable int id,
            @Valid @RequestBody Student student) {

        // Verify if the student exists
        studentService.findById(id);

        student.setId(id);

        return studentService.save(student);
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteById(id);
    }
}
