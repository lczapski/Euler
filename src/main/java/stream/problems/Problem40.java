package stream.problems;

import utils.Para;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=40
 */
public class Problem40 {
    /**
     An irrational decimal fraction is created by concatenating the positive integers:

     0.123456789101112131415161718192021...

     It can be seen that the 12th digit of the fractional part is 1.

     If dn represents the nth digit of the fractional part, find the value of the following expression.

     d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
     */
    public void doIt() {
        m3();
    }

    private void m3() {

        List<Para<Integer, String>> allTens = new ArrayList();
        IntStream.range(1, 1000000)
                .mapToObj(x -> new Para<Integer, String>(0,"" + x))
                .reduce(new Para<Integer, String>(1,""), (a, b) -> {
                    String val = a.v2 + b.v2;
                    Integer index = a.v1;
                    if (val.length() > 10) {
                        allTens.add(new Para<Integer, String>(index,val.substring(0, 10)));
                        val = val.substring(10, val.length());
                        index += 1;
                    }
                    return new Para<Integer, String>(index,val);
                });

        List<Integer> toFind = Arrays.asList(1, 10, 100, 1000, 10000, 100000);
        List<Para<Integer, String>> find = allTens.stream().filter(p -> toFind.contains(p.v1)).collect(Collectors.toList());

        System.out.println(find);
        int value = find.stream().map(p -> new Integer(p.v2.substring(9))).reduce(1, (a, b) -> a * b).intValue();
        System.out.println(value);

    }

}
