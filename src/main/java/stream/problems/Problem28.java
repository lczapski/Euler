package stream.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=28
 */
public class Problem28 {

    /**
     * Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

     21 22 23 24 25
     20  7  8  9 10
     19  6  1  2 11
     18  5  4  3 12
     17 16 15 14 13

     It can be verified that the sum of the numbers on the diagonals is 101.

     What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?
     */
    public void doIt() {
        System.out.println(createDiagonal(5).stream().reduce(0L, (x, y) -> x + y ));
        System.out.println(createDiagonal(1001).stream().reduce(0L, (x, y) -> x + y ));
    }

    private List<Long> createDiagonal(long size) {
        List<Long> l = new ArrayList<Long>();
        if (size == 1L)
            l.add(1L);
        else {
            l.add(size * size);
            l.add(size * size  - (size - 1));
            l.add(size * size  - (size - 1) - (size - 1));
            l.add(size * size  - (size - 1) - (size - 1) - (size - 1));
            l.addAll(createDiagonal(size - 2));
        }
        return l;

    }
}
