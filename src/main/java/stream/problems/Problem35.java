package stream.problems;

import utils.Commons;
import utils.Para;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=35
 */
public class Problem35 {
    /**
     * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

     There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

     How many circular primes are there below one million?
     */
    public void doIt() {


        List<Long> fiteredPrimes = IntStream.range(2, 1_000_000)
                .parallel()
                .mapToObj(p -> new Long(p))
                .filter(p -> !conntainMultiplay(p, 2))
                .filter(p -> !conntainMultiplay(p, 5))
                .filter(p -> isPrime(p))
                .collect(Collectors.toList());

        System.out.println(fiteredPrimes);
        System.out.println("---");
        List<Long> values = fiteredPrimes.stream()
                .filter(x -> isCircularPrime(x, fiteredPrimes))
                .collect(Collectors.toList());

        System.out.println(conntainMultiplay(1253L, 5));

        System.out.println(values);
        System.out.println(values.size());

//        System.out.println(createPossibleValuesRotate(197L));

    }

    private boolean isCircularPrime(Long x, List<Long> fiteredPrimes) {
        List<Long> possibleValues = createPossibleValuesRotate(x);
        for (Long l : possibleValues){
            if (!fiteredPrimes.contains(l)){
                return false;
            }
        }
        return true;
    }

    private List<Long> createPossibleValuesRotate(Long x) {
        String core = "" + x;

        List<String> result = new ArrayList<>();
        for (int i = 0; i < core.length(); i++) {
            core = core.substring(1,core.length()) + core.charAt(0);
            result.add(core);
        }

        return new HashSet<>(result).stream().map(s -> new Long(s)).collect(Collectors.toList());
    }

    private List<Long> createPossibleValues(Long x) {
        char a[]= ("" + x).toCharArray();
        List<String> collect = new ArrayList<>();
        printPermutation(a, 0, a.length, collect);
        return new HashSet<>(collect).stream().map(s -> new Long(s)).collect(Collectors.toList());
    }


    private boolean isPrime(Long x) {
        return x >= 0 && (Commons.getPodzielniki(x).size() == 0);
    }

    private boolean conntainMultiplay(Long x, int val) {
        String[] split = ("" + x).split("");
        if (split.length == 1){
            return false;
        }
        for (String s: split){
            if (new Integer(s).intValue() % val == 0) {
                return true;
            }
        }
        return false;
    }

    private static void printPermutation(char[] a, int startIndex, int endIndex, List<String> collect) {

        if (startIndex == endIndex) {//reached end of recursion, print the state of a
//            System.out.println(new String(a));
            collect.add(new String(a));
        }
        else {
            //try to move the swap window from start index to end index
            //i.e 0 to a.length-1
            for (int x = startIndex; x < endIndex; x++) {
                swap(a, startIndex, x);
                printPermutation(a, startIndex + 1, endIndex, collect);
                swap(a, startIndex, x);
            }
        }
    }

    private static void swap(char[] a, int i, int x) {
        char t = a[i];
        a[i] = a[x];
        a[x] = t;
    }

}
