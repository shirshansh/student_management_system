package com.studentmanagement.service;

import com.studentmanagement.dto.StudentRequestDto;
import com.studentmanagement.dto.StudentResponseDto;
import com.studentmanagement.entity.Student;
import com.studentmanagement.exception.StudentNotFoundException;
import com.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
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

    public List<StudentResponseDto> findAll() {

        return studentRepository
                .findAll()
                .stream()
                .map(this::convertToResponseDto)
                .toList();
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
