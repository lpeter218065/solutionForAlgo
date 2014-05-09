package main;

import io.InputReader;
import io.OutputWriter;

import java.util.Random;

public class Poj3040 {

    public void sort(int left, int right, int[] a, int[] b) {
        int i = left;
        int j = right;
        Random rnd = new Random();
        int p = left + rnd.nextInt(right - left + 1);
        int x = a[p];
        int y = b[p];
        while (i < j) {
            while (a[i] < x || a[i] == x && b[i] < y) i++;
            while (a[j] > x || a[j] == x && b[j] > y) j--;
            if (i <= j) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                t = b[i];
                b[i] = b[j];
                b[j] = t;
                i++;
                j--;
            }
        }
        if (left < j) sort(left, j, a, b);
        if (right > i) sort(i, right, a, b);
    }

    int[] a, count;
    int N, C;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        N = in.readInt();
        C = in.readInt();
        a = new int[N];
        count = new int[N];
        for (int i = 0; i < N; ++i) {
            a[i] = in.readInt();
            count[i] = in.readInt();
        }
        sort(0, N - 1, a, count);
        int ans = 0;
        loop2:
        while (true) {
            if (allEmpty(count)) break;
            int nc = C;
            loop:
            for (int i = N - 1; i >= 0; i--) {
                int t = 0;
                while (nc > 0) {
                    if (count[i] == 0) continue loop;
                    if (count[i] > 0) {
                        count[i]--;
                        nc -= a[i];
                        t++;
                    }
                    if (nc == 0 || t == 1 && nc < 0) {
                        ans++;
                        continue loop2;
                    }
                    if (nc < 0) {
                        count[i]++;
                        nc += a[i];
                        break loop;
                    }
                }
            }
            loop3:
            for (int i = 0; i < N; ++i) {
                while (nc > 0) {
                    if (count[i] == 0) continue loop3;
                    if (count[i] > 0) {
                        count[i]--;
                        nc -= a[i];
                    }
                    if (nc <= 0) {
                        ans++;
                        continue loop2;
                    }
                }
            }
        }
        if (ans < 0) throw new AssertionError();
        out.printLine(ans);
    }

    private boolean allEmpty(int[] count) {
        for (int i = 0; i < count.length; ++i) {
            if (count[i] > 0) return false;
        }
        return true;
    }
}
