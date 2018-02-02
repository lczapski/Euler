package stream.problems;

import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=6
 */

public class Problem6 {

    /**
     * The sum of the squares of the first ten natural numbers is,

     1^2 + 2^2 + ... + 10^2 = 385
     The square of the sum of the first ten natural numbers is,

     (1 + 2 + ... + 10)^2 = 55^2 = 3025
     Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

     Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
     */

    public void doIt() {

        System.out.println("Cos: " + getCos(1, 11));
        System.out.println("Cos: " + getCos(1, 101));

    }

    private int getCos(int start, int end) {
        int sum = IntStream.range(start, end).map(i -> i * i).sum();
        int sum1 = IntStream.range(start, end).sum();
        return sum1 * sum1 - sum;
    }
}
