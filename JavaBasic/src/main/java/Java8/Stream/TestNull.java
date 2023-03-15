package Java8.Stream;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestNull {

    public static void main(String[] args) {
        final ConcurrentHashMap<Object, Map<String,String>> map = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, String> map1 = new ConcurrentHashMap<>();
        map1.put("name","daq");
        map.put("123", map1);
        if (!map.isEmpty()&& map.get("1231")!=null){
            map.get("1231").remove("name");
            System.out.println(map);
        }

    }
}
