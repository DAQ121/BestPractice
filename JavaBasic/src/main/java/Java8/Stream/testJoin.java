package Java8.Stream;

import java.util.ArrayList;
import java.util.List;

public class testJoin {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        strings.add("a");
        strings.add("b");
        strings.add("c");

        String delimiter = "#";

        String collect = String.join("#", strings);
        System.out.println(collect);

        String first = "daq";

        System.out.println(String.join(delimiter, collect, first));

    }
}
