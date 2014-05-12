package Utils;/**
 * Created with IntelliJ IDEA.
 * User: lpeter
 * Date: 5/13/14
 * Time: 12:26 AM
 * To change this template use File | Settings | File Templates.
 */

public class StringUtils {


    public static int[] calcPrefix(char[] s) {
        int[] pre = new int[s.length];
        int k = -1;
        pre[0] = -1;
        for (int i = 1; i < s.length; ++i) {
            while (k > -1 && s[k + 1] != s[i]) {
                k = pre[k];
            }
            if (s[k + 1] == s[i]) k++;
            pre[i] = k;
        }
        return pre;
    }


    public static boolean match(char[] s, char[] t) {
        int k = -1;
        int[] p = calcPrefix(t);
        for (int i = 0; i < s.length; ++i) {
            while (k > -1 && t[k + 1] != s[i])
                k = p[k];
            if (t[k + 1] == s[i])
                k++;
            if (k == t.length - 1) return true;
        }
        return false;
    }


}

