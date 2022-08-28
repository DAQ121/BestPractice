package Java8.Stream;

import DTO.Person;
import DTO.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Title: TestFlatMap</p>
 * <p>Description: 测试FlatMap</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/8/27</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class TestMap {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        students.add(Student.builder().name("daiaoqi").age(23).sex("man").grade(80).build());
        students.add(Student.builder().name("wutong").age(25).sex("woman").grade(78).build());
        students.add(Student.builder().name("kanghaike").age(19).sex("woman").grade(98).build());
        students.add(Student.builder().name("jieweicheng").age(23).sex("man").grade(89).build());
        students.add(Student.builder().name("jieweicheng").age(23).sex("man").grade(89).build());


        // map 接收的是一个Function表达式
        students.stream().map(student -> {
            student.setGrade(student.getGrade() + 100);
            return student;
        }).forEach(System.out::println);

        // peek效果和map一样,但是peek接收的是一个consumer表达式,没有返回值, peek是唯一能改变原本对象中元素的操作
        students.stream().peek(student -> student.setGrade(student.getGrade() + 100)).forEach(System.out::println);

        students.stream().forEach(System.out::println);

        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder().age(12).money(10000).name("daq").sex("nan").students(students).build());
        persons.add(Person.builder().age(13).money(12123).name("keke").sex("nv").students(students).build());

        persons.stream().flatMap(person -> person.getStudents().stream())
                .map(Student::getAge)
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }
}
