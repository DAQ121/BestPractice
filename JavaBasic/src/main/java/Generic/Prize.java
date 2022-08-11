package Generic;

/**
 * <p>Title: Prize</p>
 * <p>Description: 奖品</p>
 * <p>Company: www.h-visions.com</p>
 * <p>create date: 2022/7/3</p>
 *
 * @author :daiaoqi
 * @version :1.0.0
 */
public class Prize {
    /**
     * <p>Title: Car</p>
     * <p>Description: 汽车</p>
     * <p>Company: www.h-visions.com</p>
     * <p>create date: 2022/7/3</p>
     *
     * @author :daiaoqi
     * @version :1.0.0
     */
    public static class Car extends Prize{
        private String name = "本田汽车";

        @Override
        public String toString() {
            return "Car{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    /**
     * <p>Title: Iphone</p>
     * <p>Description: 奖品手机</p>
     * <p>Company: www.h-visions.com</p>
     * <p>create date: 2022/7/3</p>
     *
     * @author :daiaoqi
     * @version :1.0.0
     */
    public static class Iphone extends Prize{
        private String name = "Iphone13";

        @Override
        public String toString() {
            return "Iphone{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
