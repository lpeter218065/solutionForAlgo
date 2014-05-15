package main;

import io.InputReader;
import io.OutputWriter;

import java.util.Arrays;
import java.util.Comparator;

public class Poj3109 {


    class point {
        int x, y;

        point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    int N;
    long x[];
    long y[];

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        N = in.readInt();
        x = new long[N];
        y = new long[N];
        for (int i = 0; i < N; ++i) {
            x[i] = in.readLong();
            y[i] = in.readLong();
        }
        int[] nx = compress(x);
        int[] ny = compress(y);

        point[] p = new point[N];
        for (int i = 0; i < N; ++i) {
            p[i] = new point(nx[i], ny[i]);
        }

        Arrays.sort(p, new Comparator<point>() {
            @Override
            public int compare(point o1, point o2) {
                if (o1.x < o2.x || o1.x == o2.x && o1.y < o2.y)
                    return -1;
                else
                    return 1;
            }
        });


        out.printLine(Arrays.toString(p));

    }

    private int[] compress(long[] x) {
        long[] x2 = x.clone();
        Arrays.sort(x2);
        long[] x3 = new long[x2.length];
        int ps = 0;
        x3[0] = x2[0];
        for (int i = 0; i < x2.length - 1; ++i) {
            if (x2[i + 1] > x2[i]) {
                x3[++ps] = x2[i + 1];
            }
        }
        int[] res = new int[x.length];
        for (int i = 0; i < x.length; ++i) {
            int id = Arrays.binarySearch(x3, 0, ps + 1, x[i]);
            res[i] = id;
        }
        return res;
    }
}
