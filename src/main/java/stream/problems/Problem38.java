package stream.problems;

import rx.Observable;
import utils.Commons;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=38
 */
public class Problem38 {
    /**
     Take the number 192 and multiply it by each of 1, 2, and 3:

     192 × 1 = 192
     192 × 2 = 384
     192 × 3 = 576
     By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated
     product of 192 and (1,2,3)

     The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645,
     which is the concatenated product of 9 and (1,2,3,4,5).

     What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an
     integer with (1,2, ... , n) where n > 1?
     */
    public void doIt() {

        m1();

    }

    private void m1() {

        Long max = IntStream.range(1, 99999999)
                .parallel()
                .filter(s -> Arrays.asList(("" + s).split("")).stream().distinct().filter(c -> !"0".equals(c)).count() == ("" + s).length())
                .mapToObj(p -> IntStream.range(1, (9 / ("" + p).length()) + 1)
                        .mapToObj(x -> "" + (x * p))
                        .reduce("", (a, b) -> (a.length() == 9) ? a : a + b)
                )
                .filter(s -> s.length() == 9)
                .filter(s -> Arrays.asList(s.split("")).stream().distinct().filter(c -> !"0".equals(c)).count() == 9)
                .map(s -> new Long(s))
                .distinct()
                .max((x, y) -> (int) (x - y)).get();
        System.out.println(max);
    }

}
