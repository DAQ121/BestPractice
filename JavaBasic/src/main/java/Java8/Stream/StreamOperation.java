package Java8.Stream;

import DTO.Student;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Title: StreamOperation</p>
 * <p>Description: Stream所有操作</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/20</p>
 * 注意：没有被接收的流，会影响到后续的流操作，foreach不算是接收流
 * @author :daiaoqi
 * @version :1.0.0
 */
public class StreamOperation {

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        students.add(Student.builder().name("daiaoqi").age(23).sex("man").grade(80).build());
        students.add(Student.builder().name("wutong").age(25).sex("woman").grade(78).build());
        students.add(Student.builder().name("kanghaike").age(19).sex("woman").grade(98).build());
        students.add(Student.builder().name("jieweicheng").age(23).sex("man").grade(89).build());
        students.add(Student.builder().name("jieweicheng").age(23).sex("man").grade(89).build());

        Stream<Object> stream = Stream.builder().add("12").add("qq").build();


        Stream.builder().add("12").add("qq").build().forEach(System.out::println);

        // 遍历 foreach
        students.forEach(System.out::println);

        // 匹配 find
        System.out.println(students.stream().filter(x -> x.age > 20).findFirst().get());
        System.out.println(students.stream().filter(x -> x.age > 20).findAny().get());

        // 匹配 match
        System.out.println(students.stream().anyMatch(x -> x.age == 19));
        System.out.println(students.stream().noneMatch(x -> x.age == 19));
        System.out.println(students.stream().allMatch(x -> x.age == 19));

        // 筛选
        students.stream().filter(x -> x.age > 20)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // filter筛选并通过map获取部分属性
        students.stream()
                .filter(x -> x.getAge() > 20)
                .map(Student::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        // 聚合计算（max / min / count）
        System.out.println(students.stream().max(Comparator.comparing(Student::getAge)).get());
        System.out.println(students.stream().min(Comparator.comparing(Student::getAge)).get());
        System.out.println(students.stream().count());

        // 自定义Comparator排序规则
        System.out.println(students.stream().max(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.age.compareTo(o2.age);
            }
        }).get());

        // 映射: 将一个流的元素按照一定的映射规则映射到另一个流中。分为map和flatMap
        // map: 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<Integer> list = students.stream().map(x -> x.age + 3).collect(Collectors.toList());

        students.stream().map(x -> x.age = x.age + 3).collect(Collectors.toList()).forEach(System.out::println);

        // flatMap: 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        students.stream().flatMap(x -> {
            x.age += 3;
            Stream<Student> studentStream = students.stream();
            return studentStream;
        }).collect(Collectors.toList()).forEach(System.out::println);


        // 规约reduce 也称缩减，顾名思义，是把一个流缩减成一个值，能实现对集合求和、求乘积和求最值操作。
        System.out.println(students.stream().reduce((x, y) -> {
            y.grade =  x.grade + y.grade;
            return y;
        }).get());

        // 分组
        students.stream().collect(Collectors.groupingBy(x -> x.grade > 80)).get(true).forEach(System.out::println);

//        // 多级分组 (先根据年龄分组，在根据性别分组)
        final Map<Integer, List<Student>> man = students.stream().collect(Collectors.groupingBy(Student::getSex, Collectors.groupingBy(Student::getAge)))
                .get("man");
        System.out.println(man);

        // 合并，去重，限制，跳过
        students.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
        students.stream().limit(1).collect(Collectors.toList()).forEach(System.out::println);
        students.stream().skip(2).collect(Collectors.toList()).forEach(System.out::println);

    }

}
