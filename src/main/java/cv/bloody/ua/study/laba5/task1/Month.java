package cv.bloody.ua.study.laba5.task1;

import cv.bloody.ua.study.laba5.Container;
import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

public class Month implements Container , Comparable<Month> {

    public Month() {}

    private int month , planProduction , production;

    public Month(int month , int planProduction , int production) {
        this.month = month;
        this.planProduction = planProduction;
        this.production = production;
    }

    public int getMonth() {
        return month;
    }

    public void setPlanProduction(int planProduction) {
        this.planProduction = planProduction;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    @Override
    public void read(BinaryReader reader) throws IOException {
        month = reader.readVarInt();
        planProduction = reader.readVarInt();
        production = reader.readVarInt();
    }

    @Override
    public void write(BinaryWriter writer) throws IOException {
        writer.writeVarInt(month);
        writer.writeVarInt(planProduction);
        writer.writeVarInt(production);
    }

    @Override
    public int compareTo(Month o) {
        return Double.compare(
                ((double) o.production) / ((double) o.planProduction) ,
                ((double) production) / ((double) planProduction)
        );
    }

    @Override
    public String toString() {
        return String.format("month = %s , plan production = %s , production = %s" ,
                month , planProduction , production);
    }
}
