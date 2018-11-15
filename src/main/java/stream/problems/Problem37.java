package stream.problems;

import rx.Observable;
import utils.Commons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=37
 */
public class Problem37 {
    /**
     * The number 3797 has an interesting property. Being prime itself, it is possible to continuously
     * remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7.
     * Similarly we can work from right to left: 3797, 379, 37, and 3.

     Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

     NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
     */
    public void doIt() {

//        m2();
        m12();
//        m1();

    }

    private void m2() {

        List<Long> values = IntStream.range(2, 1_000_000)
                .parallel()
                .mapToObj(p -> new Long(p))
                .filter(p -> !conntainMultiplay(p, 2))
                .filter(p -> !conntainMultiplay(p, 5))
                .filter(p -> isPrime(p))
                .filter(x -> x > 10)
                .filter(x -> isTruncatablePrimes(x))
                .collect(Collectors.toList());
        System.out.println(values);
        System.out.println(values.size());
    }

    private void m12() {
        List<Long> collector = new ArrayList<>();
        int limit = 11;
        List<Long> values = IntStream.range(2, 1_000_000)
                .parallel()
                .mapToObj(p -> new Long(p))
                .filter(p -> !conntainMultiplay(p, 2))
                .filter(p -> !conntainMultiplay(p, 5))
                .filter(p -> isPrime(p))
                .filter(x -> x > 10)
                .filter(x -> isTruncatablePrimes(x))
                .collect(Collectors.toList());

        Observable.from(() -> values.stream().iterator())
                .takeWhile(n -> collector.size() < limit)
                .forEach(n -> collector.add(n));

        System.out.println(collector);
        System.out.println(collector.size());
        System.out.println(collector.stream().reduce(0L, (x, y) -> x + y));
    }

    private void m1() {
        List<Long> collector = new ArrayList<>();
        int limit = 11;
        Stream<Long> intStream = Stream
                .iterate(2L, p -> p + 1L)
                .parallel()
                .filter(p -> !conntainMultiplay(p, 2))
                .filter(p -> !conntainMultiplay(p, 5))
                .filter(p -> isPrime(p))
                .filter(x -> x > 11)
                .filter(x -> isTruncatablePrimes(x))
                ;
        Observable.from(() -> intStream.iterator())
                .takeWhile(n -> collector.size() < limit)
                .forEach(n -> collector.add(n));

        System.out.println(collector);
        System.out.println(collector.size());
    }

    private void fibonacciWithMax(int limit, List<Long> collector) {
        Stream<Long> intStream = Stream
                .iterate(2L, p -> p + 1L)
                .filter(p -> !conntainMultiplay(p, 2))
                .filter(p -> !conntainMultiplay(p, 5))
                .filter(p -> isPrime(p))
                .filter(x -> x > 10)
                .filter(x -> isTruncatablePrimes(x))
                ;
        Observable.from(() -> intStream.iterator())
                .takeWhile(n -> collector.size() < limit)
                .forEach(n -> collector.add(n));
    }


    private boolean isTruncatablePrimes(Long x) {
        List<Long> possibleValues = createTruncatablePrimes(x);
        for (Long l : possibleValues){
            if (l == 1L || !isPrime(l)){
                return false;
            }
        }
        return true;
    }

    private List<Long> createTruncatablePrimes(Long x) {
        String base = "" + x;
        String core = base;

        List<String> result = new ArrayList<>();
        for (int i = 0; i < base.length()-1; i++) {
            core = core.substring(1,core.length());
            result.add(core);
        }
        core = base;
        for (int i = 0; i < base.length()-1; i++) {
            core = core.substring(0,core.length()-1);
            result.add(core);
        }

        return new HashSet<>(result).stream().map(s -> new Long(s)).collect(Collectors.toList());
    }

    private boolean conntainMultiplay(Long x, int val) {
        String[] split = ("" + x).split("");
        if (split.length == 1){
            return false;
        }
        for (int i = 1; i < split.length; i++) {
            String s = split[i];
            if (new Integer(s).intValue() % val == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isPrime(Long x) {
        return x >= 0 && (Commons.getPodzielniki(x).size() == 0);
    }

}
