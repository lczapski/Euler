package stream.problems;

import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=5
 */
public class Problem5 {

    /**
     * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
     * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
     */

    public void doIt() {

        int toFind = IntStream.range(100000, 1000000000).filter(i ->
                IntStream.range(1, 21).map(j -> i % j).filter(j -> j > 0).findFirst().orElse(0) == 0
        ).findFirst().orElse(0);
        System.out.println("toFind: "+ toFind);

    }
}
