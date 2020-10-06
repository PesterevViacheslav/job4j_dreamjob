package ru.job4j.dreamjob;
/**
 * Class Vacancy - Вакансия. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 1. Техническое задание - проект "Работа мечты"[#282972]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 06.10.2020
 * @version 1
 */
public class Vacancy extends Document {
    private int salary;
    /**
     * Method Vacancy. Конструктор
     * @param text Текст
     * @param name Текст
     * @param salary Зарплата
     */
    public Vacancy(String name, String text, int salary) {
        super(name, text);
        this.salary = salary;
    }
    /**
     * Method getSalary. Получение зароплаты
     * @return ЗП
     */
    public int getSalary() {
        return salary;
    }
    @Override
    public String toString() {
        return "Vacancy{" + "salary=" + salary + '}' + super.toString();
    }
}
