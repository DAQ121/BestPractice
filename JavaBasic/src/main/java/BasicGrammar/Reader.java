package BasicGrammar;

public interface Reader {

    // 常量
    String NAME = "daiaoqi";

    // 抽象方法,接口中定义的所有变量或者方法，都会自动添加上 public 关键字
    abstract void readConfig();


    /**
     * 静态方法
     * 1.实现类无法调用，只能通过接口名来调用。
     * 2.目的是为了提供一种简单的机制，使我们不必创建对象就能调用方法，从而提高接口的竞争力
     */
    static void readTable() {
        System.out.println("静态方法必须要有方法体");
    }

    /**
     * 默认方法,提供默认实现
     * 1. 子类可以直接调用
     * 2. 子类也可以覆写，自己实现
     */
    default void ReaderDefaultFunc() {
        System.out.println("默认方法也要有方法体，这是父类的方法");
    }
}
