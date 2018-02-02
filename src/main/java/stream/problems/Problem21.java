package stream.problems;

import utils.Commons;

import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=21
 */
public class Problem21 {
    /**
     * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
     * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
     *
     * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
     * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
     *
     * Evaluate the sum of all the amicable numbers under 10000.
     */
    public void doIt() {
        System.out.println(divisors(220));
        System.out.println(divisors(284));
        System.out.println(divisors(1));
        System.out.println(divisors(6));
        System.out.println(divisors(496));

        System.out.println("------");
        long sum = LongStream.range(1, 10001).filter(i -> divisors(i) != i && divisors(divisors(i)) == i)
                .sum();

        System.out.println(sum);
    }

    private Long divisors(long i) {
        return Commons.getPodzielniki(i).stream().reduce(1l, (x, y) -> (x + y));
    }
}
