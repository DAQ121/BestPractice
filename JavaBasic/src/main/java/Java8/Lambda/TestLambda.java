package Java8.Lambda;

import DTO.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * <p>Title: TestLabmda</p>
 * <p>Description: 测试lambda表达式</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/8/28</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class TestLambda {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(Student.builder().name("daiaoqi").age(23).sex("man").grade(80).build());
        students.add(Student.builder().name("wutong").age(25).sex("woman").grade(78).build());
        students.add(Student.builder().name("kanghaike").age(19).sex("woman").grade(98).build());
        students.add(Student.builder().name("jieweicheng").age(23).sex("man").grade(89).build());
        students.add(Student.builder().name("jieweicheng").age(23).sex("man").grade(89).build());

        TestLambda testLambda = new TestLambda();

        // 测试类的实例方法引用
        testLambda.testConsumer(students);

        // 类的静态方法引用
        students.stream().forEach(TestLambda::testStaticMethod);

        // 对象的实例方法引用
        students.stream().forEach(testLambda::testObjectRef);

    }

    /**
     * @Description 类的实例方法引用
     * @param students
     * @Return
     */
    public void testConsumer(List<Student> students){
        Consumer<Student> consumer = new Consumer<Student>() {
            @Override
            public void accept(Student student) {
                System.out.println(student.getAge());
            }
        };

        students.stream()
                .peek(consumer)
                .collect(Collectors.toList());
    }

    /**
     * @Description 对象的实例方法引用
     * @param student
     * @Return
     */
    public void testObjectRef(Student student) {
        System.out.println(student.getGrade());
    }

    /**
     * @Description 类的静态方法应用
     * @param student
     * @Return
     */
    public static void testStaticMethod(Student student) {
        System.out.println(student.getGrade());
    }

}
