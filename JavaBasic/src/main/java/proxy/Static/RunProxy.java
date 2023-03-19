package proxy.Static;

import proxy.Car;
import proxy.Transportation;

/**
 * <p>Title: RunProxy</p>
 * <p>Description: 代理类</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/22</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class RunProxy implements Transportation {

    private Car car;

    // 在代理类中维持一个被代理类的引用
    public RunProxy(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        // 这里可以加入其他的业务逻辑
        System.out.println("这里是业务逻辑");
        // 这里再去调用被代理类需要做的事
        car.run();
        // 后面加业务逻辑
        System.out.println("之后的业务逻辑");
    }
}
