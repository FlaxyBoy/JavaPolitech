package cv.bloody.ua.study.laba5;

import cv.bloody.ua.study.laba4.WorkingDay;
import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public abstract class AbstractDatabase <T extends Container> {

    protected final List<T> list;
    private final File file;

    public AbstractDatabase(String file , Supplier<T> supplier) {
        list = new ArrayList<>();
        this.file = new File(file);
        try {
            BinaryReader stream = new BinaryReader(new FileInputStream(file));
            if(stream.available() > 0) {
                T value = supplier.get();
                value.read(stream);
                list.add(value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void save() throws IOException {
        try {
            BinaryWriter stream = new BinaryWriter(new FileOutputStream(file));
            list.forEach(value -> {
                try {
                    value.write(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void input();

    public abstract void edit();

    public abstract void delete();

    public abstract void search();

    protected abstract T get(Scanner scanner);
}
