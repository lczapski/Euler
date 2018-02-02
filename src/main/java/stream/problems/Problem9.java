package stream.problems;

/**
 * https://projecteuler.net/problem=9
 */
public class Problem9 {
    /**
     * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
     *
     * a^2 + b^2 = c^2
     * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
     *
     * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
     * Find the product abc.
     */
    public void doIt() {
        System.out.println("sss : "+isPythagorean(3, 4, 5) );
        System.out.println("sss : "+isPythagorean(3, 4, 15) );
        int limit = 1000;
        for (int a = 0; a <= limit; a++) {
            for (int b = 0; b <= (limit - a); b++) {
                for (int c = 0; c <= limit - (a + b); c++) {
                    if ((a + b + c) == limit && isPythagorean(a, b, c)) {
                        System.out.println("> " + a + ", "+ b + ", "+  c + " = "+ (a * b * c));
                    }
                }
            }
        }
    }

    private boolean isPythagorean(int a, int b, int c) {
        if (a < b && b < c) {
            return Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2);
        }
        return false;

    }
}
