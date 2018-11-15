package stream.problems;

import utils.Para;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=39
 */
public class Problem39 {
    /**
     If p is the perimeter of a right angle triangle with integral length sides, {a,b,c},
     there are exactly three solutions for p = 120.

     {20,48,52}, {24,45,51}, {30,40,50}

     For which value of p ≤ 1000, is the number of solutions maximised?
     */
    public void doIt() {

        m1();

    }

    private void m1() {

        Para max = IntStream.range(1, 1001)
                .parallel()
                .mapToObj(q ->

                        new Para(new Long(q),
                                new Long(Arrays.asList(q).stream()
                        .map(x -> IntStream.range(2, x - 1).mapToObj(i ->
                                IntStream.range(1, i - 1).mapToObj(y -> Arrays.asList(x - i, i - y, y).stream().sorted((a, b) -> a - b).collect(Collectors.toList())
                                ).collect(Collectors.toList())
                        )
                                .collect(Collectors.toList()))
                        .flatMap(s -> s.stream())
                        .flatMap(s -> s.stream())
                        .distinct()
                        .filter(s -> Math.pow(s.get(0), 2) + Math.pow(s.get(1), 2) == Math.pow(s.get(2), 2))
                        .count()))
                )
                .filter(x -> (long)(x.v2) > 0)
                .max((a, b) -> (int) ((long)(a.v2) - (long)(b.v2))).get();

        System.out.println(max);

    }

}
