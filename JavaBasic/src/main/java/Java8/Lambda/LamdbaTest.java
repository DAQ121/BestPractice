package Java8.Lambda;

/**
 * @description:
 * 内部类就是类内部的类，就相当于是类的一个属性，所以内部类可以访问外部类的所有成员变量，内部类还包括成员/局部/静态/匿名内部类
 * 1. 成员内部类: 就相当于类的成员
 * 2. 局部内部类: 方法内定义的类，很少使用
 * 3. 静态内部类: 成员类加static修饰，不用实例化外部类即可调用内部类的方法，静态内部类中也只能访问外部类的静态成员
 * 4. 匿名内部类: 没有名字的内部类，成员内部类的简化写法，匿名内部类中最好只有一个抽象方法，参考Thread创建线程
 *
 * 使用匿名内部类其实就是为了简化编码，不用创建冗余类
 *
 * @author: daiaoqi
 * @date: 2022/9/13
 */
public class LamdbaTest {
    public static void main(String[] args) {
        System.out.println("================调用内部类时,传递的参数可以是 接口 / 抽象类 / 普通类================");
        // 接口
        new Inter() {
            @Override
            public void show() {
                System.out.println("接口Inter");
            }
        }.show();


        // 抽象类
        new People() {
            @Override
            void method() {
                System.out.println("抽象类People");
            }
        }.method();

        // 普通类
        new Employee() {
            @Override
            void work() {
                super.work();
                System.out.println("具体类Employee");
            }
        }.work();

        // 用lambda的写法
        byLambda(() -> System.out.println("采用lambda写法"));

    }

    public static void byLambda(Inter inter){
        inter.show();
    }
}

// 接口
interface Inter {
    void show();
}

// 抽象类
abstract class People {
    abstract void method();
}

// 普通类
class Employee {
    void work(){
        System.out.println("雇员要工作");
    }
}


