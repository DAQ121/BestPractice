package Java8.Function;

import java.util.HashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Utility {

    public static void main(String[] args) {
        // 方法重试
        //http调用，失败会重试3次

        //把数字1转成数字 失败会重试三次
//        String s = retryFunction(String::valueOf, 1, 3);
//        String ss = retryFunction(String::valueOf, 1, 3);
        HashMap<String, String> daq = retryFunction(Utility::toMap, "daq", 3);
    }

    public static HashMap<String, String> toMap(String str) {

        HashMap<String, String> map = new HashMap<>();
        map.put(str, "value");
        System.out.println(map);
        return map;
    }

    public static <T, R> R retryFunction(Function<T, R> function, T t, int time) {
        while (true) {
            try {
                return function.apply(t);
            } catch (Exception e) {
                time--;
                if (time <= 0) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static <T, U, R> R retryFunction(BiFunction<T, U, R> function, T t, U u, int time) {
        while (true) {
            try {
                return function.apply(t, u);
            } catch (Exception e) {
                time--;
                if (time <= 0) throw new RuntimeException(e);
            }
        }
    }

}
