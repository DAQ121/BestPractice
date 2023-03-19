package proxy;

/**
 * <p>Ti交通方式tle: Car</p>
 * <p>Description: 汽车实现类</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/5/22</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class Car implements Transportation {

    private String name;

    @Override
    public void run() {
        System.out.println("陆地上的车辆");
    }
}
