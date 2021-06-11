package cv.bloody.ua.study.laba1;

public enum TimeOfTheYearEnum {
    WINTER("Зима" , 1 , 2 , 12),
    SPRING("Весна" , 3 , 4 , 5),
    SUMMER("Літо" , 6 , 7 , 8),
    AUTUMN("Осінь" , 9 , 10 , 11);

    private final String name;
    private final int[] numbersOfMonths;

    /**
     * @param name Імя пори року
     * @param numbersOfMonths Номер місяців пори року
     */

    private TimeOfTheYearEnum(String name , int... numbersOfMonths) {
        this.name = name;
        this.numbersOfMonths = numbersOfMonths;
    }

    /**
     * Метод для получення імя пори року
     *
     * @return String імя пори року
     */

    public String getName() {
        return name;
    }

    /**
     * Статичний метод для получення значення пори року по порядковому номеру місяця
     *
     * @param id - порядковий номер місяця
     * @return Значення місяця
     * @throws IllegalArgumentException якщо порядковий місяця не правильний
     */

    public static TimeOfTheYearEnum getByMonthID(int id) throws IllegalArgumentException {
        switch (id) {
            case 1:
            case 2:
            case 12: return WINTER;
            case 3:
            case 4:
            case 5: return SPRING;
            case 6:
            case 7:
            case 8: return SUMMER;
            case 9:
            case 10:
            case 11: return AUTUMN;
        }
        throw new IllegalArgumentException("illegal month id");
    }

}
