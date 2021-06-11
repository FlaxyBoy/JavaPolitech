package cv.bloody.ua.study;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

    private static final Map<Integer , IProgram> programMap = new HashMap<>();

    static {
        programMap.put(1 , new cv.bloody.ua.study.laba1.Program());
        programMap.put(2 , new cv.bloody.ua.study.laba2.Program());
        programMap.put(3 , new cv.bloody.ua.study.laba3.Program());
        programMap.put(4 , new cv.bloody.ua.study.laba4.Program());
        programMap.put(5 , new cv.bloody.ua.study.laba5.Program());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int laba = 0;
        System.out.println("Введіть номер лабалаторної");
        laba = scanner.nextInt();
        programMap.get(laba).start(args);
    }




}
