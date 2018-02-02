package stream.problems;

import java.util.Optional;
import java.util.stream.LongStream;

public class Problem10 {
    public void doIt() {

        Optional<Long> sum =
                LongStream.range(2, 2000000)
                .parallel()
                .mapToObj(i -> new Para<Long, Boolean>(i, isPrime(i)))
                .filter(x -> x.v2)
                .map(x -> x.v1)
//                .forEach(System.out::println)
                .reduce((x, y) -> x + y)
                ;

        System.out.println(sum);

    }

    private boolean isPrime(long it) {
        if (it == 2 || it == 3){
            return true;
        }
        if (it % 2 == 0 || it % 3 == 0){
            return false;
        }
        long i = 3;
        long half = (it / 2) + 10;
        while(i < it) {
            if (it % i == 0) {
                return false;

            }
            if (i > half) {
                break;
            }
            i = i + 2;
        }
        return true;
    }

    private static class Para<T1,T2>{
        final T1 v1;
        final T2 v2;

        public Para(T1 v1, T2 v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override
        public String toString() {
            return "Para{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    '}';
        }
    }
}
