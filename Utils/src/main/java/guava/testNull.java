package guava;

import com.google.common.base.Strings;
import org.junit.Test;

import java.util.HashMap;

import static java.util.Optional.ofNullable;

public class testNull {

    private String flag;


    @Test
    public void testOptions() {
        HashMap<Object, String> map = new HashMap<>();
        map.put("name","daq");

        Object age = map.get("age");

        Object res = ofNullable(age).orElse("hhh");

        ofNullable(map.get("age"))
                .ifPresent(url -> {
                    flag = url;
                });

        final String age1 = Strings.nullToEmpty(map.get("age"));
        final String s = Strings.commonPrefix("1", "2");

        System.out.println(s);


    }

}
