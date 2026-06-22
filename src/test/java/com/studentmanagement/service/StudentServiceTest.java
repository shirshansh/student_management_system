package com.studentmanagement.service;

import com.studentmanagement.dto.StudentPageResponseDto;
import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.LibraryCard;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.DepartmentNotFoundException;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.repository.LibraryCardRepository;
import com.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private LibraryCardRepository libraryCardRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    private Department department;

    private LibraryCard libraryCard;

    @BeforeEach
    void setup() {

        department = new Department(
                1,
                "cse"
        );

        libraryCard = new LibraryCard(
                1,
                3
        );

        student = new Student(
                1,
                "shim",
                "chan",
                "shim.chan@college.com",
                department,
                9.5,
                libraryCard
        );
    }

    private StudentRequestDto createStudentRequestDto() {

        return new StudentRequestDto(
                "shim",
                "chan",
                "shim.chan@college.com",
                1,
                9.5,
                3
        );
    }

    private StudentRequestDto createUpdateStudentRequestDto() {

        return new StudentRequestDto(
                "mihs",
                "nahc",
                "mihs.nahc@college.com",
                1,
                9.0,
                0
        );
    }

    private void assertResponse(
            StudentResponseDto response,
            Integer id,
            String firstName,
            String lastName,
            String email,
            String departmentName,
            Double cgpa,
            Integer issuedBooks) {

        assertEquals(id, response.getId());
        assertEquals(firstName, response.getFirstName());
        assertEquals(lastName, response.getLastName());
        assertEquals(email, response.getEmail());
        assertEquals(departmentName, response.getDepartmentName());
        assertEquals(cgpa, response.getCgpa());
        assertEquals(issuedBooks, response.getIssuedBooks());
    }

    @Test
    void shouldReturnStudentWhenIdExists() {

        when(studentRepository.findById(1))
                .thenReturn(Optional.of(student));

        StudentResponseDto response =
                studentService.findById(1);

        assertResponse(
                response,
                1,
                "shim",
                "chan",
                "shim.chan@college.com",
                "cse",
                9.5,
                3
        );
    }

    @Test
    void shouldCreateStudentWhenValidRequestIsProvided() {

        StudentRequestDto request = createStudentRequestDto();

        when(studentRepository.save(any(Student.class)))
                .thenReturn(student);

        when(departmentRepository.findById(1))
                .thenReturn(Optional.of(department));

        StudentResponseDto response =
                studentService.save(request);

        assertResponse(
                response,
                1,
                "shim",
                "chan",
                "shim.chan@college.com",
                "cse",
                9.5,
                3
        );
    }

    @Test
    void shouldUpdateStudentWhenValidIdAndRequestAreProvided() {

        StudentRequestDto updateRequest = createUpdateStudentRequestDto();

        when(studentRepository.findById(1))
                .thenReturn(Optional.of(student));

        when(departmentRepository.findById(1))
                .thenReturn(Optional.of(department));

        when(studentRepository.save(any(Student.class)))
                .thenReturn(student);

        StudentResponseDto response = studentService.updateStudent(1, updateRequest);

        assertResponse(
                response,
                1,
                "mihs",
                "nahc",
                "mihs.nahc@college.com",
                "cse",
                9.0,
                0
        );

    }

    @Test
    void shouldDeleteStudentWhenValidIdIsProvided() {

        when(studentRepository.findById(1))
                .thenReturn(Optional.of(student));

        studentService.deleteById(1);

        verify(studentRepository).delete(student);
    }

    @Test
    void shouldReturnStudentWhenValidEmailIsProvided() {

        when(studentRepository.findByEmail("shim.chan@college.com"))
                .thenReturn(Optional.of(student));

        StudentResponseDto response = studentService.findByEmail("shim.chan@college.com");

        assertResponse(
                response,
                1,
                "shim",
                "chan",
                "shim.chan@college.com",
                "cse",
                9.5,
                3
        );
    }

    @Test
    void shouldThrowStudentNotFoundExceptionWhenIdDoesNotExist() {

        when(studentRepository.findById(1))
                .thenReturn(Optional.empty());

        StudentNotFoundException exception =
                assertThrows(
                        StudentNotFoundException.class,
                        () -> studentService.findById(1)
                );

        assertEquals(
                "Student with id = 1 not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowStudentNotFoundExceptionWhenEmailDoesNotExist() {

        String email = "unknown@college.com";

        when(studentRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        StudentNotFoundException exception =
                assertThrows(
                        StudentNotFoundException.class,
                        () -> studentService.findByEmail(email)
                );

        assertEquals(
                "Student with email = unknown@college.com not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowStudentNotFoundExceptionWhenDeletingNonExistingStudent() {

        when(studentRepository.findById(1))
                .thenReturn(Optional.empty());

        StudentNotFoundException exception =
                assertThrows(
                        StudentNotFoundException.class,
                        () -> studentService.deleteById(1)
                );

        assertEquals(
                "Student with id = 1 not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowStudentNotFoundExceptionWhenUpdatingNonExistingStudent() {

        StudentRequestDto updateRequest = createUpdateStudentRequestDto();

        when(studentRepository.findById(1))
                .thenReturn(Optional.empty());

        StudentNotFoundException exception =
                assertThrows(
                        StudentNotFoundException.class,
                        () -> studentService.updateStudent(1, updateRequest)
                );

        assertEquals(
                "Student with id = 1 not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldThrowDepartmentNotFoundExceptionWhenDepartmentIdDoesNotExist() {

        StudentRequestDto request =
                createStudentRequestDto();

        when(departmentRepository.findById(1))
                .thenReturn(Optional.empty());

        DepartmentNotFoundException exception =
                assertThrows(
                        DepartmentNotFoundException.class,
                        () -> studentService.save(request)
                );

        assertEquals(
                "Department with id = 1 not found",
                exception.getMessage()
        );
    }

    @Test
    void shouldReturnStudentPageResponseWhenFindAllStudents() {

        List<Student> students = List.of(student);

        Page<Student> page =
                new PageImpl<>(students, PageRequest.of(0, 5), 1);

        when(studentRepository.findAll(any(Pageable.class)))
                .thenReturn(page);

        StudentPageResponseDto response =
                studentService.findAll(
                        0,
                        5,
                        "id",
                        Sort.Direction.ASC);

        assertEquals(1, response.getStudents().size());

        assertResponse(
                response.getStudents().getFirst(),
                1,
                "shim",
                "chan",
                "shim.chan@college.com",
                "cse",
                9.5,
                3
        );

        assertEquals(0, response.getCurrentPage());
        assertEquals(1, response.getTotalPages());
        assertEquals(1, response.getTotalElements());
        assertTrue(response.isFirst());
        assertTrue(response.isLast());
    }
}
