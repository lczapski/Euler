package stream.problems;

import utils.Commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=42
 */
public class Problem42 {
    /**
     The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:

     1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
s
     By converting each letter in a word to a number corresponding to its alphabetical position
     and adding these values we form a word value. For example, the word value for SKY is 19 + 11 + 25 = 55 = t10.
     If the word value is a triangle number then we shall call the word a triangle word.

     Using words.txt (right click and 'Save Link/Target As...'), a 16K text file containing nearly two-thousand common
     English words, how many are triangle words?
     */
    public void doIt() throws IOException {
        m3();
    }

    private void m3() throws IOException {

        List<Long> firstN = IntStream.range(1, 100)
                .mapToObj(i -> new Long(i))
                .map(i -> ((i + 1) * i) / 2)
                .collect(Collectors.toList());

        String names = new String(Files.readAllBytes(Paths.get("./src/main/resources/p042_words.txt")));
        List<String> namesA = Arrays
                .asList(names.replace("\"", "").split(",")
                );


        System.out.println(namesA.stream()
                .map(s -> getNumebr(s))
                .filter(n -> firstN.contains(n))
                .collect(Collectors.toList())
                .size()
        );

    }

    private Long getNumebr(String val) {
        return new Long(Arrays.asList(val.split("")).stream()
                .map(c -> Character.getNumericValue(c.charAt(0)) - 9)
                .reduce(0, (a, b) -> a + b));
    }

}
