package NPE;

import DTO.Student;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class testNPE {

    public static void main(String[] args) {
//        System.out.println("=====字符串判空=====");
//        stringNull();
//
//        System.out.println("=====集合判空=====");
//        collectionNull();
//
        System.out.println("=====对象判空=====");
        objectNull();


        // 使用optional判空
//        testOption();

    }

    // 字符串判空
    public static void stringNull() {
        String str = " ";
        System.out.println(StringUtils.isNotBlank(str)); // 去除空格之后的判断
        System.out.println(StringUtils.isNotEmpty(str)); // 包含空格就不算空
    }

    // 集合判空
    public static void collectionNull() {
         List<String> list = new ArrayList<>();
//         list.add("daq");

         if (!CollectionUtils.isEmpty(list)) {
             System.out.println("not empty !!!");
         } else {
             System.out.println("empty !!!!");
         }
    }

    // 对象判空
    public static void objectNull() {
        Student student = new Student();
//        student.setName("daq");
//        student.setAge(23);

//        // base
//        if (student.getGrade() == null) {
//            System.out.println("null point exception !!!");
//        }

        System.out.println(Objects.isNull(student.getAge()));
    }

    // 使用optional
    public static void testOption() {

        Student student1 = new Student();

        Student student = new Student();
        student.setName("daq");

        // 可以允许为null，不会抛出异常
        Integer age = Optional.ofNullable(student1.getAge()).orElse(student.getAge());
        System.out.println(age);

        // 抛出异常，根本不会进行运算
//        Integer age1 = Optional.of(student1.getAge()).orElse(student.getAge());
//        System.out.println(age1);

        // 抛出异常，还要有异常提示
//        Integer integer = Optional.ofNullable(student.getAge())
//                .orElseThrow(() -> new RuntimeException("student age null for " + student.getName() +";"));

        ArrayList<Object> list = new ArrayList<>();
        list.add(13);
        Optional.ofNullable(list).ifPresent(o -> o.stream().forEach(System.out::println));


    }

}
