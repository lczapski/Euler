package stream.problems;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=24
 */
public class Problem24 {

    /**
     * A permutation is an ordered arrangement of objects.
     * For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
     * If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
     * The lexicographic permutations of 0, 1 and 2 are:
     *
     * 012   021   102   120   201   210
     *
     * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
     */
    public void doIt() {

        int size = 10;
        List<Integer> base = IntStream.range(0, size).mapToObj(i -> i).collect(Collectors.toList());

        List<String> lit = permutation(base, size);
        System.out.println(lit.get(1000000 - 1 ));
    }

    private List<String> permutation(List<Integer> base, int size) {
        if (size == 1) {
            return base.stream().map(i -> "" + i).collect(Collectors.toList());
        }
        List<String> append =  permutation(base, size - 1);
        List<String> result = base.stream().flatMap(i -> append.stream()
                .filter(s -> !s.contains(""+i))
                .map(s -> "" + i + s)).collect(Collectors.toList());
        return result;
    }
}
