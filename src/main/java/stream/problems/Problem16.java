package stream.problems;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=16
 */
public class Problem16 {

    /**
     * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
     * What is the sum of the digits of the number 2^1000?
     */
    public void doIt() {
        xx(15);
        xx(1000);
    }

    private void xx(int base) {
        String v15 = IntStream.range(0, base).mapToObj(i -> new BigInteger("2"))
                .reduce(BigInteger.ONE, (x, y) -> (x.multiply(y))).toString();
        System.out.println(v15);
        long longValue = Arrays.asList(v15.split("")).stream().map(s -> Long.parseLong(s))
                .reduce(0L, (x, y) -> (x + y))
                .longValue();
        System.out.println(longValue);
    }
}
