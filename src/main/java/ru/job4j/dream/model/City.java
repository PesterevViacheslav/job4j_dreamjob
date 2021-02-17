package ru.job4j.dream.model;
import java.util.Objects;
/**
 * Class City - Город. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 2. JSP 5. 1. Загрузка и скачивание файла.[#282970]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 12.10.2020
 * @version 1
 */
public class City {
    private int id;
    private String name;
    private int selected;
    /**
     * Method City. Конструктор
     * @param id ID
     * @param name Имя
     */
    public City(int id, String name) {
        this.id = id;
        this.name = name;
        this.selected = 0;
    }
    /**
     * Method getId. Получение ID
     * @return ID
     */
    public int getId() {
        return id;
    }
    /**
     * Method setId. Установка ID
     * @param id ID
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Method getName. Получение имени
     * @return Имя
     */
    public String getName() {
        return name;
    }
    /**
     * Method setName. Установка имени
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method getSelected. Получение признака текущего в списке
     * @return Признак
     */
    public int getSelected() {
        return selected;
    }
    /**
     * Method setSelected. Установка признака текущего в списке
     * @param selected
     */
    public void setSelected(int selected) {
        this.selected = selected;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city  = (City) o;
        return id == city.id &&
                Objects.equals(name, city.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}