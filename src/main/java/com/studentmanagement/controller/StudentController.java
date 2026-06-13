package com.studentmanagement.controller;

import com.studentmanagement.dto.*;
import com.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    // GET /api/students?page={page}&size={size}
    @GetMapping
    public ResponseEntity<StudentPageResponseDto> getStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortOrder
    ) {

        StudentPageResponseDto response =
                studentService
                        .findAll(
                                page,
                                size,
                                sortBy,
                                sortOrder
                        );

        return ResponseEntity.ok(response);
    }

    // GET /api/students/{id}
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Integer id) {

        StudentResponseDto studentResponseDto = studentService.findById(id);

        return ResponseEntity.ok(studentResponseDto);
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<StudentResponseDto> saveStudent(@Valid @RequestBody CreateStudentRequestDto studentRequestDto) {

        StudentResponseDto savedStudentResponseDto = studentService.save(studentRequestDto);

        return new ResponseEntity<>(savedStudentResponseDto, HttpStatus.CREATED);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateStudentRequestDto updateStudentRequestDto) {

        StudentResponseDto updatedStudentResponseDto = studentService.updateStudent(id, updateStudentRequestDto);

        return ResponseEntity.ok(updatedStudentResponseDto);
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {

        studentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // GET /api/students/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDto> getStudentByEmail(@PathVariable String email) {

        StudentResponseDto studentResponseDto = studentService.findByEmail(email);

        return ResponseEntity.ok(studentResponseDto);
    }

    // GET /api/students/departmentId/{departmentId}
    @GetMapping("/departmentId/{departmentId}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartment(@PathVariable Integer departmentId) {

        List<StudentResponseDto> students = studentService.findByDepartment(departmentId);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/search?keyword={keyword}
    @GetMapping("/search")
    public ResponseEntity<List<StudentResponseDto>> searchStudents(@RequestParam String keyword) {

        List<StudentResponseDto> students = studentService.searchByFirstName(keyword);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/cgpa/{comparator}/{cgpa}
    @GetMapping("/cgpa/{comparator}/{cgpa}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByCgpaGreaterThan(@PathVariable String comparator, @PathVariable Double cgpa) {

        List<StudentResponseDto> students = studentService.findByCgpa(comparator, cgpa);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/filter?departmentId={departmentId}&cgpa={cgpa}
    @GetMapping("/filter")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByFilter(@RequestParam Integer departmentId, @RequestParam Double cgpa) {

        List<StudentResponseDto> students = studentService.findByDepartmentAndCgpaGreaterThan(departmentId, cgpa);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/libraryCard?student={studentId}
    @GetMapping("/libraryCard")
    public ResponseEntity<LibraryCardResponse> getLibraryCard(@RequestParam Integer studentId) {

        LibraryCardResponse libraryCard = studentService.findLibraryCard(studentId);

        return ResponseEntity.ok(libraryCard);
    }
}
