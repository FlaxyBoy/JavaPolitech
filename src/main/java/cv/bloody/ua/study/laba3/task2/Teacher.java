package cv.bloody.ua.study.laba3.task2;

import java.util.Date;

public class Teacher {

    private int certificateNumber;
    private String name;
    private String secondName;
    private String fatherName;
    private String departmentName;
    private String scienceDegree;

    /**
     * @param certificateNumber номер посвідчення
     * @param name імя студента
     * @param secondName прізвище студента
     * @param fatherName імя по батькові
     * @param departmentName кафедра
     * @param scienceDegree наукова степінь
     */
    public Teacher(int certificateNumber , String name , String secondName , String fatherName , String departmentName , String scienceDegree) {
        this.certificateNumber = certificateNumber;
        this.name = name;
        this.secondName = secondName;
        this.fatherName = fatherName;
        this.departmentName = departmentName;
        this.scienceDegree = scienceDegree;
    }

    /**
     * Метод для получення рівня кваліфікації викладача
     * @return String - рівень кваліфікації
     */

    public String getScienceDegree() {
        return scienceDegree;
    }

    /**
     * Метод для получення кафедри викладача
     * @return String - назва кафедри
     */

    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * Метод для получення номера посвідчення
     *
     * @return int номер посвідчення
     */

    public int getCertificateNumber() {
        return certificateNumber;
    }

    /**
     * Метод для получення імя викладача
     *
     * @return String імя
     */

    public String getName() {
        return name;
    }

    /**
     * Метод для получення прізвище викладача
     *
     * @return String прізвище
     */

    public String getSecondName() {
        return secondName;
    }

    /**
     * Метод для получення імя по батькові викладача
     *
     * @return String імя по батькові
     */

    public String getFatherName() {
        return fatherName;
    }

    /**
     * Метод для зміни рівня кваліфікації
     * @param scienceDegree рівень кваліфікації
     */

    public void setScienceDegree(String scienceDegree) {
        this.scienceDegree = scienceDegree;
    }

    /**
     * Метод для зміни кафедри викладача
     * @param departmentName
     */

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return String.format("Викладач: %s %s %s \nНомер посвідчення: %s\nКафедра викладача: %s\nРівень кваліфікації: %s" ,
                secondName , name , fatherName , certificateNumber , departmentName , scienceDegree);
    }
}
