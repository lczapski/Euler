package stream.problems;

import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=26
 */

public class Problem26 {
    /**
     * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

     1/2	= 	0.5
     1/3	= 	0.(3)
     1/4	= 	0.25
     1/5	= 	0.2
     1/6	= 	0.1(6)
     1/7	= 	0.(142857)
     1/8	= 	0.125
     1/9	= 	0.(1)
     1/10	= 	0.1
     Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.
     Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
     */
    public void doIt() {

        Integer[] integers = IntStream.range(1, 1001)
                .mapToObj(i -> new Integer[]{i, findRecurringCycleValue(1, i)})
                .max((x, y) -> x[1] - y[1]).get();

        System.out.println(integers[0] +" : "+integers[1]);
    }


    public int findRecurringCycleValue(int numerator, int denominator) {
        if (numerator == 0)
            return 0;
        if (denominator == 0)
            return 0;

        String result = "";

        // is result is negative
        if ((numerator < 0) ^ (denominator < 0)) {
            result += "-";
        }

        // convert int to long
        long num = numerator, den = denominator;
        num = Math.abs(num);
        den = Math.abs(den);

        // quotient
        long res = num / den;
        result += String.valueOf(res);

        // if remainder is 0, return result
        long remainder = (num % den) * 10;
        if (remainder == 0)
            return 0;

        // right-hand side of decimal point
        HashMap<Long, Integer> map = new HashMap<Long, Integer>();
        result += ".";
        while (remainder != 0) {
            // if digits repeat
            if (map.containsKey(remainder)) {
                map.get(remainder);
                return map.size();
            }

            // continue
            map.put(remainder, result.length());
            res = remainder / den;
            result += String.valueOf(res);
            remainder = (remainder % den) * 10;
        }

        return 0;
    }
}
