package BasicGrammar;

public interface Writer {

    String NAME = "writer";

    void witeConfig();

    static void printConf() {
        System.out.println("这是writer的静态方法");
    }

    default void WriteDefaultFUnc() {
        System.out.println("这是同名的默认方法");
    }

}
