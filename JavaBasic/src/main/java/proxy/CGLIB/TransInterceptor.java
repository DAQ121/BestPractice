package proxy.CGLIB;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TransInterceptor implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before do something");
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("After do something");
        return result;
    }
}
