package Java8.Stream;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: daiaoqi
 * @date: 2022/9/18
 **/
public class MapDemo {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();

        map.put("name", "daiaoqi");
        map.put("age", 23);
        map.put("sex", "man");

        // 直接遍历map，无返回
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        // 操作map中的数据，有返回
        map.entrySet().stream().map(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            return "";
        }).collect(Collectors.toList());



    }
}
