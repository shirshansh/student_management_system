package com.studentmanagement.controller;

import com.studentmanagement.dto.LibraryCardResponse;
import com.studentmanagement.dto.StudentPageResponseDto;
import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@Tag(
        name = "Student Controller",
        description = "CRUD APIs and Search APIs for Students"
)
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    // GET /api/students?page={page}&size={size}
    @GetMapping
    @Operation(
            summary = "Get List of Students",
            description = "Returns the List of Students"
    )
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
    @Operation(
            summary = "Get Student by ID",
            description = "Returns a Student for the given ID"
    )
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Integer id) {

        StudentResponseDto studentResponseDto = studentService.findById(id);

        return ResponseEntity.ok(studentResponseDto);
    }

    // POST /api/students
    @PostMapping
    @Operation(
            summary = "Save new Student",
            description = "Returns the Student after saving"
    )
    public ResponseEntity<StudentResponseDto> saveStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {

        StudentResponseDto savedStudentResponseDto = studentService.save(studentRequestDto);

        return new ResponseEntity<>(savedStudentResponseDto, HttpStatus.CREATED);
    }

    // PUT /api/students/{id}
    @PutMapping("/{id}")
    @Operation(
            summary = "Update Student by ID",
            description = "Returns the Student after updation"
    )
    public ResponseEntity<StudentResponseDto> updateStudent(
            @PathVariable Integer id,
            @Valid @RequestBody StudentRequestDto studentRequestDto) {

        StudentResponseDto updatedStudentResponseDto = studentService.updateStudent(id, studentRequestDto);

        return ResponseEntity.ok(updatedStudentResponseDto);
    }

    // DELETE /api/students/{id}
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Student by ID",
            description = "Deletes the Student and returns nothing"
    )
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {

        studentService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    // GET /api/students/email/{email}
    @GetMapping("/email/{email}")
    @Operation(
            summary = "Search Student by Email",
            description = "Returns a Student for the given Email"
    )
    public ResponseEntity<StudentResponseDto> getStudentByEmail(@PathVariable String email) {

        StudentResponseDto studentResponseDto = studentService.findByEmail(email);

        return ResponseEntity.ok(studentResponseDto);
    }

    // GET /api/students/departmentId/{departmentId}
    @GetMapping("/departmentId/{departmentId}")
    @Operation(
            summary = "Search Students by Department ID",
            description = "Returns the List of Students for the given Department ID"
    )
    public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartment(@PathVariable Integer departmentId) {

        List<StudentResponseDto> students = studentService.findByDepartment(departmentId);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/search?keyword={keyword}
    @GetMapping("/search")
    @Operation(
            summary = "Search Students by keyword",
            description = "Returns the List of Students whose First Name contains the given keyword"
    )
    public ResponseEntity<List<StudentResponseDto>> searchStudents(@RequestParam String keyword) {

        List<StudentResponseDto> students = studentService.searchByFirstName(keyword);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/cgpa/{comparator}/{cgpa}
    @GetMapping("/cgpa/{comparator}/{cgpa}")
    @Operation(
            summary = "Search Student by comparator and cgpa",
            description = "Returns the List of Students for the given cgpa condition"
    )
    public ResponseEntity<List<StudentResponseDto>> getStudentsByCgpaGreaterThan(@PathVariable String comparator, @PathVariable Double cgpa) {

        List<StudentResponseDto> students = studentService.findByCgpa(comparator, cgpa);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/filter?departmentId={departmentId}&cgpa={cgpa}
    @GetMapping("/filter")
    @Operation(
            summary = "Search Student by Department ID and CGPA",
            description = "Returns the List of Students for the given Department ID and CGPA"
    )
    public ResponseEntity<List<StudentResponseDto>> getStudentsByFilter(@RequestParam Integer departmentId, @RequestParam Double cgpa) {

        List<StudentResponseDto> students = studentService.findByDepartmentAndCgpaGreaterThan(departmentId, cgpa);

        return ResponseEntity.ok(students);
    }

    // GET /api/students/libraryCard?student={studentId}
    @GetMapping("/libraryCard")
    @Operation(
            summary = "Get Library Card by Student ID",
            description = "Returns the Library Card information for the given Student ID"
    )
    public ResponseEntity<LibraryCardResponse> getLibraryCard(@RequestParam Integer studentId) {

        LibraryCardResponse libraryCard = studentService.findLibraryCard(studentId);

        return ResponseEntity.ok(libraryCard);
    }
}
