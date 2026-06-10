package com.studentmanagement.controller;

import com.studentmanagement.entity.Student;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Student>> getStudents() {
        List<Student> students = studentService.findAll();

        return ResponseEntity.ok(students);
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        Student student = studentService.findById(id);

        return ResponseEntity.ok(student);
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        // Force id to null so that save() performs insert
        student.setId(null);

        Student savedStudent = studentService.save(student);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody Student student) {

        Student updatedStudent = studentService.updateStudent(id, student);

        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
