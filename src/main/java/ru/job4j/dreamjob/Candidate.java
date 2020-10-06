package ru.job4j.dreamjob;
/**
 * Class Candidate - Кандидат. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 1. Техническое задание - проект "Работа мечты"[#282972]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 06.10.2020
 * @version 1
 */
public class Candidate implements User {
    private String name;
    /**
     * Method HR. Конструктор
     * @param name
     */
    public Candidate(String name) {
        this.name = name;
    }
    /**
     * Method getName. Получить имя
     * @return Имя
     */
    public String getName() {
        return name;
    }
    /**
     * Method publish. Публикация документа
     * @param document Документ
     */
    @Override
    public void publish(Document document) {
        System.out.println("publish summary");
    }
    /**
     * Method confirm. Подтверждение документа
     * @param document Документ
     */
    @Override
    public void confirm(Document document) {
        System.out.println("confirm vacancy");
    }
    @Override
    public String toString() {
        return "Candidate{" + "name='" + name + '\'' + '}';
    }
}
