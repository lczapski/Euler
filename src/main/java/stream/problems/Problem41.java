package stream.problems;

import utils.Commons;
import utils.Para;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=41
 */
public class Problem41 {
    /**
     We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once.
     For example, 2143 is a 4-digit pandigital and is also prime.

     What is the largest n-digit pandigital prime that exists?
     */
    public void doIt() {
        m3();
    }

    private void m3() {

        Integer findIt = IntStream.range(1, 10)
                .map(x -> 11 - x)
//                .parallel()
                .mapToObj(x -> createPoslible(IntStream.range(1, x).mapToObj(i -> new Integer(i))
                                .collect(Collectors.toList())).stream()
                                .parallel()
                                .sorted((a, b) -> (b - a))
                                .filter(x1 -> Commons.isPrime(new Long(x1)))
                                .findFirst().orElse(0)
                )
                .filter(x1 -> x1 != 0)
                .findFirst().orElse(0);

        System.out.println(findIt);

    }

    private List<Integer> createPoslible(List<Integer> a) {
        if (a.size() == 1) {
            return  a;
        } else {
            return a.stream()
                    .parallel()
                    .map(x -> createOther(x, a.stream().filter(u -> u != x).collect(Collectors.toList())))
                    .flatMap(x -> x.stream())
                    .distinct()
                    .collect(Collectors.toList());
        }
    }

    private List<Integer> createOther(Integer x, List<Integer> a) {
        if (a.size() == 1) {
            Integer val = a.get(0);
            return Arrays.asList(new Integer("" + x  + val), new Integer("" + val + x));
        }
        return a.stream()
                .parallel()
                .map(y -> createOther(y, a.stream().filter(u -> u != y).collect(Collectors.toList())))
                .flatMap(y -> y.stream())
                .flatMap(xx -> {
                    Integer val = xx;
                    return Arrays.asList(new Integer("" + x  + val), new Integer("" + val + x)).stream();
                })
                .collect(Collectors.toList());
    }

}
