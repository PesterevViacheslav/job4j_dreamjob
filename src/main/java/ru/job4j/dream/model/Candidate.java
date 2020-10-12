package ru.job4j.dream.model;
import java.util.Objects;
/**
 * Class Store - Хранилище данных. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 2. JSP 4. candicates.jsp - список кандидатов.[#282980]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 12.10.2020
 * @version 1
 */
public class Candidate {
    private int id;
    private String name;
    /**
     * Method Candidate. Конструктор
     * @param id ID
     * @param name Имя
     */
    public Candidate(int id, String name) {
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
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                Objects.equals(name, candidate.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    @Override
    public String toString() {
        return "Candidate{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}