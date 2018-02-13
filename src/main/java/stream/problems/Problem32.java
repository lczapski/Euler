package stream.problems;

import utils.Para;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=32
 */
public class Problem32 {
    /**
     * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigital.

     The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

     Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

     HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
     */
    public void doIt() {
        int limit = 10000;
        Integer sum = IntStream.range(1, limit)
                .parallel()
                .mapToObj(i -> IntStream.range(1, limit).mapToObj(j -> new Para<Integer, Integer>(i, j)))
                .flatMap(p -> p)
                .map(p -> new Para<Para<Integer, Integer>, String>(p, ("" + (p.v1 * p.v2) + p.v1 + p.v2)))
                .filter(p -> p.v2.length() == 9)
                .map(p -> {
                    String s = Arrays.asList(p.v2.split("")).stream().sorted().reduce("", (x, y) -> x + y);
                    return new Para<Para<Integer, Integer>, String>(p.v1, s);
                })
                .filter(p -> p.v2.equals("123456789"))
                .map(p -> p.v1.v1 * p.v1.v2)
                .distinct()
                .reduce(0, (x, y) -> x + y);

        System.out.println(sum);
    }
}
