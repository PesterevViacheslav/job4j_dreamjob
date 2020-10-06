package ru.job4j.dreamjob;
/**
 * Interface User - Пользователь. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 1. Техническое задание - проект "Работа мечты"[#282972]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 06.10.2020
 * @version 1
 */
public interface User {
    /**
     * Method publish. Публикация документа
     * @param document Документ
     */
    void publish(Document document);
    /**
     * Method confirm. Подтверждение документа
     * @param document Документ
     */
    void confirm(Document document);
}