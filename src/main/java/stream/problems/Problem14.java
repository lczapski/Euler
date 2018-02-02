package stream.problems;

import rx.Observable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * https://projecteuler.net/problem=14
 */
public class Problem14 {
    /**
     * The following iterative sequence is defined for the set of positive integers:
     *
     *     n → n/2 (n is even)
     *     n → 3n + 1 (n is odd)
     *
     * Using the rule above and starting with 13, we generate the following sequence:
     *
     * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
     * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
     *
     * Which starting number, under one million, produces the longest chain?
     *
     * NOTE: Once the chain starts the terms are allowed to go above one million.
     */
    public void doIt() {

//        System.out.println(collatz(113383));

       Map<Integer, Integer> counts = new ConcurrentHashMap<>();
                IntStream.range(2, 1000000)
                        .parallel()
                        .forEach(i -> {
//                            System.out.println("> "+i);
                            Integer value = collatzCount(i, counts);
//                            System.out.println("> "+i +" = "+value);
                            counts.put(i, value);
                        });
//                .max((x, y) -> x.v2 - y.v2);

//        System.out.println(counts);
        Map.Entry<Integer, Integer> max = counts.entrySet().stream().max((e1, e2) -> e1.getValue() - e2.getValue()).get();
        System.out.println(max);
        System.out.println(collatz(max.getKey()));
    }


    private Integer collatzCount(long start, Map<Integer, Integer> counts) {
        Stream<Long> intStream = Stream.iterate(start, p -> {
            if (p % 2 == 0) {
                return p/2;
            }
            return 3 * p + 1;
        });
        int count = 0;
        Iterator<Long> iterator = intStream.iterator();
        while (iterator.hasNext()) {
            Long next = iterator.next();
            count++;
            if (next == 1) {
                return count;
            }
            if (counts.containsKey(next)){
                count = count  + counts.get(next) - 1;
                return count;
            }
        }
        return count;
    }

    private List<Long> collatz(long start) {
        Stream<Long> intStream = Stream.iterate(start, p -> {
            if (p % 2 == 0) {
                return (long)(p/2);
            }
            return (long)(3 * p + 1);
        });
        List<Long> a = new ArrayList<>();
        Iterator<Long> iterator = intStream.iterator();
        while (iterator.hasNext()) {
            Long next = iterator.next();
            a.add(next);
//            System.out.println("next > "+ next);
            if (next == 1 || next < 0) {
                return a;
            }
        }
        return a;
    }
    private Observable<Integer> collatz2(int start) {
        Stream<Integer> intStream = Stream.iterate(start, p -> {
            if (p % 2 == 0) {
                return p/2;
            }
            return 3 * p + 1;
        });
        return Observable.from(() -> intStream.iterator())
                .takeWhile(n -> n != 1);
    }

    private List<Integer> getVals(int i) {
        return Arrays.asList(13, 40, 20, 10, 5, 16, 8, 4, 2, 1);
    }
}
