package cv.bloody.ua.study.laba5;

import cv.bloody.ua.study.IProgram;
import cv.bloody.ua.study.laba5.task1.Database;

import java.io.IOException;
import java.util.Scanner;

public class Program implements IProgram {

    @Override
    public void start(String[] args) {
        task1();
    }

    private void task1() {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("Введіть 1 щоб добавити запис, " +
                    "2 щоб редагувати запис, " +
                    "3 щоб знищити запис, " +
                    "4 пошук інформації, " +
                    "5 сортування даних за відсотком виконаї продукції, "  +
                    "6 вивід місяця зі найменшим відсотком виконаної продукції, " +
                    "7 вивід місяця зі найбільшим відсотком виконаної продукції, " +
                    "8 вихід");
            try {
                n = scanner.nextInt();
            }catch (Exception e) {
                e.printStackTrace();
                n = 100;
            }
            if(n == 1) {
                database.input();
            }else if(n == 2) {
                database.edit();
            }else if(n == 3) {
                database.delete();
            }else if(n == 4) {
                database.search();
            }else if(n == 5) {
                database.sort();
            }
            else if(n == 6) {
                database.showPoorMonth();
            }
            else if(n == 7) {
                database.showRichMonth();
            }
            else if(n != 8){
                System.out.println("Невідома операція");
            }

        }while (n != 8);
        try {
            database.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void task2() {
        cv.bloody.ua.study.laba5.task2.Database database = new cv.bloody.ua.study.laba5.task2.Database();
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("Введіть 1 щоб добавити запис, " +
                    "2 щоб редагувати запис, " +
                    "3 щоб знищити запис, " +
                    "4 пошук інформації, " +
                    "5 показ кількості відвідувачів за весь період, "  +
                    "6 показ дня зі найменшою кількістью відвідувачів, " +
                    "7 пошук за словом зі коментарієм, " +
                    "8 вихід");
            try {
                n = scanner.nextInt();
            }catch (Exception e) {
                e.printStackTrace();
                n = 100;
            }
            if(n == 1) {
                database.input();
            }else if(n == 2) {
                database.edit();
            }else if(n == 3) {
                database.delete();
            }else if(n == 4) {
                database.search();
            }else if(n == 5) {
                database.countOfVisitors();
            }
            else if(n == 6) {
                database.printPoorCountOfVisitors();
            }
            else if(n == 7) {
                database.searchByProperty();
            }
            else if(n != 8){
                System.out.println("Невідома операція");
            }

        }while (n != 8);
        try {
            database.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
