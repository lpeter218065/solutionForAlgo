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
            if (a[m] >= cnt) hb = m;
            else lb = m;
        }
        return hb;
    }

    public static int upperBound(int[] a, int cnt) {
        int lb = -1;
        int hb = a.length;
        while (hb - lb > 1) {
            int m = (hb + lb) >> 1;
            if (a[m] > cnt) hb = m;
            else lb = m;
        }
        if (a[lb] < cnt) return -1;
        else return lb;
    }

    //sort for 2 array, without write a compare function
    public void sort(int left, int right, int[] a, int[] b) {
        int i = left;
        int j = right;
        Random rnd = new Random();
        int p = left + rnd.nextInt(right - left + 1);
        int x = a[p], y = b[p];
        do {
            while (a[i] < x || (a[i] == x && b[i] < y)) i++;
            while (a[j] > x || (a[j] == x && b[j] > y)) j--;
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
        } while (i <= j);
        if (left < j) sort(left, j, a, b);
        if (right > i) sort(i, right, a, b);
    }

}
