package rx.problems;

import utils.Commons;
import rx.Observable;
import stream.problems.Problem4;

import java.util.stream.Stream;

public class RX4 {
    public void doIt() {

        new Problem4().doIt();

        System.out.println("--------");
        reversRange(100000, 10000000)
                .map(n -> ""+n)
                .map(s -> new String[] {s.substring(0, s.length() / 2), s.substring(s.length() / 2, s.length())})
                .filter(s -> new StringBuilder(s[1]).reverse().toString().equals(s[0]))
                .map(s -> new Integer[]{Integer.valueOf(s[0] + s[1]),
                        (int) Commons.getPodzielnikiPara(Long.valueOf(s[0] + s[1]))
                                .stream().filter(p -> (""+p.v1).length() == 3 && (""+p.v2).length() == 3)
                                .count()})
                .filter(i -> i[1] > 0)
                .first()
                .map(i -> i[0])
                .forEach(System.out::println);
    }

    private Observable<Integer> reversRange(int from, int to) {
        Stream<Integer> intStream = Stream.iterate(to, p -> p - 1);
        return Observable.from(() -> intStream.iterator())
                .takeWhile(n -> n > from)
                .map(n -> n );
    }

}
