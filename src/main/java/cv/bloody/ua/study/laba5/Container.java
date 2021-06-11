package cv.bloody.ua.study.laba5;

import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public interface Container {

    public void read(BinaryReader reader) throws IOException;

    public void write(BinaryWriter writer) throws IOException;
}
