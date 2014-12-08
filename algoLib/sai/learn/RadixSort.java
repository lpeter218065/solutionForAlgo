package learn;

import java.util.Arrays;

/**
 * Created by rxu on 12/4/2014.
 */
public class RadixSort {
    static void radixSort(int[] a) {
        int[] count = new int[100];
        int[] temp = new int[a.length];
        for (int i = 0; i < a.length; ++i) {
            count[a[i] + 1]++;
        }
        for (int i = 1; i < count.length; ++i) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < a.length; ++i) {
            temp[count[a[i]]++] = a[i];
        }
        System.arraycopy(temp, 0, a, 0, a.length);
    }


    public static void main(String[] args) {
        int[] a = new int[]{1, 4, 7, 5, 9, 2, 3, 7, 8, 9};
        radixSort(a);
        System.out.println(Arrays.toString(a));
    }

}
