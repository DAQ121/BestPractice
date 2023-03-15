package guava;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;

public class testCollection {


    /**
     * 差集，并集，交集
     */
    @Test
    public void testSet() {
        HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView<Integer> union = Sets.union(setA, setB);
        System.out.println("并集:" + union);

        Sets.SetView<Integer> difference = Sets.difference(setA, setB);
        System.out.println("前差集:" + difference);

        Sets.SetView<Integer> intersection = Sets.intersection(setA, setB);
        System.out.println("交集:" + intersection);
    }

    @Test
    public void immutableCollection() {
        final ImmutableList<Integer> immutableList = ImmutableList.of(23, 24);
    }
}
