package stream.problems;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=22
 */
public class Problem22 {

    /**
     * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand first names,
     * begin by sorting it into alphabetical order. Then working out the alphabetical value for each name,
     * multiply this value by its alphabetical position in the list to obtain a name score.
     *
     * For example, when the list is sorted into alphabetical order, COLIN,
     * which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
     * So, COLIN would obtain a score of 938 × 53 = 49714.
     *
     * What is the total of all the name scores in the file?
     */
    public void doIt() throws IOException {
        String names = new String(Files.readAllBytes(Paths.get("./src/main/resources/p022_names.txt")));
        List<String> namesA = Arrays.asList(names.replace("\"", "").split(","));
        Collections.sort(namesA);
        BigInteger all = IntStream.range(0, namesA.size()).mapToObj(i -> BigInteger.valueOf(xx(namesA.get(i)) * (i + 1)))
                .reduce(BigInteger.ZERO, (x, y) -> (x.add(y)));
//        namesA.stream().forEach(System.out::println);
        System.out.println(all);
        System.out.println(xx("COLIN"));
    }

    private Integer xx(String s) {
        byte start = ((byte)'A') - 1;
        int sum = Arrays.asList(s.split("")).stream().map(x -> ((byte)x.charAt(0)) - start).reduce(0, (x, y) -> (x + y)).intValue();
        return sum;
    }
}
