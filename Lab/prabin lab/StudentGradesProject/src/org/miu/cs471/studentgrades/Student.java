package org.miu.cs471.studentgrades;

import java.time.LocalDate;

public class Student {
    private Long studentId;
    private Long departmentId;
    private Long courseNumber;
    private LocalDate courseDate;
    private Double credit;
    private Double grade;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(Long courseNumber) {
        this.courseNumber = courseNumber;
    }

    public LocalDate getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(LocalDate courseDate) {
        this.courseDate = courseDate;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", departmentId=" + departmentId +
                ", courseNumber=" + courseNumber +
                ", courseDate=" + courseDate +
                ", credit=" + credit +
                ", grade=" + grade +
                '}';
    }
}
