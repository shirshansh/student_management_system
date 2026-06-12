package com.studentmanagement.controller;

import com.studentmanagement.dto.StudentPageResponseDto;
import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
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

    // GET /api/students/email/{email}
    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDto> getStudentByEmail(@PathVariable String email) {

        StudentResponseDto studentResponseDto = studentService.findByEmail(email);

        return ResponseEntity.ok(studentResponseDto);
    }

    // GET /api/students/department/{department}
    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartment(@PathVariable String department) {

        List<StudentResponseDto> students = studentService.findByDepartment(department);

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

    // GET /api/students/filter?department={department}&cgpa={cgpa}
    @GetMapping("/filter")
    public ResponseEntity<List<StudentResponseDto>> getStudentsByFilter(@RequestParam String department, @RequestParam Double cgpa) {

        List<StudentResponseDto> students = studentService.findByDepartmentAndCgpaGreaterThan(department, cgpa);

        return ResponseEntity.ok(students);
    }
}
