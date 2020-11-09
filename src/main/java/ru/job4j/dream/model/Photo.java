package ru.job4j.dream.model;
import java.util.Objects;
/**
 * Class Photo - Фото. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 2. JSP 5. 1. Загрузка и скачивание файла.[#282970]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 12.10.2020
 * @version 1
 */
public class Photo {
    private int id;
    private String name;
    /**
     * Method Photo. Конструктор
     * @param id ID
     * @param name Имя
     */
    public Photo(int id, String name) {
        this.id = id;
        this.name = name;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo  = (Photo) o;
        return id == photo.id &&
                Objects.equals(name, photo.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    @Override
    public String toString() {
        return "Photo{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}