package stream.problems;

import utils.Para;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=31
 */
public class Problem31 {
    /**
     * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:

     1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
     It is possible to make £2 in the following way:

     1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
     How many different ways can £2 be made using any number of coins?
     */
    public void doIt() {
        List<Integer> allows = Arrays.asList(2,5,10,20, 50, 100, 200);
        List<Integer> max = IntStream.range(0, 200).mapToObj(i -> new Integer(1)).collect(Collectors.toList());
        List<List<Integer>> init = new ArrayList<>();
        init.add(max);
        List<String> all = new ArrayList<>();
        all.add(max.stream().map(i -> ""+i).reduce("", (x, y) -> x + y));

        List<List<Integer>> a = init;
        int index = 200;
        while (index != 0) {
            System.out.println("i:"+index +" s: "+a.size());

            a = a.stream()
                    .parallel()
                    .map(i -> changeWithOne(i, allows))
                    .flatMap(p -> p.stream())
                    .collect(Collectors.toList());

//            a.subList(0, 5).stream().forEach(System.out::println);

            List<Para<List<Integer>, String>> collect = a.stream()
                    .parallel()
                    .map(l -> new Para<List<Integer>, String>(l, l.stream().map(i -> "" + i).reduce("", (x, y) -> x + "_" + y)))
                    .filter(p -> !all.contains(p.v2))
                    .distinct()
                    .collect(Collectors.toList());

            a = collect.stream().map(p -> p.v1).collect(Collectors.toList());

            all.addAll(collect.stream().map(p -> p.v2).collect(Collectors.toList()));
            index--;
        }


        System.out.println(all.size());

//        Collections.sort(all);
//        all.stream()
//                .filter(s -> s.contains("50_50_50"))
//                .forEach(System.out::println);

//                System.out.println(getFirstAllowed( 50,getFirstAllowed( 50,getFirstAllowed( 50, getFirstAllowed(50,max )))));

    }

    private List<List<Integer>> changeWithOne(List<Integer> current, List<Integer> allows) {
       return allows.stream()
               .map(v -> getFirstAllowed(v, current))
               .filter(l -> l.size() > 0)
               .collect(Collectors.toList());
    }

    private List<Integer> getFirstAllowed(Integer v, List<Integer> current) {
        List<Integer> result = new ArrayList<>();
        if (current.size() >=  v && current.get(v - 1).equals(1)) {
            if (!v.equals(200))
                result.addAll(current.subList(v , current.size()));
            result.add(v);
            Collections.sort(result);
        }
//        System.out.println(current);
//        System.out.println(current.size());
        return result;
    }
}
