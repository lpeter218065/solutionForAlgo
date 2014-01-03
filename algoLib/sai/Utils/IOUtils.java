package Utils;

import io.InputReader;

/**
 * Created by rxu on 1/3/14.
 */
public class IOUtils {
    public static int[] readIntArray(InputReader in, int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = in.readInt();
        }
        return res;
    }

    public static int[][] readIntArray(InputReader in, int size, int sizeb) {
        int[][] res = new int[size][sizeb];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < sizeb; j++) {
                res[i][j] = in.readInt();
            }
        }
        return res;
    }
}
