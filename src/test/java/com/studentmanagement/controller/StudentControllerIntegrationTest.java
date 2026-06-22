package com.studentmanagement.controller;

import com.studentmanagement.dto.StudentRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class StudentControllerIntegrationTest {

    private final String API_PREFIX = "/api/students";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnStudentWhenIdExists() throws Exception {

        mockMvc.perform(get(API_PREFIX + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Shirshansh"))
                .andExpect(jsonPath("$.lastName").value("Mishra"))
                .andExpect(jsonPath("$.email").value("shirshansh.mishra@college.com"))
                .andExpect(jsonPath("$.departmentName").value("Computer Science and Engineering"))
                .andExpect(jsonPath("$.cgpa").value(9.43))
                .andExpect(jsonPath("$.issuedBooks").value(5));
    }

    @Test
    void shouldReturn404WhenStudentDoesNotExist() throws Exception {

        mockMvc.perform(get(API_PREFIX + "/99999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message")
                        .value("Student with id = 99999 not found"));
    }

    @Test
    void shouldCreateStudentWhenValidRequestIsProvided() throws Exception {

        StudentRequestDto request = new StudentRequestDto(
                "Shim",
                "Chan",
                "shim.chan@college.com",
                1,
                8.5,
                3
        );

        String requestJson =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post(API_PREFIX)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("Shim"))
                .andExpect(jsonPath("$.lastName").value("Chan"))
                .andExpect(jsonPath("$.email").value("shim.chan@college.com"))
                .andExpect(jsonPath("$.departmentName").value("Computer Science and Engineering"))
                .andExpect(jsonPath("$.cgpa").value(8.5))
                .andExpect(jsonPath("$.issuedBooks").value(3));
    }

    @Test
    void shouldReturn500WhenRequestIsInvalid() throws Exception {

        StudentRequestDto request = new StudentRequestDto(
                "Shim",
                "Chan",
                "shim.chan@college.com",
                1,
                8.5,
                -3
        );

        String requestJson =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        post(API_PREFIX)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500));
    }

    @Test
    void shouldUpdateStudentWhenValidRequestIsProvided() throws Exception {

        StudentRequestDto request = new StudentRequestDto(
                "Mihs",
                "Nahc",
                "mihs.nahc@college.com",
                1,
                9.5,
                0
        );

        String requestJson =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        put(API_PREFIX + "/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Mihs"))
                .andExpect(jsonPath("$.lastName").value("Nahc"))
                .andExpect(jsonPath("$.email").value("mihs.nahc@college.com"))
                .andExpect(jsonPath("$.departmentName").value("Computer Science and Engineering"))
                .andExpect(jsonPath("$.cgpa").value(9.5))
                .andExpect(jsonPath("$.issuedBooks").value(0));
    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingStudent() throws Exception {

        StudentRequestDto request = new StudentRequestDto(
                "Mihs",
                "Nahc",
                "mihs.nahc@college.com",
                1,
                9.5,
                0
        );

        String requestJson =
                objectMapper.writeValueAsString(request);

        mockMvc.perform(
                        put(API_PREFIX + "/99999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void shouldDeleteStudentWhenIdExists() throws Exception {

        mockMvc.perform(
                        delete(API_PREFIX + "/1")
                )
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenDeletingNonExistingStudent() throws Exception {

        mockMvc.perform(
                        delete(API_PREFIX + "/99999")

                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }
}
