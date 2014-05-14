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
    int ans;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        N = in.readInt();
        C = in.readInt();
        a = new int[N];
        count = new int[N];
        ans = 0;
        for (int i = 0; i < N; ++i) {
            a[i] = in.readInt();
            count[i] = in.readInt();
        }
        sort(0, N - 1, a, count);
		for(int i=N-1;i>=0;i--) {
			if (a[i]>=C) {
				ans+=count[i];
				count[i]=0;
			}
		}
        loop:
        while (true) {
            if (allEmpty(count)) break;
            int nc = C;
            for (int i = N - 1; i >= 0; --i) {
                int t = nc / a[i];
                t = Math.min(t, count[i]);
                nc -= t * a[i];
                count[i] -= t;
				if (nc == 0) {
					ans++;
					continue loop;
				}
            }
            for (int i = 0; i < N; ++i) {
				if (count[i] == 0) continue;
                int t = nc / a[i];
				count[i] -= (t+1);
				ans++;
				continue loop;
                // t = Math.min(t, count[i]);
                // count[i] -= t;
                // if (nc - a[i] * t <= 0) {
                    // ans++;
                    // continue loop;
                // }
            }
        }
        out.printLine(ans);
    }

    private boolean allEmpty(int[] count) {
        for (int i = 0; i < count.length; ++i) {
            if (count[i] > 0) return false;
        }
        return true;
    }
}
