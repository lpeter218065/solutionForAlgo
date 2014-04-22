package main;

import io.InputReader;
import io.OutputWriter;

public class Poj3273 {

    long INF = Long.MAX_VALUE/2;
    int N,M;
    int[] a;

    boolean C(long m) {
        int i=0;
        int count=0;
        while(true) {
            int sum=0;
            while(i<N && sum<=m) {
                if (a[i] > m) return false;     //dead loop if not add this
                sum+= a[i];
                i++;
            }
            count++;
            if (i==N) break;
            i--;
        }
        return count<=M;
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        N=in.readInt();
        M=in.readInt();
        a = new int[N];
        for(int i=0;i<N;++i) {
            a[i]=in.readInt();
        }
        long lb=0,ub=INF;
        while(ub-lb>1) {
            long m=(ub+lb)/2;
            if (C(m)) ub=m; // (lb,ub]
            else lb=m;
        }
        out.printLine(ub);

    }
}
