package cv.bloody.ua.study.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BinaryReader extends BufferedInputStream {

    public BinaryReader(InputStream in) {
        super(in);
    }

    public int readVarInt() throws IOException {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = (byte) this.read();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }

    public String readSizedString() throws IOException {
        final int length = readVarInt();
        final byte[] bytes = new byte[length];
        read(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

}
