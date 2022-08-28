package Java8.Function;

import DTO.Student;

import javax.swing.table.TableRowSorter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p>Title: TestFunction</p>
 * <p>Description: 函数式接口</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/8/28</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class TestFunction {
    public static void main(String[] args) {
        TestFunction testFunction = new TestFunction();

        System.out.println("测试Consumer消费型函数");
        testFunction.testConsumer();

        System.out.println("测试Supplier供给型函数");
        testFunction.testSupplier();

        System.out.println("测试Function函数式接口");
        testFunction.testFunc();

        System.out.println("测试Predicate");
        testFunction.testPredicate();

        System.out.println("测试predicate实现的通用过滤");
        testFunction.predicateFilter();

        System.out.println("测试自定义的函数式接口");
        Student student = Student.builder().name("daiaoqi").age(23).build();
        testFunction.caustomFunc(student, (s) -> System.out.println("这是自定义的函数式接口实现逻辑"));



    }

    /**
     * @Description Consumer消费型接口
     * @param
     * @Return
     */
    public void testConsumer() {

        Consumer<Integer> action1 = i -> {
            System.out.println(i);
        };

        Consumer<Integer> action2 = i -> {
            System.out.println(i + 3);
        };

        // 先执行action1的消费，再执行action2的消费
        action1.andThen(action2)
                .accept(3);

    }

    /**
     * @Description Supplier供给型接口，无参数，有返回值，适合提供数据的场景
     * @param
     * @Return
     */
    public void testSupplier() {
        Supplier<Integer> supplier = () -> {
            Random random = new Random();
            return random.nextInt(3);
        };

        System.out.println(supplier.get());
    }

    public void testFunc() {
        Function<Student, Integer> func1 = (student) -> student.getAge();

        Function<Integer, Integer> func2 = (age) -> age + 1;

        Student student = Student.builder().name("daq").age(23).grade(99).build();

        // 先执行compose方法参数before中的apply方法，然后将执行结果传递给调用compose函数中的apply方法在执行
        // 如下：先执行func1，在把返回结果当做func2的入参执行。
        System.out.println(func2.compose(func1).apply(student));

        // 如下：先执行func1，在把返回结果当做func2的入参执行。
        System.out.println(func1.andThen(func2).apply(student));
    }

    public void testPredicate() {

        Student stu = Student.builder().name("daq").age(23).grade(99).build();

        Predicate<Student> predicate1 = (student) -> student.getAge() > 23;
        Predicate<Student> predicate2 = (student) -> student.getGrade() > 90;

        System.out.println(predicate1.test(stu));
        System.out.println(predicate1.and(predicate2).test(stu));
        System.out.println(predicate1.or(predicate2).test(stu));
        System.out.println(predicate1.negate());
    }

    /**
     * @Description 使用predicate实现的通用过滤
     * @param
     * @Return
     */
    public void predicateFilter() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<String> words = Arrays.asList("a", "ab", "abc");

        filter(numbers, x -> (int)x % 2 == 0);
        filter(words, x -> ((String)x).length() > 1);
    }

    public static void filter(List list, Predicate condition) {
        list.forEach(x -> {
            if (condition.test(x)) {
                System.out.println("这里是对应的业务逻辑");
            }
        });
    }

    /**
     * @Description 测试自定义函数式接口
     * @param
     * @Return
     */
    public void caustomFunc(Student s, MyFunction myFunction) {
        myFunction.show(s);
    }


}
