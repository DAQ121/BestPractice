package proxy.Dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * PersonInvocationHandler 类需要实现InvocationHandler接口，这个类中持有一个被代理对象(委托类)的实例target。该类别JDK Proxy类回调
 * 重写了 InvocationHandler 接口中的invoke方法，当一个代理实例的方法被调用时，代理方法将被编码并分发到 invoke 方法执行。
 */

public class DynamicProxy<T> implements InvocationHandler {

    /**
     * 被代理对象引用，invoke 方法里面 method 需要使用这个被代理对象
     */
    T target;

    public DynamicProxy(T target) {
        this.target = target;
    }

    /**
     * @param proxy  代表动态生成的 动态代理 对象实例
     * @param method 代表被调用委托类的接口方法，和生成的代理类实例调用的接口方法是一致的，它对应的Method 实例
     * @param args   代表调用接口方法对应的Object参数数组，如果接口是无参，则为null； 对于原始数据类型返回的他的包装类型。
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("调用代理类方法之前的处理逻辑");
        // 调用被代理对象的真实方法
        Object result = method.invoke(target, args);
        System.out.println("调用代理类方法之后的处理逻辑");

        return result;
    }
}