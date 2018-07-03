package stream.problems;

import utils.Para;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=34
 */
public class Problem34 {
    /**
     * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

     Find the sum of all numbers which are equal to the sum of the factorial of their digits.

     Note: as 1! = 1 and 2! = 2 are not sums they are not included.
     */
    public void doIt() {


        Map<Integer, Long> allFactorial = new HashMap<>();
        IntStream.range(0, 10).forEach(i -> {
            allFactorial.put(i, factorial(i));
        });

        System.out.println(allFactorial);


        IntStream.range(11, 900000)
                .mapToObj(x -> new Para<Integer, List<Integer>>(x, toList(x, new ArrayList<Integer>())))
                .map(p -> new Para<Long, Para<Long, List<Integer>>>((long)p.v1, new Para<Long, List<Integer>>(
                        p.v2.stream()
                        .map(i -> factorial(i))
                        .reduce(0L, (a, b) -> a + b)
                        .longValue(), p.v2)
                ))
                .filter(p -> p.v1.equals(p.v2.v1))
                .forEach(System.out::println);
    }

    private List<Integer> toList(int x, List<Integer> list) {
        if (x < 10) {
            list.add(x);
            return list;
    }

        int last = x % 10;
        int rest = x / 10;
        list.add(last);
        return toList(rest, list);
    }

    public static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
