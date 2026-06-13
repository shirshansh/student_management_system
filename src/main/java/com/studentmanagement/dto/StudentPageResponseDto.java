package com.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Student Page Response")
public class StudentPageResponseDto {

    @Schema(
            description = "List of Student Response"
    )
    private List<StudentResponseDto> students;

    @Schema(
            description = "Current Page Number",
            example = "0"
    )
    private int currentPage;

    @Schema(
            description = "Total Number of Pages",
            example = "3"
    )
    private int totalPages;

    @Schema(
            description = "Total Number of Elements",
            example = "5"
    )
    private long totalElements;

    @Schema(
            description = "Is Current Page the First Page?",
            example = "true"
    )
    private boolean first;

    @Schema(
            description = "Is Current Page the Last Page?",
            example = "false"
    )
    private boolean last;

    public StudentPageResponseDto() {

    }

    public StudentPageResponseDto(List<StudentResponseDto> students, int currentPage, int totalPages, long totalElements, boolean first, boolean last) {

        this.students = students;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.last = last;
    }

    public List<StudentResponseDto> getStudents() {

        return students;
    }

    public void setStudents(List<StudentResponseDto> students) {

        this.students = students;
    }

    public int getCurrentPage() {

        return currentPage;
    }

    public void setCurrentPage(int currentPage) {

        this.currentPage = currentPage;
    }

    public int getTotalPages() {

        return totalPages;
    }

    public void setTotalPages(int totalPages) {

        this.totalPages = totalPages;
    }

    public long getTotalElements() {

        return totalElements;
    }

    public void setTotalElements(long totalElements) {

        this.totalElements = totalElements;
    }

    public boolean isFirst() {

        return first;
    }

    public void setFirst(boolean first) {

        this.first = first;
    }

    public boolean isLast() {

        return last;
    }

    public void setLast(boolean last) {

        this.last = last;
    }

    @Override
    public String toString() {

        return "StudentPageResponseDto{" +
                "students=" + students +
                ", currentPage=" + currentPage +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                ", first=" + first +
                ", last=" + last +
                '}';
    }
}
