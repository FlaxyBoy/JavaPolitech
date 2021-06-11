package cv.bloody.ua.study.laba4;

import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.*;

public abstract class SwimmingPool {

    protected String name;
    protected String street;

    public SwimmingPool(BinaryReader reader) throws IOException {
        name = null;
        street = null;
        this.read(reader);
    }

    public SwimmingPool(String name , String street) {
        this.name = name;
        this.street = street;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    protected abstract void write(BinaryWriter stream) throws IOException;

    protected abstract void read(BinaryReader stream) throws IOException;


}
