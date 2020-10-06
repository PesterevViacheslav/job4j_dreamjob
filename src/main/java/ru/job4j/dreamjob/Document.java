package ru.job4j.dreamjob;
/**
 * Class Document - Документ. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 1. Техническое задание - проект "Работа мечты"[#282972]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 06.10.2020
 * @version 1
 */
public class Document {
    private String name;
    private String text;
    /**
     * Method Document. Конструктор
     * @param text Текст документа
     * @param name Название документа
     */
    public Document(String name, String text) {
        this.text = text;
        this.name = name;
    }
    /**
     * Method getText. Получение текста
     * @return Текст
     */
    public String getText() {
        return text;
    }
    /**
     * Method getName. Получение названия
     * @return Название
     */
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return "Document{" + "text='" + text + '\'' + "name='" + name + '\'' + '}';
    }
}
