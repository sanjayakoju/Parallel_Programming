package org.miu.cs471.studentgrades;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentGradesMain {
    public static void main(String[] args) {
        List<Student> studentList = DataGenerator.generateStudentList(100000);

        Long start = System.currentTimeMillis();
        Map<Long,Double> stringDoubleMap = studentList.stream()
                .collect(
                        Collectors.groupingBy(Student::getStudentId,
                                Collectors.averagingDouble(Student::getGrade))
                );

        DoubleSummaryStatistics summaryStatistics = studentList.stream()
                .collect(Collectors.groupingBy(Student::getStudentId, Collectors.averagingDouble(Student::getGrade)))
                .entrySet().stream().collect(Collectors.summarizingDouble(entry-> entry.getValue()));
//        System.out.println("Max: "+summaryStatistics.getMax());
//        System.out.println("Min:" +summaryStatistics.getMin());

        Map<Long,Double> averageGPAEachDepartment = studentList.stream()
                .collect(Collectors.groupingBy(Student::getDepartmentId, Collectors.averagingDouble(Student::getGrade)));

        Map<Long,Double> averageGPAEachCourse = studentList.stream()
                .collect(Collectors.groupingBy(Student::getCourseNumber, Collectors.averagingDouble(Student::getGrade)));
        Long stop = System.currentTimeMillis();
        Long serialTime = stop-start;
        System.out.println("Serial Execution Time: "+serialTime);



        start = System.currentTimeMillis();
        Map<Long,Double> averageGPAEachStudentParallel = studentList.parallelStream()
                .collect(
                        Collectors.groupingBy(Student::getStudentId,
                                Collectors.averagingDouble(Student::getGrade))
                );

        DoubleSummaryStatistics summaryStatisticsParallel = studentList.parallelStream()
                .collect(Collectors.groupingBy(Student::getStudentId, Collectors.averagingDouble(Student::getGrade)))
                .entrySet().parallelStream().collect(Collectors.summarizingDouble(entry-> entry.getValue()));
//        System.out.println("Max: "+summaryStatistics.getMax());
//        System.out.println("Min:" +summaryStatistics.getMin());

        Map<Long,Double> averageGPAEachDepartmentParallel = studentList.parallelStream()
                .collect(Collectors.groupingBy(Student::getDepartmentId, Collectors.averagingDouble(Student::getGrade)));

        Map<Long,Double> averageGPAEachCourseParallel = studentList.parallelStream()
                .collect(Collectors.groupingBy(Student::getCourseNumber, Collectors.averagingDouble(Student::getGrade)));
        stop = System.currentTimeMillis();
        Long parallelTime = stop-start;
        System.out.println("Parallel Execution Time: "+parallelTime);
        System.out.println("SpeedUp: "+(double)serialTime/parallelTime);
    }

}
