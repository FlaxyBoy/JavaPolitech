package cv.bloody.ua.study.laba1;

import cv.bloody.ua.study.IProgram;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public final class Program implements IProgram {


    @Override
    public void start(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер завдання: ");
        switch (scanner.nextInt()) {
            case 1:
                task1();
                break;
            case 2:
                task2();
                break;
            case 3:
                task3();
                break;
            case 4:
                task4();
                break;
            default:
                System.out.println("Не вірний номер завдання");
        }
    }

    /**
    * метод для запуска першого завдання
    *
    */

    private void task1() {
        Scanner scanner = new Scanner(System.in);
        int monthID;
        TimeOfTheYearEnum value;
        System.out.println("Введіть порядковий номер місяця");
        try {
            monthID = scanner.nextInt();
        }catch (InputMismatchException e) {
            System.out.println("Ви ввели невірний формат числа");
            return;
        }
        try {
            value = TimeOfTheYearEnum.getByMonthID(monthID);
        }catch (IllegalArgumentException e) {
            System.out.println("Ви ввели неправильний порядковий номер місяця!");
            return;
        }
        System.out.println("Місяць з номером " + monthID + " - " + value.getName());
    }

    /* *
     * метод для запуска другого завдання
     *
     */

    private void task2() {
        double a = 0;
        do {
            System.out.println("x = " + String.format("%.2f" , a) + " | y = " + String.format("%.2f" , Math.tan(a)));
            a += Math.PI / 20;
        }while (a <= Math.PI);
    }

    /* *
     * метод для запуска третього завдання
     *
     */

    private void task3() {
        Scanner scanner = new Scanner(System.in);
        int[] array;
        System.out.println("Введіть розмірність массиву");
        array = new int[scanner.nextInt()];
        System.out.println("Введіть тип вводу масива: (a - вручну) (b - через генератор рандомних чисел)\n");
        scanner.nextLine();
        String type = scanner.nextLine();
        if(type.equals("a")) {
            for(int i = 0 ; i < array.length ; i++) {
                System.out.println("A[" + i + "]: ");
                array[i] = scanner.nextInt();
            }
        }else if(type.equals("b")) {
            for(int i = 0 ; i < array.length ; i++) {
                array[i] = new Random().nextInt(201) - 100;
            }
        }else {
            System.out.println("Невідомий тип вводу данних");
            return;
        }
        System.out.println("Введений массив");
        for(int i = 0 ; i < array.length ; i++) {
            System.out.println("A[" + i + "] = " + array[i]);
        }
        int first = -1;
        int last = -1;
        int max = -1;
        for(int i = 0 ; i < array.length ; i++) {
            if(max == -1) {
                max = i;
            }else {
                if(array[max] < array[i]) max = i;
            }
            if(array[i] < 0) {
                if(first == -1) first = i;
                last = i;
            }
        }
        System.out.println("Максимальный елеммент массиву = A[" + max + "] = " + array[max]);
        if(first == -1) {
            System.out.println("В заданому массиві немає від'ємних елементів");
        }else if(first == last) {
            System.out.println("В заданому массиві лиш 1 вдємний елемент");
        }
        else {
            int value = 0;
            for(int i = first + 1 ; i < last ; i++) {
                value += array[i];
            }
            System.out.println("Сумма між першим і останім від'ємним числом - " + value);
        }
    }

    /**
     * метод для запуска четвертого завдання
     *
     */

    private void task4() {
        Scanner scanner = new Scanner(System.in);
        int[][] array;
        System.out.println("Введіть розмірність массиву n і m");
        array = new int[scanner.nextInt()][scanner.nextInt()];
        System.out.println("Введіть тип вводу масива: (a - вручну) (b - через генератор рандомних чисел)\n");
        scanner.nextLine();
        String type = scanner.nextLine();
        if(type.equals("a")) {
            for(int i = 0 ; i < array.length ; i++) {
                for(int j = 0 ; j < array[i].length ; j++) {
                    System.out.println("A[" + i + "][" + j + "]: ");
                    array[i][j] = scanner.nextInt();
                }
            }
        }else if(type.equals("b")) {
            for(int i = 0 ; i < array.length ; i++) {
                for(int j = 0 ; j < array[i].length ; j++) {
                    array[i][j] = new Random().nextInt(201) - 100;
                }
            }
        }else {
            System.out.println("Невідомий тип вводу данних");
            return;
        }
        System.out.println("Введений массив");
        for(int i = 0 ; i < array.length ; i++) {
            for(int j = 0 ; j < array[i].length ; j++) {
                System.out.println("A[" + i + "][" + j + "] = " + array[i][j]);
            }
        }
        for(int i = 0 ; i < array.length ; i++) {
            int zero = 0;
            for(int j = 0 ; j < array[i].length ; j++) {
                if(array[i][j] == 0) zero++;
            }
            if(zero > 0) {
                System.out.println("Стовпесь номер " + i + " має " + zero + " нульових елементів");
            }
        }
    }
}
