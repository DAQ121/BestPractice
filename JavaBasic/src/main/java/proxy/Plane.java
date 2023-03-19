package proxy;

/**
 * <p>Title: Plane</p>
 * <p>Description: 飞机实现类</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/22</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class Plane implements Transportation {

    @Override
    public void run() {
        System.out.println("天上的飞机");
    }
}
