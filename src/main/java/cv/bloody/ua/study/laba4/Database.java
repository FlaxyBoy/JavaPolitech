package cv.bloody.ua.study.laba4;

import cv.bloody.ua.study.utils.BinaryReader;
import cv.bloody.ua.study.utils.BinaryWriter;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Database {

    private static final Database instance = new Database();

    /**
     * Метод для получення об'єкта класса який може бути лиш в 1 екземплярі
     *
     * @return Database - інстанс классу
     */

    public static Database getInstance() {
        return instance;
    }

    private final List<WorkingDay> list;
    private boolean load;

    private Database() {
        list = new ArrayList<>();
        load = false;
    }

    public void load(File file) {
        if(load) throw new IllegalStateException("Database is load");
        load = true;
        try {
            BinaryReader stream = new BinaryReader(new FileInputStream(file));
            if(stream.available() > 0) {
                list.add(new WorkingDay(stream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(File file) {
        try {
            BinaryWriter stream = new BinaryWriter(new FileOutputStream(file));
            list.forEach(workingDay -> {
                try {
                    workingDay.write(stream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для пошуку за деякий період
     */

    public void searchByPeriod() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть початкову дату");
        Date start = inputDate(scanner);
        System.out.println("Введіть кінцеву дату");
        Date end = inputDate(scanner);
        if(start.after(end)) {
            System.out.println("Ви ввели невірний часовий період");
        }
        List<WorkingDay> days = list.stream().filter(workingDay -> workingDay.inPeriod(start , end)).collect(Collectors.toList());
        AtomicInteger integer = new AtomicInteger(0);
        days.forEach(workingDay -> {
            System.out.printf("%s) %s\n" , integer.incrementAndGet() , workingDay);
        });
    }

    /**
     * Метод для пошуку доріжок з мінімальною кількістю
     */

    public void searchByAvailableTracks() {
        Scanner scanner = new Scanner(System.in);
        int min;
        System.out.println("Enter minimal available tracks");
        min = scanner.nextInt();
        List<WorkingDay> days = list.stream().filter(workingDay -> workingDay.getNumberOfTracksAvailable() >= min).collect(Collectors.toList());
        AtomicInteger integer = new AtomicInteger(0);
        days.forEach(workingDay -> {
            System.out.printf("%s) %s\n" , integer.incrementAndGet() , workingDay);
        });
    }

    private Date inputDate(Scanner scanner) {
        int year , month , day;
        System.out.println("Введіть рік");
        year = scanner.nextInt();
        System.out.println("Введіть місяць");
        month = scanner.nextInt();
        System.out.println("Введіть день");
        day = scanner.nextInt();
        return new Date(year , month , day);
    }

    /**
     * Метод для вводу даних про викладача з консолі
     */

    public void input() {
        String name , street;
        Date date;
        int numberOfVisitors , numberOfTracksAvailable;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву басейну");
        name = scanner.next();
        if(list.stream().anyMatch(workingDay -> workingDay.getName().equals(name))) {
            System.out.println("Назва басейну занята");
            return;
        }
        System.out.println("Введіть вулицю басейну");
        street = scanner.next();
        date = inputDate(scanner);
        System.out.println("Введіть кількість відвідувачів");
        numberOfVisitors = scanner.nextInt();
        System.out.println("Введіть кількість вільних доріжок");
        numberOfTracksAvailable = scanner.nextInt();
        list.add(new WorkingDay(name , street , date , numberOfVisitors , numberOfTracksAvailable));
    }

    /**
     * Метод для редактування данних
     */

    public void edit() {
        Scanner scanner = new Scanner(System.in);
        WorkingDay day = get(scanner);
        if(day == null) return;
        System.out.println(day);
        int operation = 0;
        do {
            System.out.println("Введіть 1 щоб змінити кількість відвідувачів , 2 щоб змінити кількість вільних доріжок, 3 щоб завершити");
            operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    System.out.println("Введіть кількість відвідувачів");
                    day.setNumberOfVisitors(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введіть кількість вільних доріжок");
                    day.setNumberOfTracksAvailable(scanner.nextInt());
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Невідома операція");
            }
        }while (operation != 3);
    }

    /**
     * Метод для виделення данних
     */

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        WorkingDay day = get(scanner);
        if(day == null) return;
        System.out.println(day);
        System.out.println("Ви дійсно хочете видалити данний бассейн? Введіть \"так\" для пітвердження");
        if(scanner.next().equalsIgnoreCase("так")) {
            list.remove(day);
        }
        System.out.println("Бассейн успішно видалений");
    }

    /**
     * Метод для пошуку данних
     */

    public void search() {
        Scanner scanner = new Scanner(System.in);
        WorkingDay day = get(scanner);
        if(day == null) return;
        System.out.println(day);
    }

    private WorkingDay get(Scanner scanner) {
        System.out.println("Введіть назву басейну");
        String name = scanner.next();
        Optional<WorkingDay> workingDay = list.stream().filter(t -> t.getName().equals(name)).findFirst();
        if(!workingDay.isPresent()) {
            System.out.println("Басейну не уснує");
            return null;
        }
        return workingDay.get();
    }
}
