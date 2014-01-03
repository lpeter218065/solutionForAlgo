package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxu on 1/3/14.
 */
public class NumUtils {

    public static int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static long[][] generateComb(int n) {
        long[][] c = new long[n + 1][n + 1];
        for (int i = 0; i < n; ++i) c[i][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) continue;
                c[i + 1][j + 1] = c[i][j] + c[i][j + 1];
            }
        }
        return c;
    }

    public static int[] generatePrime(int upto) {
        List<Integer> prime = new ArrayList<Integer>();
        loop2:
        for (int i = 2; i < 100000; ++i) {
            for (int j = 2; j * j <= i; ++j) {
                if (i % j == 0) {
                    continue loop2;
                }
            }
            prime.add(i);
        }
        int[] res = new int[prime.size()];
        for (int i = 0; i < prime.size(); ++i) {
            res[i] = prime.get(i);
        }
        return res;
    }
}
