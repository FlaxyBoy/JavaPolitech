package cv.bloody.ua.study.laba2;

import cv.bloody.ua.study.IProgram;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Program implements IProgram {

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
            default:
                System.out.println("Не вірний номер завдання");
        }
    }

    /**
     * метод для запуска першого завдання
     *
     */

    public void task1() {
        try {
            File file = new File("C:\\Users\\Blood\\Desktop\\lava.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            List<String> list = new LinkedList<>();
            StringBuffer last = new StringBuffer();
            while (true) {
                int b = reader.read();
                if (b == -1) {
                    if (!last.toString().equals("")) {
                        list.add(last.toString());
                    }
                    break;
                } else {
                    char c = (char) b;
                    if (c == ' ') {
                        if (!last.toString().equals("")) {
                            list.add(last.toString());
                            last = new StringBuffer();
                        }
                    } else {
                        last.append(c);
                    }
                }
            }
            list = list.stream().filter(s -> {
                for (int i = 1; i < s.length(); i++) {
                    if (s.charAt(i - 1) == s.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
            System.out.println("Кількість слів у тексті " + list.size());
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            list.forEach(s -> {
                try {
                    writer.write(s + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для запуска другого завдання
     *
     */

    public void task2() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть кількість чисел: ");
            int n = scanner.nextInt();
            File file = new File("C:\\Users\\Blood\\Desktop\\laba_2.txt");
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            StringJoiner joiner = new StringJoiner(" ");
            for(int i = 0 ; i < n ; i++) {
                joiner.add(new Random().nextInt(200) + "");
            }
            writer.write(joiner.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
