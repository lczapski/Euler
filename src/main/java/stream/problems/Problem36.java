package stream.problems;

import utils.Commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=36
 */
public class Problem36 {
    /**
     * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

     Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

     (Please note that the palindromic number, in either base, may not include leading zeros.)
     */
    public void doIt() {


        List<Long> palindromic = IntStream.range(1, 1_000_000)
                .parallel()
                .mapToObj(p -> new Long(p))
                .filter(p -> isPalindromic10 (p))
                .filter(p -> isPalindromic2 (p))
                .collect(Collectors.toList());

        System.out.println(palindromic);
        System.out.println("---");
        System.out.println(palindromic.stream().reduce(0L, (x, y) -> x + y));

    }

    private boolean isPalindromic10(Long p) {
        String base = "" + p;
        return isPalindromic(base);
    }
    private boolean isPalindromic2(Long p) {
        String base = "" + Long.toBinaryString(p);
        return isPalindromic(base);
    }

    private boolean isPalindromic(String base) {
        char a[]= base.toCharArray();
        for(int i = 0; i < a.length / 2; i++)
        {
            char temp = a[i];
            a[i] = a[a.length - i - 1];
            a[a.length - i - 1] = temp;
        }
        return new String(a).equals(base);
    }

}
