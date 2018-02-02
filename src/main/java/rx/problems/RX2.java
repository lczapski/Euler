package rx.problems;

import utils.DoIt;
import rx.Observable;
import stream.problems.Problem2;

import java.util.stream.Stream;

public class RX2 {
    public void doIt() {

        new Problem2().doIt();

        System.out.println("--------");

        DoIt.forEvery(100, 4000000).doIt(
                (l) -> fibonacciWithMax(l)
                        .filter(i -> (i % 2 == 0))
                        .reduce((x, y) -> x + y)
                        .subscribe(System.out::println)
        );

    }

    private Observable<Integer> fibonacciWithMax(int limit) {
        Stream<Integer[]> intStream = Stream.iterate(new Integer[]{1, 1}, p -> new Integer[]{p[1], p[0] + p[1]});
        return Observable.from(() -> intStream.iterator())
                .takeWhile(n -> n[1] < limit)
                .map(n -> n[1]);
    }

}
