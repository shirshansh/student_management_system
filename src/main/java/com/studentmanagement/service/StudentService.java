package com.studentmanagement.service;

import com.studentmanagement.dto.LibraryCardResponse;
import com.studentmanagement.dto.StudentPageResponseDto;
import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Department;
import com.studentmanagement.entity.LibraryCard;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.DepartmentNotFoundException;
import com.studentmanagement.exception.LibraryCardNotFoundException;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.repository.DepartmentRepository;
import com.studentmanagement.repository.LibraryCardRepository;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {

    private static final Set<String> ALLOWED_SORT_FIELDS =
            Set.of(
                    "id",
                    "firstName",
                    "lastName",
                    "email",
                    "departmentName",
                    "cgpa",
                    "issuedBooks"
            );

    private final StudentRepository studentRepository;

    private final DepartmentRepository departmentRepository;

    private final LibraryCardRepository libraryCardRepository;

    public StudentService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            LibraryCardRepository libraryCardRepository
    ) {

        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.libraryCardRepository = libraryCardRepository;
    }

    private Student convertToEntity(StudentRequestDto studentRequestDto) {

        Student student = new Student();

        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDepartment(getDepartmentById(studentRequestDto.getDepartmentId()));
        student.setCgpa(studentRequestDto.getCgpa());
        student.assignLibraryCard(new LibraryCard(studentRequestDto.getIssuedBooks()));

        return student;
    }

    private StudentResponseDto convertToResponseDto(Student student) {

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setDepartmentName(student.getDepartment().getName());
        studentResponseDto.setCgpa(student.getCgpa());
        studentResponseDto.setIssuedBooks(student.getLibraryCard().getIssuedBooks());

        return studentResponseDto;
    }

    private LibraryCardResponse convertToResponseDto(Student student, LibraryCard libraryCard) {

        LibraryCardResponse libraryCardResponse = new LibraryCardResponse();

        libraryCardResponse.setFirstName(student.getFirstName());
        libraryCardResponse.setLastName(student.getLastName());
        libraryCardResponse.setLibraryCardId(libraryCard.getId());
        libraryCardResponse.setIssuedBooks(libraryCard.getIssuedBooks());

        return libraryCardResponse;
    }

    private Student getStudentById(Integer id) {

        return studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id = " + id + " not found"));
    }

    private Department getDepartmentById(Integer id) {

        return departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id = " + id + " not found"));
    }

    private LibraryCard getLibraryCardById(Integer id) {

        return libraryCardRepository
                .findById(id)
                .orElseThrow(() -> new LibraryCardNotFoundException("Library Card with id = " + id + " not found"));
    }

    public StudentPageResponseDto findAll(int page, int size, String sortBy, Sort.Direction sortOrder) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {

            throw new IllegalArgumentException(
                    "Invalid sort field: " + sortBy);
        }

        Sort sort = Sort.by(sortOrder, sortBy);

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Student> studentPage = studentRepository.findAll(pageable);

        List<StudentResponseDto> students =
                studentPage
                        .getContent()
                        .stream()
                        .map(this::convertToResponseDto)
                        .toList();

        return new StudentPageResponseDto(
                students,
                studentPage.getNumber(),
                studentPage.getTotalPages(),
                studentPage.getTotalElements(),
                studentPage.isFirst(),
                studentPage.isLast()
        );
    }

    public StudentResponseDto findById(Integer id) {

        return convertToResponseDto(getStudentById(id));
    }

    public StudentResponseDto updateStudent(Integer id, StudentRequestDto studentRequestDto) {

        Student existingStudent = getStudentById(id);

        existingStudent.setFirstName(studentRequestDto.getFirstName());
        existingStudent.setLastName(studentRequestDto.getLastName());
        existingStudent.setEmail(studentRequestDto.getEmail());
        existingStudent.setDepartment(getDepartmentById(studentRequestDto.getDepartmentId()));
        existingStudent.setCgpa(studentRequestDto.getCgpa());
        existingStudent.getLibraryCard().setIssuedBooks(studentRequestDto.getIssuedBooks());

        return convertToResponseDto(studentRepository.save(existingStudent));
    }

    public StudentResponseDto save(StudentRequestDto requestDto) {

        Student student = convertToEntity(requestDto);

        Student savedStudent = studentRepository.save(student);

        return convertToResponseDto(savedStudent);
    }

    public void deleteById(Integer id) {

        Student student = getStudentById(id);

        studentRepository.delete(student);
    }

    public StudentResponseDto findByEmail(String email) {

        Student student = studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student with email = " + email + " not found"));

        return convertToResponseDto(student);
    }

    public List<StudentResponseDto> findByDepartment(Integer departmentId) {

        return studentRepository
                .findByDepartment(getDepartmentById(departmentId))
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public List<StudentResponseDto> searchByFirstName(String keyword) {

        return studentRepository
                .findByFirstNameContainingIgnoreCase(keyword)
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public List<StudentResponseDto> findByCgpa(String comparator, Double cgpa) {

        comparator = comparator.toLowerCase();

        List<Student> students = switch (comparator) {
            case "gt" -> studentRepository.findByCgpaGreaterThan(cgpa);
            case "lt" -> studentRepository.findByCgpaLessThan(cgpa);
            case "eq" -> studentRepository.findByCgpa(cgpa);
            default -> throw new IllegalArgumentException("Invalid comparator");
        };

        return students
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public List<StudentResponseDto> findByDepartmentAndCgpaGreaterThan(Integer departmentId, Double cgpa) {

        return studentRepository
                .findByDepartmentAndCgpaGreaterThan(getDepartmentById(departmentId), cgpa)
                .stream()
                .map(this::convertToResponseDto)
                .toList();
    }

    public LibraryCardResponse findLibraryCard(Integer studentId) {

        Student student = getStudentById(studentId);

        LibraryCard libraryCard = student.getLibraryCard();

        return convertToResponseDto(student, libraryCard);
    }
}
