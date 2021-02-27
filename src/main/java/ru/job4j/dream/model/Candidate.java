package ru.job4j.dream.model;
import java.util.Objects;
/**
 * Class Candidate - Кандидат. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 2. JSP 4. candidates.jsp - список кандидатов.[#282980]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 12.10.2020
 * @version 1
 */
public class Candidate {
    private int id;
    private String name;
    private int photoId;
    private String photoName;
    private int cityId;
    private String cityName;
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
     * Method Candidate. Конструктор
     * @param id ID
     * @param name Имя
     * @param photoId ID фото
     * @param photoName Название файла
     */
    public Candidate(int id,
                     String name,
                     int photoId,
                     String photoName,
                     int cityId,
                     String cityName) {
        this.id = id;
        this.name = name;
        this.photoId = photoId;
        this.photoName = photoName;
        this.cityId = cityId;
        this.cityName = cityName;
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
     * Method getPhotoId. Получение ID фото
     * @return id
     */
    public int getPhotoId() {
        return photoId;
    }
    /**
     * Method getPhotoName. Получение названия файла
     * @return Название
     */
    public String getPhotoName() {
        return photoName;
    }
    /**
     * Method setName. Установка имени
     * @param name Имя
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method getCityId. Получение id города
     * @return id
     */
    public int getCityId() {
        return cityId;
    }
    /**
     * Method getCityName. Получение названия города
     * @return Название
     */
    public String getCityName() {
        return cityName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id && Objects.equals(name, candidate.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
    @Override
    public String toString() {
        return "Candidate{" + "id=" + id + ", name='" + name + '\''
                            +  ", photoId='" + photoId + '\'' + ", photoName='"
                            + photoName + '\'' + ", cityId='" + cityId + '\'' + '}';
    }
}