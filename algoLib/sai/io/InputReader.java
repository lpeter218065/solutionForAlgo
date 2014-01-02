package io;

import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

/**
 * Created by rxu on 12/27/13.
 */
public class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int nc;
    private int cnt;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    private int read() {
        if (nc == -1) {
            throw new InputMismatchException();
        }
        if (cnt >= nc) {
            cnt = 0;
            try {
                nc = stream.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (nc == -1) {
                return -1;
            }
        }
        return buf[cnt++];
    }

    private boolean isSpaceChar(int b) {
        return b == ' ' || b == '\n' || b == -1 || b == '\r' || b == '\t';
    }

    public int readInt() {
        int res = 0;
        boolean sgn = true;
        int c = read();
        while (isSpaceChar(c)) c = read();
        if (c == '-') {
            sgn = false;
            c = read();
        }
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res = res * 10 + (c - '0');
            c = read();
        } while (!isSpaceChar(c));
        return res * (sgn ? 1 : -1);
    }

    public long readLong() {
        long res = 0;
        boolean sgn = true;
        int c = read();
        while (isSpaceChar(c)) c = read();
        if (c == '-') {
            sgn = false;
            c = read();
        }
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res = res * 10 + (c - '0');
            c = read();
        } while (!isSpaceChar(c));
        return res * (sgn ? 1 : -1);
    }


}
