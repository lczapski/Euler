package stream.problems;

import utils.Para;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * https://projecteuler.net/problem=7
 */
public class Problem7 {
    /**
     * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
     * What is the 10 001st prime number?
     */
    public void doIt() {

        List<Para<Long, Integer>> collect = LongStream.range(2, 200000)
                .mapToObj(i -> new Para<Long, Integer>(i, getPodzielniki(i).size()))
                .filter(x -> x.v2 == 0)
                .limit(10001)
                .collect(Collectors.toList());
//                .forEach(x -> System.out.println(x));
        System.out.println("collect: "+ collect.get(10001-1));

    }

    private List<Long> getPodzielniki(long it) {
        List<Long> contain = new ArrayList<>();
        long i = 2;
        while(i < it) {
            if (contain.contains(i)) {
                break;
            }
            if (it % i == 0) {
                long n = it / i;
                contain.add(i);
                contain.add(n);

            }
            i++;
        }
        return contain;
    }

}
