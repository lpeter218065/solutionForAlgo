package Utils;

import java.util.Random;

/**
 * Created by rxu on 1/3/14.
 */
public class ArrayUtils {
    public static int lowerBound(int[] a, int cnt) {
        int lb = -1;
        int hb = a.length;
        while (hb - lb > 1) {
            int m = (hb + lb) >> 1;
            if (a[m] >= cnt) hb = m; // (lb,mid]
            else lb = m; // (mid,hb]
        }
        return hb;
    }

    public static int upperBound(int[] a, int cnt) {
        int lb = -1;
        int hb = a.length;
        while (hb - lb > 1) {
            int m = (hb + lb) >> 1;
            if (a[m] > cnt) hb = m; //which means that cnt should be located before m
            else lb = m; // [m,hb)
        }
        if (a[lb] < cnt) return -1;
        else return lb;
    }

    public static int lowerBound(long[] a, long cnt) {
        int lb = -1;
        int hb = a.length;
        while (hb - lb > 1) {
            int m = (hb + lb) >> 1;
            if (a[m] >= cnt) hb = m; // (lb,mid]
            else lb = m; // (mid,hb]
        }
        return hb;
    }


    public static void sort(int left, int right, int[]... a) {//[left,right)
        int i = left;
        int j = right;
        Random rnd = new Random();
        int p = left + rnd.nextInt(right - left + 1);
        while (i < j) {
            while (a[0][i] < a[0][p]) i++; //a[i] >= a[p], could stop at p
            while (a[0][j] > a[0][p]) j--;
            if (i <= j) { //should be i<=j, case i==j is a[i]=a[j]=a[p]
                for (int k = 0; k < a.length; ++k) {
                    int t = a[k][i];
                    a[k][i] = a[k][j];
                    a[k][j] = t;
                }
                i++;
                j--;
            }
        }
        if (left < j) sort(left, j, a);
        if (right > i) sort(i, right, a);
    }

    public static void sort(int left, int right, long[]... a) {
        int i = left;
        int j = right;
        Random rnd = new Random();
        int p = left + rnd.nextInt(right - left + 1);
        while (i < j) {
            while (a[0][i] < a[0][p]) i++;
            while (a[0][j] > a[0][p]) j--;
            if (i <= j) {
                for (int k = 0; k < a.length; ++k) {
                    long t = a[k][i];
                    a[k][i] = a[k][j];
                    a[k][j] = t;
                }
                i++;
                j--;
            }
        }
        if (left < j) sort(left, j, a);
        if (right > i) sort(i, right, a);
    }
}
