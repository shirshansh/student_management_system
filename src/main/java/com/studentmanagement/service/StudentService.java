package com.studentmanagement.service;

import com.studentmanagement.dto.StudentPageResponseDto;
import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.StudentNotFoundException;
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
                    "department",
                    "cgpa"
            );

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;
    }

    private Student convertToEntity(StudentRequestDto studentRequestDto) {

        Student student = new Student();

        student.setFirstName(studentRequestDto.getFirstName());
        student.setLastName(studentRequestDto.getLastName());
        student.setEmail(studentRequestDto.getEmail());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setCgpa(studentRequestDto.getCgpa());

        return student;
    }

    private StudentResponseDto convertToResponseDto(Student student) {

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setFirstName(student.getFirstName());
        studentResponseDto.setLastName(student.getLastName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setDepartment(student.getDepartment());
        studentResponseDto.setCgpa(student.getCgpa());

        return studentResponseDto;
    }

    private Student getStudentById(Integer id) {

        return studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id = " + id + " not found"));
    }

    public StudentPageResponseDto findAll(int page, int size, String sortBy, Sort.Direction sortOrder) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {

            throw new IllegalArgumentException(
                    "Invalid sort field: " + sortBy);
        }

        Sort sort = sortOrder.isAscending() ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);

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

    public StudentResponseDto updateStudent(Integer id, StudentRequestDto updatedStudentRequestDto) {

        Student existingStudent = getStudentById(id);

        Student updatedStudent = convertToEntity(updatedStudentRequestDto);

        existingStudent.setFirstName(updatedStudent.getFirstName());
        existingStudent.setLastName(updatedStudent.getLastName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setCgpa(updatedStudent.getCgpa());

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
}
