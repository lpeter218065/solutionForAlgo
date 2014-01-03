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
            if (nc <= 0) {
                return -1;
            }
        }
        return buf[cnt++];
    }

    public int peek() {
        if (nc == -1)
            return -1;
        if (cnt >= nc) {
            cnt = 0;
            try {
                nc = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (nc <= 0)
                return -1;
        }
        return buf[nc];
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

    public String readString() {
        int c = read();
        while (isSpaceChar(c)) c = read();
        StringBuilder sb = new StringBuilder();
        while (!isSpaceChar(c)) {
            sb.appendCodePoint(c);
            c = read();
        }
        return sb.toString();
    }

    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
    }

    public double readDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E')
                return res * Math.pow(10, readInt());
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }

    public String readLine() {
        StringBuffer sb = new StringBuffer();
        int c = read();
        while (isSpaceChar(c)) c = read();
        while (c != '\n' && c != -1) {
            sb.appendCodePoint(c);
            c = read();
        }
        return sb.toString();
    }

    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1) read();
        return value == -1;
    }

}
