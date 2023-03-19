package proxy.CGLIB;

import net.sf.cglib.proxy.Enhancer;
import proxy.Car;
import proxy.Transportation;

/**
 * jdk的动态代理和CGLIB的动态代理的区别：
 * 1. 实现方式不同：Java的动态代理是基于接口的代理，通过反射机制动态生成代理类，在代理类中生成被代理类实现的接口方法的实现，来实现代理。而CGLIB代理是基于继承的代理，它通过生成被代理类的子类，来实现代理。
 * 2. 代理效率不同：由于Java的动态代理是基于接口的代理，它只能代理接口中定义的方法，对于非接口中定义的方法，它无法进行代理。而CGLIB代理可以代理所有的非final的方法，包括private、protected、static等修饰符所修饰的方法，代理效率相对更高。
 * 3. 实现原理不同：Java的动态代理是通过反射机制来实现代理的，而CGLIB代理是通过ASM字节码生成框架来动态生成代理类的。
 *
 * 使用CGLIB代理的情况：
 * 1. 被代理类没有实现任何接口
 * 2. 被代理类实现的接口过多，为每个接口都生成一个代理类过于繁琐
 * 3. 需要代理的方法比较多，且方法调用频繁，使用CGLIB代理的效率会更高
 *
 * Java的动态代理通常适用于以下情况：
 * 1. 被代理类实现了一个或多个接口，而且需要代理的方法只在这些接口中定义
 * 2. 需要代理的方法比较少，使用Java的动态代理的效率足够
 * 3. 需要在运行时动态地生成代理类
 */
public class CGLIBProxyTest {
    public static void main(String[] args) {
        // 代理普通类
//        proxyNormalClass();

        // 代理接口的实现类
//        proxyInterfaceImpl();

        // 代理接口
        proxyInterface();

    }

    public static void proxyNormalClass() {
        Enhancer enhancer = new Enhancer();
        // 需要代理的类
        enhancer.setSuperclass(NormalClass.class);
        // 设置代理类的方法拦截
        enhancer.setCallback(new TransInterceptor());
        // 这是代理普通类
        NormalClass proxy = (NormalClass) enhancer.create();
        proxy.doSomething();
    }

    public static void proxyInterfaceImpl() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Car.class);
        enhancer.setCallback(new TransInterceptor());
        Car carProxy = (Car) enhancer.create();
        carProxy.run();
    }

    public static void proxyInterface() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Transportation.class);
        enhancer.setCallback(new TransInterceptor());
        Transportation transportationProxy = (Transportation)enhancer.create();
        transportationProxy.run();
    }
}
