package main;



import Utils.ArrayUtils;
import io.InputReader;
import io.OutputWriter;

import java.util.Arrays;

public class Poj3666 {

    final long INF = Long.MAX_VALUE/2;
    long[][] dp;

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n=in.readInt();
        long[] a = new long[n];
        for(int i=0;i<n;++i) a[i]=in.readLong();
        long[] b=a.clone();
        Arrays.sort(b);
        dp=new long[n+1][n+1];
        for(long[] d:dp) Arrays.fill(d,INF);
        long res=INF;
        Arrays.fill(dp[0],0);
        for(int i=0;i<n;++i) {
            long t=dp[i][0];
            for(int j=0;j<n;++j) {
                //for(int k=0;k<=j;++k)
                t=Math.min(t,dp[i][j]);
                dp[i+1][j]=Math.min(dp[i+1][j],t+Math.abs(a[i]-b[j]));
            }
        }
        for(int i=0;i<n;++i) res=Math.min(res,dp[n][i]);
        /*
        {
            for(long[] d:dp) Arrays.fill(d,INF);
            dp[1][0]=0;
            for(int i=1;i<n;++i) {
                for(int k=0;k<i;++k){
                    if (a[i]>=a[k]) dp[i+1][i]=Math.min(dp[i+1][i],dp[i][k]);
                    if (dp[i][k]!=INF)
                    dp[i+1][k]=Math.min(dp[i+1][k],dp[i][k]+Math.abs(a[i] - a[k]));
                }
            }
            for(int i=0;i<n;++i) res=Math.min(res,dp[n][i]);
        }

        {
            for(long[] d:dp) Arrays.fill(d,INF);
            dp[1][0]=0;
            for(int i=1;i<n;++i) {
                for(int k=0;k<i;++k){
                    if (a[i]<=a[k]) dp[i+1][i]=Math.min(dp[i+1][i],dp[i][k]);
                    if (dp[i][k]!=INF)
                    dp[i+1][k]=Math.min(dp[i+1][k],dp[i][k]+Math.abs(a[i] - a[k]));
                }
            }
            for(int i=0;i<n;++i) res=Math.min(res,dp[n][i]);
        } */
        out.printLine(res);
    }

}
