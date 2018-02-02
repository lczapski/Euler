package stream.problems;

import java.util.function.IntToLongFunction;
import java.util.stream.IntStream;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5
 * , we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class Problem1 {

    public void doIt(){
        IntToLongFunction exec = (l) ->  IntStream.range(1, l).filter(i -> (0 == i % 3 || i % 5 == 0)).sum();

        System.out.println(exec.applyAsLong(10));
        System.out.println(exec.applyAsLong(1000));
    }
}
