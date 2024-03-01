package org.miu.cs471.studentgrades;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DataGenerator {

    public static List<Student> generateStudentList(int size){
        Student[] list = new Student[size];
        return Arrays.stream(list).map(student-> {
            student = new Student();
            student.setStudentId(new Random().nextLong(4000,5000));
            student.setDepartmentId(new Random().nextLong(100,115));
            student.setCourseNumber(new Random().nextLong(100,1000));
            student.setCredit(4.0);
            student.setCourseDate(LocalDate.of(new Random().nextInt(2000,2023),new Random().nextInt(1,13),1));
            student.setGrade(Grade.values()[new Random().nextInt(6)].getGrade());
            return student;
        }).collect(Collectors.toList());
    }

    public enum Grade{
        A(4.0),B(3.6),C(3.3),D(3.0),E(2.6),F(2.3);
        double grade;
        Grade(double grade){
            this.grade=grade;
        }
        public double getGrade() {
            return grade;
        }
    }
}
