package com.studentmanagement.controller;

import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
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
    public ResponseEntity<List<StudentResponseDto>> getStudents() {

        List<StudentResponseDto> students = studentService.findAll();

        return ResponseEntity.ok(students);
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Integer id) {

        StudentResponseDto studentResponseDto = studentService.findById(id);

        return ResponseEntity.ok(studentResponseDto);
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<StudentResponseDto> saveStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {

        StudentResponseDto savedStudentResponseDto = studentService.save(studentRequestDto);

        return new ResponseEntity<>(savedStudentResponseDto, HttpStatus.CREATED);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody StudentRequestDto studentRequestDto) {

        StudentResponseDto updatedStudentResponseDto = studentService.updateStudent(id, studentRequestDto);

        return ResponseEntity.ok(updatedStudentResponseDto);
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {

        studentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
