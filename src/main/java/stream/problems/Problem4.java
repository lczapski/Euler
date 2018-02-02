package stream.problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=4
 */

public class Problem4 {

    /**
     * A palindromic number reads the same both ways.
     * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
     *
     * Find the largest palindrome made from the product of two 3-digit numbers.
     */

    public void doIt() {

//        System.out.println(">" + (99 * 99));
//        System.out.println(">" + (999 * 999));
//        System.out.println(">" + (999 * 999));
//        System.out.println(">" + isPalindromic(110011));
        Para cos = reversRange(100000, 10000000)
                .filter(i -> isPalindromic(i))
                .mapToObj(i -> new Para<Integer, List<Para<Long, Long>>>(i, getPodzielniki((long) i)))
                .filter((c) -> c.v2.size() > 0)
                .findFirst().get();
//                .forEach(i -> {System.out.println(">" + i);});
        System.out.println("cos>" + cos);
    }

    private IntStream reversRange(int from, int to) {
        return IntStream.range(from, to)
                .map(i -> to - i + from - 1);
    }

    private static class Para<T1,T2>{
        final T1 v1;
        final T2 v2;

        public Para(T1 v1, T2 v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public String toString() {
            return "Para{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    '}';
        }
    }

    private List<Para<Long, Long>> getPodzielniki(long it) {
        List<Para<Long, Long>> contain = new ArrayList<>();
        long i = 2;
        while(i < it) {
            if (contain.contains(i)) {
                break;
            }
            if (it % i == 0) {
                long n = it / i;
                Para<Long, Long> podzielniki = new Para(i, n);
                if (isBothThisRzad(podzielniki, 3)) {
                    contain.add(podzielniki);
                }
            }
            i++;
        }
        return contain;
    }

    static boolean isBothThisRzad(Para<Long, Long> p, int rzad) {
        return rzad == getOrderOfMagnitude(p.v1.intValue()) && rzad == getOrderOfMagnitude(p.v2.intValue());
    }

    private boolean isPalindromic(int i) {
        int oof = getOrderOfMagnitude(i);

        if (oof % 2 != 0){
            return false;
        }

        double splitBy = Math.pow(10, (oof / 2));

        int front = (int)(i / splitBy);
        int end = (int)(i % splitBy);
        int reversFront = revers(front);
        return reversFront == end;
    }

    private int revers(int front) {
        int oom = getOrderOfMagnitude(front);
        int sumaRevers = 0;
        for (int i = 1; i <= oom; i++) {
            double last = (front % 10);
            sumaRevers = sumaRevers + (int)(last * Math.pow(10, oom - i));
            front = (int)(front / 10);
        }
        return sumaRevers;
    }

    private static int getOrderOfMagnitude(int val) {
        if (val / 10 > 0) {
            return getOrderOfMagnitude(val / 10) + 1;
        }
        return 1;
    }
}
