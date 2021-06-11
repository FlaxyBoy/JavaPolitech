package cv.bloody.ua.study.laba5.task2;

import cv.bloody.ua.study.laba5.AbstractDatabase;
import cv.bloody.ua.study.laba5.task1.Month;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Database extends AbstractDatabase<Day> {

    private static final SimpleDateFormat formarter = new SimpleDateFormat("dd:MM:yyyy");

    public Database() {
        super("laba5_task2.bin", Day::new);
    }

    @Override
    public void input() {
        Scanner scanner = new Scanner(System.in);
        Date date = inputDate(scanner);
        String name, surname;
        int numberOfVisitors;
        String property;
        System.out.println("Введіть імя художника");
        name = scanner.next();
        System.out.println("Введіть фамілію");
        surname = scanner.next();
        System.out.println("Введіть кількість відвідувачів");
        numberOfVisitors = scanner.nextInt();
        System.out.println("Введіть коментарії");
        property = scanner.nextLine();
        list.add(new Day(date , name , surname , numberOfVisitors , property));
    }

    public void countOfVisitors() {
        AtomicInteger count = new AtomicInteger(0);
        list.forEach(day -> count.addAndGet(day.getNumberOfVisitors()));
        System.out.println("Кількість відвідувачів за весь період: " + count.get());
    }

    public void printPoorCountOfVisitors() {
        if(list.size() == 0) System.out.println("Кількість записів в базі данних рівна 0, будьласка введіть записи");
        AtomicReference<Day> day = new AtomicReference<>(null);
        list.forEach(d -> {
            if(day.get() == null) {
                day.set(d);
            }else {
                if(d.getNumberOfVisitors() < day.get().getNumberOfVisitors()) day.set(d);
            }
        });
        System.out.println("День зі найменшою кількістю відвідувачів - " + day.get());
    }

    public void searchByProperty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть слово з коментарію");
        String word = scanner.next();
        AtomicInteger integer = new AtomicInteger();
        List<Day> days = list.stream().filter(day -> day.getProperty().contains(word)).collect(Collectors.toList());;
        if(days.size() == 0) {
            System.out.println("Не було найдено записів де коментарій містить це слово");
        }
        days.forEach(day -> {
            System.out.printf("%s) %s\n" , integer.incrementAndGet() , day.getProperty());
        });
    }
    @Override
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        Day day = get(scanner);
        if(day == null) return;
        System.out.println(day);
        int operation = 0;
        do {
            System.out.println("Введіть 1 щоб змінити кількість відвідувачів , 2 щоб змінити коментарій, 3 щоб завершити");
            operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    System.out.println("Введіть кількість відвідувачів");
                    day.setNumberOfVisitors(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введіть коментарій");
                    day.setProperty(scanner.nextLine());
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Невідома операція");
            }
        }while (operation != 3);
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        Day day = get(scanner);
        if(day == null) return;
        System.out.println(day);
        System.out.println("Ви дійсно хочете видалити данний запис? Введіть \"так\" для пітвердження");
        if(scanner.next().equalsIgnoreCase("так")) {
            list.remove(day);
        }
        System.out.println("Запис успішно видалений");
    }

    @Override
    public void search() {
        Scanner scanner = new Scanner(System.in);
        Day day = get(scanner);
        if(day == null) return;
        System.out.println(day);
    }

    @Override
    protected Day get(Scanner scanner) {
        Date date = inputDate(scanner);
        Optional<Day> value = list.stream().filter(t -> t.check(date)).findFirst();
        if(!value.isPresent()) {
            System.out.println("Запису з датою " + formarter.format(date) + " не існує");
            return null;
        }
        return value.get();
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

}
