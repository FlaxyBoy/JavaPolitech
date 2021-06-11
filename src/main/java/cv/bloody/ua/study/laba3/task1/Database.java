package cv.bloody.ua.study.laba3.task1;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    private List<Student> studentList;

    private Database() {
        studentList = new ArrayList<>();
    }

    /**
     * Метод для вводу даних про студента з консолі
     */

    public void inputStudent() {
        String name , secondName, fatherName;
        int year , month , day;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть імя студента");
        name = scanner.nextLine();
        System.out.println("Введіть прізвище студента");
        secondName = scanner.nextLine();
        System.out.println("Введіть імя по батькові студента");
        fatherName = scanner.nextLine();
        System.out.println("Введіть рік народження студента");
        year = scanner.nextInt();
        System.out.println("Введіть місяць народження студента");
        month = scanner.nextInt();
        System.out.println("Введіть день народження студента");
        day = scanner.nextInt();
        studentList.add(new Student(Date.from(LocalDate.of(year , Month.of(month) , day).atStartOfDay()
                .atZone(ZoneId.systemDefault()).toInstant()) , name , secondName , fatherName));
    }

    /**
     * Метод для виводу студента з найближнім днем народження
     */

    public void showNearBirthdayStudents() {
        Date date = new Date();
        if(studentList.isEmpty()) throw new IllegalStateException("Щоб вивести студента з найближчим днем народження ви, повині заповнити базу даних");
        Collections.sort(studentList , Comparator.comparingInt(o -> getDayToBirthday(o.getBirthday(), date)));
        Student student = studentList.get(0);
        System.out.printf("Студент з найближнім днем народження: %s %s %s - %s\n" , student.getSecondName() , student.getName() ,
                student.getFatherName() , formarter.format(student.getBirthday()));
    }

    /**
     * @param date дата з якої треба рахувати дні
     * @param birthday дата народження
     * @return int - кількість до дня народження
     */

    private int getDayToBirthday(Date date , Date birthday) {
        date.setYear(birthday.getYear());
        int day = (int)( (date.getTime() - birthday.getTime()) / (1000 * 60 * 60 * 24));
        if(day >= 0) return day;
        return 365 + day;
    }
}
