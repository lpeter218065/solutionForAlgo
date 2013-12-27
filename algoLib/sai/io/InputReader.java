package io;

import java.io.InputStream;

/**
 * Created by rxu on 12/27/13.
 */
public class InputReader {
    private InputStream stream;
    private byte[] buf = new byte[1024];

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

}
