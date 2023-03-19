package proxy.Static;

import proxy.Car;

/**
 * <p>Title: Main</p>
 * <p>Description: 测试</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/22</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class StaticProxyTest {

    public static void main(String[] args) {

        // 一个静态代理类只能代理一个class
        RunProxy proxy = new RunProxy(new Car());
        proxy.run();
    }
}
