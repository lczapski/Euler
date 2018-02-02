package rx.problems;

import utils.Commons;
import rx.Observable;
import stream.problems.Problem3;

import java.util.Collections;
import java.util.List;

public class RX3 {
    public void doIt() {

        new Problem3().doIt();

        System.out.println("--------");
        long it = 600851475143L;

        List<Long> contain = Commons.getPodzielniki(it);
        Collections.sort(contain);
        Collections.reverse(contain);
        System.out.println("contain > " +contain);
        Observable.from(contain)
                .map(i -> new Long[] {i, (long)Commons.getPodzielniki(i).size()})
                .filter(n -> n[1] == 0)
                .map(n -> n[0])
                .first()
                .subscribe(System.out::println);

    }
}
