package stream.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://projecteuler.net/problem=18
 */
public class Problem18 {

    String t1 = "" +
            "3\n" +
            "7 4\n" +
            "2 4 6\n" +
            "8 5 9 3";
    String t2 = "" +
            "75\n" +
            "95 64\n" +
            "17 47 82\n" +
            "18 35 87 10\n" +
            "20 04 82 47 65\n" +
            "19 01 23 75 03 34\n" +
            "88 02 77 73 07 63 67\n" +
            "99 65 04 28 06 16 70 92\n" +
            "41 41 26 56 83 40 80 70 33\n" +
            "41 48 72 33 47 32 37 16 94 29\n" +
            "53 71 44 65 25 43 91 52 97 51 14\n" +
            "70 11 33 28 77 73 17 78 39 68 17 57\n" +
            "91 71 52 38 17 14 91 43 58 50 27 29 48\n" +
            "63 66 04 68 89 53 67 30 73 16 69 87 40 31\n" +
            "04 62 98 27 23 09 70 98 73 93 38 53 60 04 23";


    /**
     * By starting at the top of the triangle below and moving to adjacent numbers on the row below,
     * the maximum total from top to bottom is 23.
     *
     *     3
     *    7 4
     *   2 4 6
     *  8 5 9 3
     *
     * That is, 3 + 7 + 4 + 9 = 23.
     *
     */
    public void doIt() {
        compute(t1);
        compute(t2);
    }

    void compute(String base) {
        List<String> strings = Arrays.asList(base.split("\n"));
        doWithStrings(strings);
    }

    void doWithStrings(List<String> strings) {
        Collections.reverse(strings);
        List<Tria> last = new ArrayList<>();

        List<List<Tria>> collect = strings.stream()
                .map(s -> {
                    System.out.println(s);
                    List<Tria> temp = Arrays.asList(s.split(" ")).stream()
                            .map(x -> new Tria(x)).collect(Collectors.toList());
                    if (last.size() > 0) {
                        for (int i = 0; i < temp.size(); i++) {
                            Tria head = temp.get(i);
                            head.t1 = last.get(i);
                            head.t2 = last.get(i + 1);
                            head.computMax();
                        }
                        last.removeAll(last);
                    }
                    last.addAll(temp);
//                    System.out.println("l: " + last);
                    return temp;
                }).collect(Collectors.toList());

//        collect.stream().forEach(System.out::println);

        Tria max = collect.get(collect.size() - 1).get(0);

        System.out.println("maxSumString "+ max.maxSumString);
        System.out.println("maxSumString "+ max.maxSum);
    }

    private static class Tria{
        Tria t1;
        Tria t2;
        long maxSum;
        long initVal;
        String maxSumString;

        public Tria(String s) {
            this.maxSum = Integer.parseInt(s);
            this.initVal = Integer.parseInt(s);
            this.maxSumString = s;
        }

        @Override
        public String toString() {
            return "Tria{" +
                    "t1=" + t1 +
                    ", t2=" + t2 +
                    ", maxSum=" + maxSum +
                    ", maxSumString='" + maxSumString + '\'' +
                    '}';
        }

        public void computMax() {
            if (initVal + t1.maxSum > initVal + t2.maxSum) {
                maxSum =  initVal + t1.maxSum;
                maxSumString = maxSumString + t1.maxSumString;
            } else {
                maxSum =  initVal + t2.maxSum;
                maxSumString = maxSumString + t2.maxSumString;
            }
//            System.out.println("computMax : "+ maxSumString);
        }
    }
}
