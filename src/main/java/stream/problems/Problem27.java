package stream.problems;

import rx.Observable;
import utils.Commons;
import utils.Para;

import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=27
 */

public class Problem27 {

    /**
     * Euler discovered the remarkable quadratic formula:

     n2+n+41n2+n+41
     It turns out that the formula will produce 40 primes for the consecutive integer values 0≤n≤390≤n≤39.
     However, when n=40,402+40+41=40(40+1)+41n=40,402+40+41=40(40+1)+41 is divisible by 41, \
     and certainly when n=41,412+41+41n=41,412+41+41 is clearly divisible by 41.

     The incredible formula n2−79n+1601n2−79n+1601 was discovered, which produces 80 primes for the
     consecutive values 0≤n≤790≤n≤79. The product of the coefficients, −79 and 1601, is −126479.

     Considering quadratics of the form:

     n2+an+bn2+an+b, where |a|<1000|a|<1000 and |b|≤1000|b|≤1000

     where |n||n| is the modulus/absolute value of nn
     e.g. |11|=11|11|=11 and |−4|=4|−4|=4
     Find the product of the coefficients, aa and bb, for the quadratic expression that
     produces the maximum number of primes for consecutive values of nn, starting with n=0n=0.
     */
    public void doIt() {
        int limit = 1000;
        Para<Para<Integer, Integer>, Integer> max = IntStream.range(-1 * limit, limit + 1)
                .parallel()
                .mapToObj(x -> IntStream.range(-1 * limit, limit + 1).mapToObj(y -> new Para<Integer, Integer>(x, y)))
                .flatMap(x -> x)

                .map(p -> new Para<Para<Integer, Integer>, Integer>(p, getFirstOnlyPrime(p.v1, p.v2).count().toBlocking().first()))
                .max((p1, p2) -> p1.v2 - p2.v2)

                .get();
        System.out.println(max);
        System.out.println("> "+ (max.v1.v1 * max.v1.v2));

    }

    private Observable<Object> getFirstOnlyPrime(int a, int b) {
        Stream<Integer> intStream = Stream.iterate(0, p -> p + 1);
        return Observable.from(() -> intStream.iterator())
                .map(n -> new Para<Integer, Long>(n, f(a, b, n)))
                .takeWhile(x -> isPrime(x.v2))
                .map(n -> n);
    }

    private boolean isPrime(Long x) {
        return x >= 0 && (Commons.getPodzielniki(x).size() == 0);
    }

    public long f(int a, int b, int i) {
        return i * i + i * a + b;
    }
}
