package rx.problems;


import utils.DoIt;
import rx.Observable;
import stream.problems.Problem1;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5
 * , we get 3, 5, 6 and 9. The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class RX1 {
    public void doIt() {


        new Problem1().doIt();

        Function<Integer, Observable<Integer>> rangeOb =
                (l) -> Observable.from(IntStream.range(1, l).mapToObj(i -> new Integer(i)).collect(Collectors.toList()));


        System.out.println("--------");

        DoIt.forEvery(10, 1000).doIt(
                (l) -> rangeOb.apply(l)
                        .filter(i -> (0 == i % 3 || i % 5 == 0))
                        .reduce(0, (a, i) -> a + i)
                        .subscribe(System.out::println)
        );

    }


}
