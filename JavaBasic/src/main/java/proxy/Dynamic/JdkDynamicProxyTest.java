package proxy.Dynamic;

import proxy.Plane;
import proxy.Transportation;

import java.lang.reflect.Proxy;

public class JdkDynamicProxyTest {

    public static void main(String[] args) {

        // 需要代理的原实例
        Plane plane = new Plane();

        // 构造出代理对象
        DynamicProxy<Plane> softwareProxy = new DynamicProxy<>(plane);

        // 根据原对象和代理对象，构造出实例, 但是只能代理接口
        Transportation planeProxy = (Transportation)Proxy.newProxyInstance(
                plane.getClass().getClassLoader(),
                plane.getClass().getInterfaces(),
                softwareProxy);

        // 实际调用的方法
        planeProxy.run();

    }
}