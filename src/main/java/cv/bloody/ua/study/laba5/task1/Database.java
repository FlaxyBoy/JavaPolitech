package cv.bloody.ua.study.laba5.task1;

import cv.bloody.ua.study.laba4.WorkingDay;
import cv.bloody.ua.study.laba5.AbstractDatabase;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class Database extends AbstractDatabase<Month> {

    public Database() {
        super("laba5_task1.bin", Month::new);
    }

    public void sort() {
        if(list.size() == 0) System.out.println("Кількість записів в базі данних рівна 0, будьласка введіть записи");
        list.sort(null);
        AtomicInteger integer = new AtomicInteger(0);
        list.forEach(month -> {
            System.out.printf("%s) %s\n" , integer.incrementAndGet() , month);
        });
    }

    public void showPoorMonth() {
        if(list.size() == 0) System.out.println("Кількість записів в базі данних рівна 0, будьласка введіть записи");
        list.sort(null);
        System.out.printf("Місяць зі найменшим відсотком виконаного плана: %s\n" , list.get(list.size() - 1));
    }

    public void showRichMonth() {
        if(list.size() == 0) System.out.println("Кількість записів в базі данних рівна 0, будьласка введіть записи");
        list.sort(null);
        System.out.printf("Місяць зі найменшим відсотком виконаного плана: %s\n" , list.get(0));
    }

    @Override
    public void input() {
        int month , planProduction , production;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть місяць");
        month = scanner.nextInt();
        if(month < 0 || month > 12) {
            System.out.println("Місяць повиний бути в діапазоні [1;12]");
            return;
        }
        if(list.stream().anyMatch(m -> m.getMonth() == month)) {
            System.out.println("Запис з таким місяцем уже занятий");
            return;
        }
        System.out.println("Введіть план продукції");
        planProduction = scanner.nextInt();
        System.out.println("Введіть кількість виготовленої продукції");
        production = scanner.nextInt();
        list.add(new Month(month , planProduction , production));
        System.out.println("Запис успішно доданий");
    }

    @Override
    public void edit() {
        Scanner scanner = new Scanner(System.in);
        Month month = get(scanner);
        if(month == null) return;
        System.out.println(month);
        int operation = 0;
        do {
            System.out.println("Введіть 1 щоб змінити план продукції , 2 щоб змінити кількість продукції, 3 щоб завершити");
            operation = scanner.nextInt();
            switch (operation) {
                case 1:
                    System.out.println("Введіть план продукції");
                    month.setPlanProduction(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введіть кількість продукції");
                    month.setProduction(scanner.nextInt());
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
        Month month = get(scanner);
        if(month == null) return;
        System.out.println(month);
        System.out.println("Ви дійсно хочете видалити данний запис? Введіть \"так\" для пітвердження");
        if(scanner.next().equalsIgnoreCase("так")) {
            list.remove(month);
        }
        System.out.println("Запис успішно видалений");
    }

    @Override
    public void search() {
        Scanner scanner = new Scanner(System.in);
        Month month = get(scanner);
        if(month == null) return;
        System.out.println(month);
    }

    @Override
    protected Month get(Scanner scanner) {
        System.out.println("Введіть місяць");
        int month = scanner.nextInt();
        Optional<Month> value = list.stream().filter(t -> t.getMonth() == month).findFirst();
        if(!value.isPresent()) {
            System.out.println("Запису з місяцем " + month + " не існує");
            return null;
        }
        return value.get();
    }
}
