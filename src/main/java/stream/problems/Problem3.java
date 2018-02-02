package stream.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://projecteuler.net/problem=3
 */
public class Problem3 {

    /**
     * The prime factors of 13195 are 5, 7, 13 and 29.
     * What is the largest prime factor of the number 600851475143 ?
     */

    public void doIt() {
        long it = 600851475143L;

        List<Long> contain = getPodzielniki(it);
        Collections.reverse(contain);
//        System.out.println("contain > " +contain);

        Long find =
                contain.stream()
                .map(i -> new Long[] {i, (long)getPodzielniki(i).size()})
                .filter(n -> n[1] == 0)
//                .forEach(n -> {
//                    System.out.println(" > " +n[0] +" = "+ n[1]);
//                });
                .findFirst().get()[0];


        System.out.println("find > " +find);
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
