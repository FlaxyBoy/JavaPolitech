package cv.bloody.ua.study.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class BinaryWriter extends BufferedOutputStream {

    public BinaryWriter(OutputStream out) {
        super(out);
    }

    public void writeVarInt(int value) throws IOException {
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            this.write(temp);
        } while (value != 0);
    }

    public void writeSizedString(String string) throws IOException{
        final byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length);
        write(bytes);
    }

}
