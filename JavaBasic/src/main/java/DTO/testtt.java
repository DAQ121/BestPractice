//package DTO;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * <p>Title: testtt</p>
// * <p>Description: </p>
// * <p>Company: www.h-visions.com</p>
// * <p>create date: 2022/7/14</p>
// *
// * @author :daiaoqi
// * @version :1.0.0
// */
//public class testtt {
//    public static void main(String[] args) {
//        final ArrayList<Student> objects = new ArrayList<>();
//        Student student = new Student();
//        student.setName("daq");
//        student.setAge(19);
//        final ArrayList<Student> objects1 = new ArrayList<>();
//        Student student1 = new Student();
//        student1.setAge(19);
//
//        objects.add(student);
//        objects.add(student1);
//
//        final List<Student> collect = objects.stream().filter(stu -> stu.getName() == null).collect(Collectors.toList());
//        System.out.println(collect);
//    }
//}
