package Java8.Lambda;

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
//                super.work();
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


