package stream.problems;

import rx.Observable;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=25
 */
public class Problem25 {
    /**
     * The Fibonacci sequence is defined by the recurrence relation:

     Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
     Hence the first 12 terms will be:

     F1 = 1
     F2 = 1
     F3 = 2
     F4 = 3
     F5 = 5
     F6 = 8
     F7 = 13
     F8 = 21
     F9 = 34
     F10 = 55
     F11 = 89
     F12 = 144
     The 12th term, F12, is the first term to contain three digits.

     What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
     */
    public void doIt() {
        findIt(makeNoumberWithZeros(2));
        findIt(makeNoumberWithZeros(3));
        findIt(makeNoumberWithZeros(999));

    }

    private BigInteger makeNoumberWithZeros(int numerOfZeros) {
        String reduce = IntStream.range(0, numerOfZeros).mapToObj(i -> "0")
                .reduce("1", (x, y) -> x + y);
        return new BigInteger(reduce);
    }

    private void findIt(BigInteger findItsIndex) {
        System.out.println("For: " + findItsIndex);
//        fibonacciWithLess(findItsIndex)
//                .forEach(i -> System.out.println(i));
        fibonacciWithLess(findItsIndex)
                .count()
                .forEach(i -> System.out.println(i + 1 ));
    }

    private Observable<BigInteger> fibonacciWithLess(BigInteger firstLessThanThis) {
        Stream<BigInteger[]> intStream = Stream.iterate(new BigInteger[]{BigInteger.ONE, BigInteger.ONE}, p -> new BigInteger[]{p[1], p[0].add(p[1])});
        return Observable.from(() -> intStream.iterator())
                .takeWhile(n -> n[0].compareTo(firstLessThanThis) < 0)
                .map(n -> n[1]);
    }
}
