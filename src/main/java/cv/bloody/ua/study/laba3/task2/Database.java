package cv.bloody.ua.study.laba3.task2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {

    private static final Database instance = new Database();
    private static final SimpleDateFormat formarter = new SimpleDateFormat("dd:MM:yyyy");

    /**
     * Метод для получення об'єкта класса який може бути лиш в 1 екземплярі
     *
     * @return Database - інстанс классу
     */

    public static Database getInstance() {
        return instance;
    }

    private List<Teacher> teacherList;

    private Database() {
        teacherList = new ArrayList<>();
    }

    /**
     * Метод для вводу даних про викладача з консолі
     */

    public void inputStudent() {
        int certificateNumber;
        String name , secondName, fatherName , departmentName , scienceDegree;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть імя викладача");
        name = scanner.nextLine();
        System.out.println("Введіть прізвище викладача");
        secondName = scanner.nextLine();
        System.out.println("Введіть імя по батькові викладача");
        fatherName = scanner.nextLine();
        System.out.println("Введіть номер посвідчення");
        certificateNumber = scanner.nextInt();
        System.out.println("Введіть назву кафедри викладача");
        departmentName = scanner.next();
        System.out.println("Введіть рівень кваліфікації викладача");
        scienceDegree = scanner.next();
        teacherList.add(new Teacher(certificateNumber , name , secondName , fatherName , departmentName , scienceDegree));
    }

    /**
     * Метод для редактування данних
     */

    public void edit() {
        int certificateNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер посвідчення викладача");
        certificateNumber = scanner.nextInt();
        Optional<Teacher> teacher = teacherList.stream().filter(t -> t.getCertificateNumber() == certificateNumber).findFirst();
        if(!teacher.isPresent()) {
            System.out.println("Викладача з данним номером посвідчення не існує");
            return;
        }
        System.out.println(teacher.get());
        System.out.println("Введіть 1 щоб змінити кафедру викладача, 2 щоб змінити рівень кваліфікації");
        int operation = scanner.nextInt();
        switch (operation) {
            case 1:
                System.out.println("Введіть назву нової кафедри");
                teacher.get().setDepartmentName(scanner.next());
                break;
            case 2:
                System.out.println("Введіть назву нового рівня кваліфікації");
                teacher.get().setScienceDegree(scanner.next());
                break;
            default:
                System.out.println("Невідома операція");
        }
    }

    /**
     * Метод для виделення данних
     */

    public void delete() {
        int certificateNumber;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть номер посвідчення викладача");
        certificateNumber = scanner.nextInt();
        Optional<Teacher> teacher = teacherList.stream().filter(t -> t.getCertificateNumber() == certificateNumber).findFirst();
        if(!teacher.isPresent()) {
            System.out.println("Викладача з данним номером посвідчення не існує");
            return;
        }
        System.out.println(teacher.get());
        System.out.println("Ви дійсно хочете видалити даного викладача? Введіть \"так\" для пітвердження");
        if(scanner.nextLine().equalsIgnoreCase("так")) {
            teacherList.remove(teacher.get());
        }
        System.out.println("Викладач успішно видалений");
    }

    public void search() {
        String departmentName;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть кафедру викладача");
        departmentName = scanner.nextLine();
        AtomicInteger integer = new AtomicInteger(0);
        teacherList.stream().filter(teacher -> teacher.getDepartmentName().equals(departmentName))
                .forEach(teacher -> {
                    System.out.printf("%s) %s\n" , integer.incrementAndGet() , teacher);
                });
    }
}
