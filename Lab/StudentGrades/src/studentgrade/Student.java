package studentgrade;

import java.util.Date;

public class Student {

    int studentId;
    String departmentId;
    String courseNumber;
    Date courseDate;
    double credits;
    double grade;

    public Student(int studentId, String departmentId, String courseNumber, Date courseDate, double credits, double grade) {
        this.studentId = studentId;
        this.departmentId = departmentId;
        this.courseNumber = courseNumber;
        this.courseDate = courseDate;
        this.credits = credits;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", departmentId='" + departmentId + '\'' +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseDate=" + courseDate +
                ", credits=" + credits +
                ", grade=" + grade +
                '}';
    }
}
