package Generic;

import DTO.Student;

/**
 * <p>Title: GenericClassTest</p>
 * <p>Description: 泛型类测试</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/7/3</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class GenericClassTest {
    public static void main(String[] args) {

        // 引用类型
        Student student = Student.builder().age(10).build();
        GenericClass<Student> g1 = new GenericClass<>(student);
        Student key1 = g1.getKey();

        // 包装类型也算引用类型
        GenericClass<String> g2 = new GenericClass<>("daiaoqi");
        GenericClass<Integer> g3 = new GenericClass<>(123);
        String key2 = g2.getKey();

        // 没有指定类型,则按照Object处理
        GenericClass g4 = new GenericClass(student);
        Object key3 = g4.getKey();


        // ---------- 泛型类实际应用 ----------
        ProductPool<Prize> pl = new ProductPool<>();
        pl.setProduct(new Prize.Car());
        pl.setProduct(new Prize.Iphone());

        System.out.println(pl.getProduct());
    }
}
