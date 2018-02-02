package stream.problems;

import stream.problems.Problem18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * https://projecteuler.net/problem=67
 */
public class Problem67 {
    /**
     * Find the maximum total from top to bottom in triangle.txt (right click and 'Save Link/Target As...'),
     * a 15K text file containing a triangle with one-hundred rows.

     NOTE: This is a much more difficult version of Problem 18.
     It is not possible to try every route to solve this problem, as there are 299 altogether!
     If you could check one trillion (1012) routes every second it would take over twenty billion years to check them all.
     There is an efficient algorithm to solve it. ;o)
     */

    public void doIt() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("./src/main/resources/p067_triangle.txt"));
        lines.stream().forEach(System.out::println);

        new Problem18().doWithStrings(lines);
    }
}
