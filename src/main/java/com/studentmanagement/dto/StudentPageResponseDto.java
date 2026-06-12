package com.studentmanagement.dto;

import java.util.List;

public class StudentPageResponseDto {

    private List<StudentResponseDto> students;

    private int currentPage;

    private int totalPages;

    private long totalElements;

    private boolean first;

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
