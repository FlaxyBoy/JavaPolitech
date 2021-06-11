package cv.bloody.ua.study.laba3;

import cv.bloody.ua.study.laba3.task1.Database;
import cv.bloody.ua.study.IProgram;

import java.util.Scanner;

public class Program implements IProgram {


    @Override
    public void start(String[] args) {
        task2();
    }

    /**
     * Метод для запуску перщого завдання
     */

    public void task1() {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("Введіть 1 щоб добавити студента, 2 щоб вивести студента з найближнім днем народження, 3 щоб вийти");
            n = scanner.nextInt();
            if(n == 1) {
                Database.getInstance().inputStudent();
            }else if(n == 2) {
                Database.getInstance().showNearBirthdayStudents();
            }else {
                System.out.println("Невідома операція");
            }

        }while (n != 3);
    }

    public void task2() {
        Scanner scanner = new Scanner(System.in);
        int n = 0;
        do {
            System.out.println("Введіть 1 щоб добавити запис, " +
                    "2 щоб редагувати запис, " +
                    "3 щоб знищити запис, " +
                    "4 пошук інформації, " +
                    "5 вихід");
            n = scanner.nextInt();
            if(n == 1) {
                cv.bloody.ua.study.laba3.task2.Database.getInstance().inputStudent();
            }else if(n == 2) {
                cv.bloody.ua.study.laba3.task2.Database.getInstance().edit();
            }else if(n == 3) {
                cv.bloody.ua.study.laba3.task2.Database.getInstance().delete();
            }else if(n == 4) {
                cv.bloody.ua.study.laba3.task2.Database.getInstance().search();
            }else {
                System.out.println("Невідома операція");
            }

        }while (n != 5);
    }
}
