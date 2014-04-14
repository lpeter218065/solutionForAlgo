package main;

import io.InputReader;
import io.OutputWriter;

import java.util.Arrays;

//not code in an easy way

public class Poj2385 {

    int[][] dp;
    int[] a;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int T = in.readInt();
        int W = in.readInt();
        dp = new int[T + 1][W + 1];
        a = new int[T + 1];
        for(int i = 0; i < T; ++i) {
            a[i] = in.readInt();
        }
        for(int[] b : dp) Arrays.fill(b, -1);
        out.printLine(go(T - 1,W));
    }

    public int go(int current, int times) {
        if (dp[current][times] != -1) return dp[current][times];
        if (current == 0) {
            if (a[current] == 1)
                return 1;
            else if (a[current] == 2 && times >= 1)
                return 1;
            else
                return 0;
        }
        if (a[current] == a[current - 1]) {
            return dp[current][times] = go(current - 1, times) + 1;
        }
        else {
            if (times == 0) {
                return 1;
            }
            int ans = 0;
            if (a[current - 1] == 2 && times == 1) { // 1 -> 2
                int i = current - 1;
                while (i >= 0 && a[i] == 2) i--;
                if (i >= 0)
                    ans = go(i,times) + 1;
            } else {
                if (times > 0)
                    ans = Math.max(ans, go(current - 1, times - 1) + 1);
                int j = current - 1;
                while(j >= 0 && a[j] != a[current]) j--;
                if (j >= 0)
                    ans = Math.max(ans, go(j, times) + 1);
            }
            return dp[current][times] = ans;
        }
    }

}
