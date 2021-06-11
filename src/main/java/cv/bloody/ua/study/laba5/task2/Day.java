package cv.bloody.ua.study.laba5.task2;

import cv.bloody.ua.study.laba5.Container;
import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Day implements Exhibition , Container {

    private static final SimpleDateFormat formarter = new SimpleDateFormat("dd:MM:yyyy");


    private Date date;
    private String name, surname;
    private int numberOfVisitors;
    private String property;

    public Day() {
    }

    public Day(Date date , String name , String surname , int numberOfVisitors , String property) {
        this.date = date;
        this.name = name;
        this.surname = surname;
        this.numberOfVisitors = numberOfVisitors;
        this.property = property;
    }


    public boolean check(Date date) {
        return date.getYear() == this.date.getYear() && date.getMonth() == this.date.getMonth() && date.getDay() == this.date.getDay();
    }

    @Override
    public void read(BinaryReader reader) throws IOException {
        name = reader.readSizedString();
        surname = reader.readSizedString();
        numberOfVisitors = reader.readVarInt();
        property = reader.readSizedString();
        date = new Date(reader.readVarInt() , reader.readVarInt() , reader.readVarInt());
    }

    @Override
    public void write(BinaryWriter writer) throws IOException {
        writer.writeSizedString(name);
        writer.writeSizedString(surname);
        writer.writeVarInt(numberOfVisitors);
        writer.writeSizedString(property);
        writer.writeVarInt(date.getYear());
        writer.writeVarInt(date.getMonth());
        writer.writeVarInt(date.getDay());
    }

    @Override
    public String getNameArtist() {
        return name;
    }

    @Override
    public String getSurnameArtist() {
        return surname;
    }

    public int getNumberOfVisitors() {
        return numberOfVisitors;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    @Override
    public String toString() {
        return String.format("date = %s , name = %s , surname = %s , number of visitors = %s , property = %s" ,
                formarter.format(date) , name , surname , numberOfVisitors , property);
    }
}
