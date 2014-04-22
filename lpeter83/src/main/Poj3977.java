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
        while (i < j) {
            while (a[i] < a[p] || (a[i] == a[p] && b[i] < b[p])) i++; //a[i] >= a[p], could stop at p
            while (a[j] > a[p] || (a[j] == a[p] && b[j] > b[p])) j--;
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
            //Random rnd = new Random();
            //n=rnd.nextInt(35);
            if (n == 0) break;
            if (n == 1) {
                out.printLine(Math.abs(in.readInt()), 1);
                //out.printLine(Math.abs(rnd.nextLong()),1);
                continue;
            }
            a = new long[n];
            for (int i = 0; i < n; ++i) {
                a[i] = in.readLong();
                //a[i]=rnd.nextLong();
            }
            Arrays.sort(a);
            int neg = -1;
            for (int i = 0; i < n; ++i)
                if (a[i] > 0) {
                    neg = i;
                    break;
                } else if (a[i] == 0) {
                    out.printLine(0, 1);
                    continue loop;
                }
            if (neg == -1) {
                out.printLine(-a[a.length - 1], 1);
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
            res = 1 << 40;
            c = 1 << 40;
            for (int i = 0; i < sm2.length; ++i) {
                int id = ArrayUtils.lowerBound(sm, -sm2[i]);
                for (int j = id - 3; j <= id + 3; ++j) {
                    if (j >= 0 && j < sm.length) {
                        if (count2[i] == 0 && count[j] == 0) continue;
                        if (res > Math.abs(sm[j] + sm2[i]) || (res == Math.abs(sm[j] + sm2[i]) && c > count[j] + count2[i])) {
                            res = Math.abs(sm[j] + sm2[i]);
                            c = count[j] + count2[i];
                        }
                    }
                }
            }
            out.printLine(res, c);
            /*
            m=n/2;
            if (neg>m) {
                //reverse the order
                for(int i=0;i<n/2;++i) {
                    long t=a[n-1-i];
                    a[n-1-i]=a[i];
                    a[i]=t;
                }
            }
            ps=0;
            sm=new long[(1<<m)-1];
            count = new long[(1<<m)-1];
            for(int i=1;i<1<<m;++i) {
                long ss=0;
                int cc=0;
                for(int j=0;j<m;++j) {
                    if ((i>>j&1)==1) {
                        ss+=a[j];
                        cc+=1;
                    }
                }
                sm[ps]=ss;
                count[ps]=cc;
                ps++;
            }
            sort(0,sm.length-1);
            res=Math.abs(sm[0]);
            c=count[0];
            for(int i=1;i<sm.length;++i) {
                if (sm[i]==sm[0]) {
                    if (c>count[i])
                        c=count[i];
                } else break;
            }
            for(int i=m;i<n;++i) {
                if (res > Math.abs(a[i])) {
                    res = Math.abs(a[i]);
                    c = 1;
                }
                int id=ArrayUtils.lowerBound(sm,-a[i]);
                for(int j=id-10;j<=id+10;++j) {
                    if (j>=0 && j<ps) {
                        if (res > Math.abs(a[i]+sm[j])) {
                            res = Math.abs(a[i]+sm[j]);
                            c = count[j]+1;
                        }
                    }
                }
            }
            out.printLine(res,c);
            */
        }
    }

}
