package stream.problems;

import utils.Commons;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=23
 */
public class Problem23 {
    /**
     A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
     For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

     A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

     As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of
     two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater than 28123 can be written as
     the sum of two abundant numbers. However, this upper limit cannot be reduced any further by analysis even though
     it is known that the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.

     Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
     */
    public void doIt() {

        System.out.println(sumOfDivisors(12));
        System.out.println(sumOfDivisors(28));
        System.out.println(Commons.getPodzielniki(4));

        List<Integer> abun = IntStream.range(1, 28123 + 1).filter(i -> sumOfDivisors(i) > i)
                .mapToObj(i -> i).collect(Collectors.toList());

        System.out.println(canIt(24, abun));
        System.out.println(canIt(25, abun));


        System.out.println("--");

        long value = LongStream.range(1, 28123 + 1)
                .parallel()
                .filter(i -> !canIt(i, abun))
                .mapToObj(i -> BigInteger.valueOf(i))
                .reduce(BigInteger.ZERO, (x, y) -> (x.add(y)))
                .longValue();

        System.out.println(value);
    }

    private boolean canIt(long val, List<Integer> abun) {
        List<Integer> list = abun.stream().filter(i -> i < val).collect(Collectors.toList());
        List<Integer> revers = new ArrayList<>(list);
        Collections.reverse(revers);
//        System.out.println(list);
        for(Integer i : list) {
            for (Integer j : revers) {
                if (j + i == val) {
                    return true;
                }
            }
        }
        return false;
    }

    private long sumOfDivisors(long val) {
        return Commons.getPodzielniki(val).stream().reduce(1l,(x, y) -> (x + y)).longValue();
    }
}
