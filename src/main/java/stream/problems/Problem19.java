package stream.problems;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

/**
 * https://projecteuler.net/problem=19
 */
public class Problem19 {
    /**
     * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
     */
    public void doIt() {
//
        Long countSun = IntStream.range(1901, 2001)
                .mapToObj(i -> IntStream.range(1, 13)
                        .mapToObj(j -> "" + i + "/" + j + "/1")
                        .filter(s -> isSun(s))
                        .count())
                .reduce(0L, (x, y) -> (x + y));

        System.out.println(countSun);
        System.out.println(isSun("1995/1/1"));
        System.out.println(run());
    }

    DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);

    private boolean isSun( String s) {
        try {
            Date parse = format.parse(s);
//            System.out.println(parse);
            return parse.toString().startsWith("Sun");
        } catch (ParseException e) {
            return false;
        }
    }


    private void print( String s) {
        try {
//            System.out.println(s);
            Date parse = format.parse(s);
            System.out.println(parse);
        } catch (ParseException e) {
        }
    }

    public String run() {
        int count = 0;
        for (int y = 1901; y <= 2000; y++) {
            for (int m = 1; m <= 12; m++) {
                if (dayOfWeek(y, m, 1) == 0)  // Sunday
                    count++;
            }
        }
        return Integer.toString(count);
    }


    // Return value: 0 = Sunday, 1 = Monday, ..., 6 = Saturday.
    private static int dayOfWeek(int year, int month, int day) {
        if (year < 0 || year > 10000 || month < 1 || month > 12 || day < 1 || day > 31) {
            System.out.println(year);
            System.out.println(month);
            System.out.println(day);
            throw new IllegalArgumentException();
        }

        // Zeller's congruence algorithm
        int m = (month - 3 + 4800) % 4800;
        int y = (year + m / 12) % 400;
        m %= 12;
        return (y + y/4 - y/100 + (13 * m + 2) / 5 + day + 2) % 7;
    }

}
