package Java8.Lambda;

import java.util.concurrent.ConcurrentHashMap;

public class testComputer {

    static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        map.put("daq","1253");

        String newValue = map.compute("daq", (key, value) -> {
            if (value.equals("123")) {
                value = "321";
            } else {
                value = "111";
            }
            return value;
        });

        System.out.println("===" + newValue);


        // 如果key不存在,则将新key value put进map, put进去的值返回
        // 存在，就保持原值
//        Object daq = map.computeIfAbsent("daq1", v -> "1111");
//        daq = "22222";
//        map.put("daq", "1111");
//        System.out.printf(daq.toString());


        // 如果key不存在，则返回null，
        // 如果存在，则返回通过重新计算后的值。
//        final Object o = map.computeIfPresent("qqq", (k, v) -> "123123");
//
//        System.out.printf(o.toString());

        ConcurrentHashMap<String, ConcurrentHashMap<String, String>> realDataBucket = new ConcurrentHashMap<>();
        realDataBucket.put("topic", map);

        realDataBucket.compute("topic", (key, stringStringConcurrentHashMap) -> null);

//
//        ConcurrentHashMap<String, String> bucket = realDataBucket.get("topic");
//        bucket.put("stationName", "message");
////        realDataBucket.put(topic, bucket);
//
//
//        System.out.println("=======");
//
//
//        final ConcurrentHashMap<String, ConcurrentHashMap<String, String>> map1 = new ConcurrentHashMap<>();
//        final ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<>();
//
//        map2.put("daq","123");
//
//        map1.put("kk", map2);

    }


}
