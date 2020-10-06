package ru.job4j.dreamjob;
/**
 * Class Summary - Резюме. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 1. Техническое задание - проект "Работа мечты"[#282972]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 06.10.2020
 * @version 1
 */
public class Summary extends Document {
    private String position;
    /**
     * Method Summary. Конструктор
     * @param text Текст
     * @param name Название
     * @param position Должность
     */
    public Summary(String name, String text, String position) {
        super(name, text);
        this.position = position;
    }
    /**
     * Method getPosition. Получение должности
     * @return Должность
     */
    public String getPosition() {
        return position;
    }
    @Override
    public String toString() {
        return "Summary{" + "position='" + position + '\'' + '}' + super.toString();
    }
}
