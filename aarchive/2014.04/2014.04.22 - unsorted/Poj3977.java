package main;

import Utils.ArrayUtils;
import io.InputReader;
import io.OutputWriter;

import java.util.Arrays;
import java.util.Random;

public class Poj3977 {

    int n, m;
    long[] a;
    long[] sm;
    long[] sm2;
    long[] count;
    long[] count2;
    long res;
    long c;
    int ps;


    public void sort(int left, int right, long[] a, long[] b) {//[left,right)
        int i = left;
        int j = right;
        Random rnd = new Random();
        int p = left + rnd.nextInt(right - left + 1);
		long x = a[p], y = b[p];
        while (i < j) {
            while (a[i] < x || (a[i] == x && b[i] < y)) i++; //a[i] >= a[p], could stop at p
            while (a[j] > x || (a[j] == x && b[j] > y)) j--;
            if (i <= j) { //should be i<=j, case i==j is a[i]=a[j]=a[p]
                long t = a[i];
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


    public void solve(int testNumber, InputReader in, OutputWriter out) {
        loop:
        for (; ; ) {
            n = in.readInt();
//            Random rnd = new Random();
//            n=rnd.nextInt(35);
            if (n == 0) break;
            a = new long[n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.readLong();
//                a[i]=rnd.nextLong();
            }
            if (n==1) {
                out.printLine(Math.abs(a[0]),1);      // WA at here
                continue loop;
            }
            m = n / 2;
            ps = 0;
            sm = new long[(1 << m)];
            count = new long[(1 << m)];
            for (int i = 0; i < 1 << m; ++i) {
                long ss = 0;
                int cc = 0;
                for (int j = 0; j < m; ++j) {
                    if ((i >> j & 1) == 1) {
                        ss += a[j];
                        cc += 1;
                    }
                }
                sm[ps] = ss;
                count[ps] = cc;
                ps++;
            }
            ps = 0;
            sm2 = new long[(1 << n - m)];
            count2 = new long[(1 << n - m)];
            for (int i = 0; i < 1 << (n - m); ++i) {
                long ss = 0;
                int cc = 0;
                for (int j = 0; j < (n - m); ++j) {
                    if ((i >> j & 1) == 1) {
                        ss += a[j + m];
                        cc += 1;
                    }
                }
                sm2[ps] = ss;
                count2[ps] = cc;
                ps++;
            }
            sort(0, sm.length - 1, sm, count);
            sort(0, sm2.length - 1, sm2, count2);
			int m2=1;
			for(int i=1;i<sm.length;++i) {
				if (sm[m2-1]<sm[i]) {
					sm[m2]=sm[i]; count[m2]=count[i];m2++;
				} else if (sm[m2-1]==0 && count[m2-1]==0 && sm[i]==0) {
					sm[m2]=sm[i]; count[m2]=count[i];m2++;
				}
			} // m2 is new length
			long INF=Long.MAX_VALUE;
            res = INF;
            c = INF;
            for (int i = 0; i < sm2.length; ++i) {
                //int id = ArrayUtils.lowerBound(sm, -sm2[i]);
				int lb=-1,hb=m2;
				while(hb-lb>1) {
					int m=(hb+lb)/2;
					if (sm[m] >= -sm2[i])
						hb=m;
					else
						lb=m;
				}
				int id=hb;
                for (int j = id - 3; j <= id + 3; ++j) {
                    if (j >= 0 && j < m2) {
                        if (count2[i] == 0 && count[j] == 0) continue;
                        if (res > Math.abs(sm[j] + sm2[i]) || (res == Math.abs(sm[j] + sm2[i]) && c > count[j] + count2[i])) {
                            res = Math.abs(sm[j] + sm2[i]);
                            c = count[j] + count2[i];
                        }
                    }
                }
            }

            if (res<0) throw new AssertionError();
            out.printLine(res, c);
        }
    }

}
