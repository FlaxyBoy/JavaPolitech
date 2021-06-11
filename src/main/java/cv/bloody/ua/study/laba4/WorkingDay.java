package cv.bloody.ua.study.laba4;

import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Date;

public class WorkingDay extends SwimmingPool {

    private static final SimpleDateFormat formarter = new SimpleDateFormat("dd:MM:yyyy");

    private Date date;
    private int numberOfVisitors;
    private int numberOfTracksAvailable;

    public WorkingDay(BinaryReader stream) throws IOException {
        super(stream);
    }

    public WorkingDay(String name , String street , Date date , int numberOfVisitors , int numberOfTracksAvailable) {
        super(name , street);
        this.date = date;
        this.numberOfVisitors = numberOfVisitors;
        this.numberOfTracksAvailable = numberOfTracksAvailable;
    }

    public int getNumberOfTracksAvailable() {
        return numberOfTracksAvailable;
    }

    public boolean inPeriod(Date start , Date end) {
        if(date.after(start) && date.before(end)) return true;
        if(date.getYear() == start.getYear() && date.getMonth() == start.getMonth() && date.getDay() == start.getDay()) return true;
        return date.getYear() == end.getYear() && date.getMonth() == end.getMonth() && date.getDay() == end.getDay();
    }

    @Override
    protected void write(BinaryWriter stream) throws IOException {
        stream.writeSizedString(name);
        stream.writeSizedString(street);
        stream.writeVarInt(date.getYear());
        stream.writeVarInt(date.getMonth());
        stream.writeVarInt(date.getDay());
        stream.writeVarInt(numberOfVisitors);
        stream.writeVarInt(numberOfTracksAvailable);
    }

    @Override
    protected void read(BinaryReader stream) throws IOException {
        name = stream.readSizedString();
        street = stream.readSizedString();
        date = new Date(stream.readVarInt() , stream.readVarInt() , stream.readVarInt());
        numberOfVisitors = stream.readVarInt();
        numberOfTracksAvailable = stream.readVarInt();
    }

    public void setNumberOfTracksAvailable(int numberOfTracksAvailable) {
        this.numberOfTracksAvailable = numberOfTracksAvailable;
    }

    public void setNumberOfVisitors(int numberOfVisitors) {
        this.numberOfVisitors = numberOfVisitors;
    }

    @Override
    public String toString() {
        return String.format("name = %s , street = %s , date = %s , number of visitors = %s, number of tracks available = %s" ,
                name , street , formarter.format(date) , numberOfVisitors , numberOfTracksAvailable);
    }
}
