package ru.job4j.dreamjob;
/**
 * Class HR - Кадровик. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 1. Техническое задание - проект "Работа мечты"[#282972]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 06.10.2020
 * @version 1
 */
public class HR implements User {
    private String name;
    /**
     * Method HR. Конструктор
     * @param name
     */
    public HR(String name) {
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
        System.out.println("publish vacancy");
    }
    /**
     * Method confirm. Подтверждение документа
     * @param document Документ
     */
    @Override
    public void confirm(Document document) {
        System.out.println("confirm summary");
    }

    /**
     * Method invite. Пригласить кандидата
     * @param candidate Кандидат
     * @param vacancy Вакансия
     */
    public void invite(Candidate candidate, Vacancy vacancy) {
        System.out.println("invite candidate");
    }

    @Override
    public String toString() {
        return "HR{" + "name='" + name + '\'' + '}';
    }
}
