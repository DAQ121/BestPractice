package Generic;

/**
 * <p>Description: 泛型类测试</p>
 * T: 泛型标识--类型形参, 再创建对象的时候指定具体的数据类型
 * 常用的泛型标识符
 *
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class GenericClass<T> {

    private T key;

    public GenericClass(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "GenericClass{" +
                "key=" + key +
                '}';
    }
}
