package stream.problems;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=15
 */
public class Problem15 {
    /**
     * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
     * there are exactly 6 routes to the bottom right corner.
     *
     * How many such routes are there through a 20×20 grid?
     */
    public void doIt() {
        int size = 20;
        String min = IntStream.range(1, size).mapToObj(i -> "0").reduce("0", (x, y) -> (x + y));
        String max = min.replace("0", "1");


        Map<Integer, Long> vals = new HashMap<>();
        IntStream.range(0, size + 1)
                .parallel()
                .forEach(ile1 -> vals.put(ile1,
                LongStream.range(Long.parseLong(min, 2), Long.parseLong(max, 2) + 1)
                .parallel()
                .mapToObj(i -> {
                    String b = Long.toBinaryString(i);
                    return (min + b).substring(b.length());
                }).filter(b -> b.replace("0", "").length() + ile1 == size)
                .count()));

        Long count = LongStream.range(Long.parseLong(min, 2), Long.parseLong(max, 2) + 1)
                .parallel()
                .mapToObj(i -> {
                    String b = Long.toBinaryString(i);
                    return (min + b).substring(b.length());
                }).map(s -> {
                    int ile1 = s.replace("0", "").length();
                    return vals.get(ile1);
                }).reduce(0l, (x, y) -> (x + y));
        System.out.println("count : "+count);
    }

}
