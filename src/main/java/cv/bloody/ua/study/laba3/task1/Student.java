package cv.bloody.ua.study.laba3.task1;

import java.util.Date;

public class Student {

    private Date birthday;
    private String name;
    private String secondName;
    private String fatherName;

    /**
     * @param birthday Дата народження
     * @param name імя студента
     * @param secondName прізвище студента
     * @param fatherName імя по батькові
     */
    public Student(Date birthday , String name , String secondName , String fatherName) {
        this.birthday = birthday;
        this.name = name;
        this.secondName = secondName;
        this.fatherName = fatherName;
    }

    /**
     * Метод для получення дати народження
     *
     * @return Date дата народження
     */

    public Date getBirthday() {
        return birthday;
    }

    /**
     * Метод для получення імя студента
     *
     * @return String імя
     */

    public String getName() {
        return name;
    }

    /**
     * Метод для получення прізвище студента
     *
     * @return String прізвище
     */

    public String getSecondName() {
        return secondName;
    }

    /**
     * Метод для получення імя по батькові студента
     *
     * @return String імя по батькові
     */

    public String getFatherName() {
        return fatherName;
    }
}
