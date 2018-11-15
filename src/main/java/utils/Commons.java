package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Commons {

    public static List<Long> getPodzielniki(long it) {
        return getPodzielnikiPara(it).stream().flatMap(p -> Arrays.asList(p.v1, p.v2).stream()).collect(Collectors.toList());
    }

    public static List<Para<Long, Long>> getPodzielnikiPara(long it) {
        List<Para<Long, Long>> contain = new ArrayList<>();
        long i = 2;
        while(i < it) {
            if (contain.contains(i)) {
                break;
            }
            if (it % i == 0) {
                long n = it / i;
                contain.add(new Para(i, n));
            }
            i++;
        }
        return contain;
    }

    public static boolean isPrime(Long x) {
        return x >= 0 && (_isPrime(x));
    }

    public static boolean _isPrime(long it) {
        long i = 2;
        while(i < it) {
            if (it % i == 0) {
                return false;
            }
            i++;
        }
        return true ;
    }

//    public static List<Long> getPodzielniki(long it) {
//        List<Long> contain = new ArrayList<>();
//        long i = 2;
//        while(i < it) {
//            if (contain.contains(i)) {
//                break;
//            }
//            if (it % i == 0) {
//                long n = it / i;
//                contain.add(i);
//                if (i != n) {
//                    contain.add(n);
//                }
//
//
//            }
//            i++;
//        }
//        return contain;
//    }

}


