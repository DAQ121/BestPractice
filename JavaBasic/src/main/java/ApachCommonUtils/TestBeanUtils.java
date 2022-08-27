package ApachCommonUtils;

import DTO.Person;
import DTO.Student;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: TestBeanUtils</p>
 * <p>Description: </p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/8/11</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class TestBeanUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        
        // 深克隆
//        testClone();
        
        // 对象拷贝
//        testCopyProperties();

        // 指定对象的属性进行拷贝
        testCopyProperty();

        // map转对象
//        mapToObject();

        // 对象转map
//        objectToMap();

        // 获取对象中数据类型为数组的属性数据
//        testGetArrayProperty();

    }


    /**
     * @Description 深克隆一个新对象
     * @param
     * @Return
     */
    private static void testClone() {
        Person person = new Person();
        person.setName("daiaoqi");
        person.setAge(22);

        try {
            // 深克隆
            Person person1 =(Person) BeanUtils.cloneBean(person);
            System.out.println(person1);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description 根据属性匹配,将一个对象的属性值复制给另一个对象,如果属性不匹配,则需要手动处理,常用于对象之间的值传递。
     * @param
     * @Return
     */
    public static void testCopyProperties(){

        Student student = new Student();
        student.setName("daq");
        student.setAge(22);

        Person person = new Person();
        person.setName("keke");
        person.setAge(18);
        person.setMoney(10000);

        try {
            // 将后者属性orig复制给前者dest,根据属性名称做匹配
            BeanUtils.copyProperties(student, person);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(student);
        System.out.println(person);
    }


    /**
     * @Description 指定属性，赋值，一般用于无法调用setget方法时，大多数时候用不到
     * @param
     * @Return
     */
    public static void testCopyProperty() throws InvocationTargetException, IllegalAccessException {

        Student student = new Student();
        student.setName("daq");
        student.setAge(22);

        BeanUtils.copyProperty(student,"grade", 100);

        System.out.println(student);

    }

    /**
     * @Description 将map转成对象，前提是map中的key与对象的属性相对应
     * @param
     * @Return
     */
    public static void mapToObject() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "daiaoqi");
        map.put("age", 22);

        Person person = new Person();
        try {
            BeanUtils.populate(person, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(person);
    }

    /**
     * @Description 对象转map,对象中空属性，到map对应的key是null
     * @param
     * @Return
     */
    public static void objectToMap(){
        Person person = new Person();
        person.setName("daiaoqi");
        person.setAge(22);
        Map<String, String> map = new HashMap<>();
        try {
            map = BeanUtils.describe(person);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.print(map);
    }

    /**
     * @Description 获取一个对象中，类型为数组的属性数据，只针对于数组中是String类型，如果是其他类型，则会转为String
     * @param
     * @Return
     */
    public static void testGetArrayProperty() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Person person = new Person();
        final Student daq = Student.builder().name("daq").age(22).build();
        final Student keke = Student.builder().name("keke").age(23).build();
        person.setStudents(Arrays.asList(daq,keke));

        String[] names = BeanUtils.getArrayProperty(person, "products");

        Arrays.stream(names).forEach(name ->{
            System.out.println(name);
        });
    }
}
