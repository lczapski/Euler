package stream.problems;

import utils.Para;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=30
 */
public class Problem30 {
    /**
     *
     * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

     1634 = 14 + 64 + 34 + 44
     8208 = 84 + 24 + 04 + 84
     9474 = 94 + 44 + 74 + 44
     As 1 = 14 is not a sum it is not included.

     The sum of these numbers is 1634 + 8208 + 9474 = 19316.

     Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
     */
    public void doIt() {
        int s = 5;
        Double limit = IntStream.range(2, 20)
                .mapToObj(i -> new Para<Double, Double>(
                        Double.parseDouble(IntStream.range(0, i).mapToObj(x -> "0").reduce("1", (x, y) -> x + y)),
                        IntStream.range(0, i).mapToObj(x -> Math.pow(9, s)).reduce(1d, (x, y) -> x + y)))
                .filter(p -> p.v1 < p.v2)
                .map(p -> p.v1 * 10)
                .max((x, y) -> (int)(x - y))
                .get();
        System.out.println(limit);

        BigInteger sum = IntStream.range(2, limit.intValue())
                .parallel()
                .mapToObj(i -> new Para<BigInteger, BigInteger>(BigInteger.valueOf(i),
                        createList(i).stream().map(x -> pow(x, s)).reduce(BigInteger.ZERO, (x, y) -> x.add(y))))
                .filter(p -> p.v1.equals(p.v2))
                .map(p -> p.v1)
                .reduce(BigInteger.ZERO, (x, y) -> x.add(y));
        System.out.println(sum);

    }

    private BigInteger pow (int v, int c){
        return IntStream.range(1, c + 1).mapToObj(i -> BigInteger.valueOf(v)).reduce(BigInteger.ONE, (x, y) -> x.multiply(y));
    }

    private List<Integer> createList(int i) {
        return Arrays.asList((""+i).split("")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
    }
}
