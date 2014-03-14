package Utils;

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

}
