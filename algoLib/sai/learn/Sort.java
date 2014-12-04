package learn;

import java.util.Arrays;

/**
 * Created by rxu on 12/4/2014.
 */
public class Sort {

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static int median3(int[] arr, int left, int right) {
        int mid = (left + right) >> 1;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[mid] > arr[right]) {
            swap(arr, mid, right);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        //move pivot to last one
        swap(arr, mid, right);
        return arr[right];
    }

    static void quickSort(int[] arr, int lo, int hi) {
        if (hi - lo < 1) return;
        int p = median3(arr, lo, hi);
        int s = lo;
        int e = hi - 1;

        while (s < e) {
            while (arr[s] < p) s++;
            while (arr[e] > p) e--;
            if (s < e) {
                swap(arr, s, e);
            }
        }
        //arr[s] <= p  arr[e] > p
        swap(arr, s, hi);
        quickSort(arr, lo, s);
        quickSort(arr, s + 1, hi);
    }

    public static void main(String[] args) {
        int[] a = new int[]{4, 6, 8, 9, 9, 3, 20, 1, 10, 20, 15};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }


}
