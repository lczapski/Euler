package stream.problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=20
 */
public class Problem20 {

    /**
     * n! means n × (n − 1) × ... × 3 × 2 × 1
     * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
     * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
     *
     * Find the sum of the digits in the number 100!
     */
    public void doIt() {
        xx(10);
        xx(100);
    }

    private void xx(int from) {
        BigInteger silnia = IntStream.range(1, from + 1)
                .mapToObj(i -> BigInteger.valueOf(i)).reduce(BigInteger.ONE, (x, y) -> (x.multiply(y)));
        int sum = Arrays.asList(silnia.toString().split("")).stream().map(s -> Integer.parseInt(s)).reduce(0, (x, y) -> (x + y)).intValue();
        System.out.println(sum);
    }
}
