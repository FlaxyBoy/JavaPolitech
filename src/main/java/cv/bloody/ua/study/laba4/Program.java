package cv.bloody.ua.study.laba4;

import cv.bloody.ua.study.IProgram;

import java.io.File;
import java.util.Scanner;

public class Program implements IProgram {

    @Override
    public void start(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("laba4.bin");
        Database.getInstance().load(file);
        int n = 0;
        do {
            System.out.println("Введіть 1 щоб добавити запис, " +
                    "2 щоб редагувати запис, " +
                    "3 щоб знищити запис, " +
                    "4 пошук інформації, " +
                    "5 пошук інформації за періодом, "  +
                    "6 пошук інформації зі мінімальною кількістю доріжок,  " +
                    "7 вихід");
            n = scanner.nextInt();
            if(n == 1) {
                Database.getInstance().input();
            }else if(n == 2) {
                Database.getInstance().edit();
            }else if(n == 3) {
                Database.getInstance().delete();
            }else if(n == 4) {
                Database.getInstance().search();
            }else if(n == 5) {
                Database.getInstance().searchByPeriod();
            }
            else if(n == 6) {
                Database.getInstance().searchByAvailableTracks();
            }
            else if(n != 7){
                System.out.println("Невідома операція");
            }

        }while (n != 7);
        Database.getInstance().save(file);
    }
}
