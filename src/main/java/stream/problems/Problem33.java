package stream.problems;

import utils.Para;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=33
 */
public class Problem33 {
    /**
     * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
     * <p>
     * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
     * <p>
     * There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.
     * <p>
     * If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
     */
    public void doIt() {
        List<Para> ll = IntStream.range(11, 100)
                .filter(x -> x % 10 != 0)
                .mapToObj(x -> IntStream.range(11, x)
                        .filter(y -> y != x)
                        .filter(y ->
                                (y % 10 == x / 10) ||
                                        (x % 10 == y / 10) ||
                                        (x / 10 == y / 10) ||
                                        (x % 10 == y % 10))
                        .mapToObj(y -> new Para<>(x, y)).collect(Collectors.toList()))
                .flatMap(a -> a.stream())
                // find all pairs of digits (non-trivial)
                .flatMap(p -> Arrays.asList(
                                ((p.v2 % 10 == p.v1 / 10) ? new Para<Integer, Integer>(p.v1 % 10, p.v2 / 10) : null),
                                ((p.v1 % 10 == p.v2 / 10) ? new Para<Integer, Integer>(p.v1 / 10, p.v2 % 10) : null),
                                ((p.v1 / 10 == p.v2 / 10) ? new Para<Integer, Integer>(p.v1 % 10, p.v2 % 10) : null),
                                ((p.v1 % 10 == p.v2 % 10) ? new Para<Integer, Integer>(p.v1 / 10, p.v2 / 10) : null)
                        ).stream()
                                .filter(p1 -> p1 != null)
                                .filter(p1 -> ((double) p1.v2 / p1.v1 == (double) p.v2 / p.v1))
                                .distinct()
                                .map(x -> new Para(p, x))
                )
                // find all curious fraction and its simple form
                .collect(Collectors.toList());


        int top = ll.stream().map(p -> ((Para<Integer, Integer>) p.v2).v2)
                .reduce(1, (x, y) -> x * y)
                .intValue();
        int bottom = ll.stream().map(p -> ((Para<Integer, Integer>) p.v2).v1)
                .reduce(1, (x, y) -> x * y)
                .intValue();
        System.out.println(top);
        System.out.println(bottom);
        System.out.println("val > "+ (bottom / top));
        ll.forEach(System.out::println);
    }
}
