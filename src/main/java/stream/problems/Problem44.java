package stream.problems;

import utils.Commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=43
 */
public class Problem43 {
    /**
     The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order,
     but it also has a rather interesting sub-string divisibility property.

     Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:

     d2d3d4=406 is divisible by 2
     d3d4d5=063 is divisible by 3
     d4d5d6=635 is divisible by 5
     d5d6d7=357 is divisible by 7
     d6d7d8=572 is divisible by 11
     d7d8d9=728 is divisible by 13
     d8d9d10=289 is divisible by 17
     Find the sum of all 0 to 9 pandigital numbers with this property.
     */
    public void doIt() {
        m3();
    }

    private void m3() {

        List<Long> all = createPoslible(IntStream.range(0, 10).mapToObj(i -> new Long(i))
                .collect(Collectors.toList()));
//
//        List<Long> all = Arrays.asList("1406357289", "0146357289", "2891406357").stream().map(s -> new Long(s))
//                .collect(Collectors.toList());

        List<Long> collect = all.stream().filter(l -> test(l))
                .sorted()
                .collect(Collectors.toList());
        System.out.println(collect);

        System.out.println(collect.stream().reduce(0L, (a, b) -> a + b));

    }

    private static boolean test(Long l) {
        String s = ""+l;
        if (s.length() == 9 && !s.contains("0")) {
            s = "0" + s;
        }
        String sf = s;

        BooleanSupplier b1 = () -> new Integer(sf.substring(1, 4)) % 2 == 0;
        BooleanSupplier b2 = () -> new Integer(sf.substring(2, 5)) % 3 == 0;
        BooleanSupplier b3 = () -> new Integer(sf.substring(3, 6)) % 5 == 0;
        BooleanSupplier b4 = () -> new Integer(sf.substring(4, 7)) % 7 == 0;
        BooleanSupplier b5 = () -> new Integer(sf.substring(5, 8)) % 11 == 0;
        BooleanSupplier b6 = () -> new Integer(sf.substring(6, 9)) % 13 == 0;
        BooleanSupplier b7 = () -> new Integer(sf.substring(7, 10)) % 17 == 0;

        if (s.length() < 4) {
            return true;
        }
        if (s.length() == 4 && allTest(b1)) {
            return true;
        }
        if (s.length() == 5 && allTest(b1, b2)) {
            return true;
        }
        if (s.length() == 6 && allTest(b1, b2, b3)) {
            return true;
        }
        if (s.length() == 7 && allTest(b1, b2, b3, b4)) {
            return true;
        }
        if (s.length() == 8 && allTest(b1, b2, b3, b4, b5)) {
            return true;
        }
        if (s.length() == 9 && allTest(b1, b2, b3, b4, b5, b5)) {
            return true;
        }
        if (s.length() == 10 && allTest(b1, b2, b3, b4, b5, b6, b7)) {
            return true;
        }
        return false;
    }

    private static boolean allTest(BooleanSupplier ... b) {
        for (BooleanSupplier v : b) {
            if (!v.getAsBoolean()){
                return false;
            }
        }
        return true;
    }

    private List<Long> createPoslible(List<Long> a) {
        if (a.size() == 1) {
            return  a;
        } else {
            return a.stream()
                    .parallel()
                    .map(x -> createOther(x, a.stream().filter(u -> u != x).collect(Collectors.toList())))
                    .flatMap(x -> x.stream())
                    .filter(l ->  test(l))
                    .distinct()
                    .collect(Collectors.toList());
        }
    }

    private List<Long> createOther(Long x, List<Long> a) {
        if (a.size() == 1) {
            Long val = a.get(0);
            return Arrays.asList(new Long("" + val + x));
        }
        return a.stream()
                .parallel()
                .map(y -> createOther(y, a.stream().filter(u -> u != y).collect(Collectors.toList())))
                .flatMap(y -> y.stream())
                .flatMap(xx -> {
                    Long val = xx;
                    return Arrays.asList( new Long("" + val + x)).stream();
                })
                .filter(l ->  test(l))
                .collect(Collectors.toList());
    }
}
