package ru.job4j.dream.store;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dream.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
/**
 * Class PsqlStore - Хранилище в БД postgres. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 5. База данных. 1. PsqlStore. [#282960]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 21.10.2020
 * @version 1
 */
public class PsqlStore implements Store {
    private static final Logger LOG = LogManager.getLogger(PsqlStore.class.getName());
    private final BasicDataSource pool = new BasicDataSource();
    /**
     * Method PsqlStore. Конструктор
     */
    private PsqlStore() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }
    /**
     * Class Lazy. Экземпляр хранилища
     */
    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }
    /**
     * Method instOf. Получение экземпляра
     * @return Экземпляр
     */
    public static Store instOf() {
        return Lazy.INST;
    }
    /**
     * Method findAllCities. Отображение постов
     * @return Коллекция вакансий
     */
    @Override
    public Collection<City> findAllCities() {
        List<City> cities = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM city")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    cities.add(new City(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            LOG.error("findAllPosts", e);;
        }
        return cities;
    }
    /**
     * Method findAllPosts. Отображение постов
     * @return Коллекция вакансий
     */
    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    posts.add(new Post(it.getInt("id"), it.getString("name"), it.getString("description"), it.getDate("created")));
                }
            }
        } catch (Exception e) {
            LOG.error("findAllPosts", e);;
        }
        return posts;
    }
    /**
     * Method findAllCandidates. Отображение кандидатов
     * @return Коллекция кандидатов
     */
    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT t.*, p.name as photo_name, ct.name as city_name FROM candidate t, photo p, city ct where p.id = t.photo_id and ct.id = t.city_id")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"),
                                                 it.getString("name"),
                                                 it.getInt("photo_id"),
                                                 it.getString("photo_name"),
                                                 it.getInt("city_id"),
                                                 it.getString("city_name"))
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("findAllCandidates", e);
        }
        return candidates;
    }
    /**
     * Method save. Сохранение вакансии
     * @param post Вакансия
     */
    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }
    /**
     * Method save. Сохранение кандидата
     * @param candidate Кандидат
     */
    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            create(candidate);
        } else {
            update(candidate);
        }
    }
    /**
     * Method create. Создание вакансии
     * @param post Выкансия
     * @return Вакансия
     */
    private Post create(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO post(name, description) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    post.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("create", e);
        }
        return post;
    }
    /**
     * Method create. Создание пользователя
     * @param user Пользователь
     * @return Пользователь
     */
    @Override
    public User create(User user) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO users(name, email, password) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("create", e);
        }
        return user;
    }
    /**
     * Method create. Создание кандидата
     * @param candidate Кандидат
     * @return Кандидат
     */
    private Candidate create(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO candidate(name, photo_id, city_id) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getPhotoId());
            ps.setInt(3, candidate.getCityId());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("create", e);
        }
        return candidate;
    }
    /**
     * Method delete. Удаление кандидата
     * @param candidate Кандидат
     */
    @Override
    public void delete(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("DELETE FROM photo WHERE id = (SELECT id FROM candidate WHERE id = ?)");
             PreparedStatement ps2 =  cn.prepareStatement("DELETE FROM candidate WHERE id = ?")
        ) {
            ps.setInt(1, candidate.getId());
            ps.execute();
            ps2.setInt(1, candidate.getId());
            ps2.execute();
        } catch (Exception e) {
            LOG.error("delete", e);
        }
    }
    /**
     * Method create. Создание фото
     * @param photo Фото
     * @return Фото
     */
    @Override
    public Photo create(Photo photo) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO photo(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, photo.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    photo.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("create", e);
        }
        return photo;
    }
    /**
     * Method update. Изменение вакансии
     * @param post Вакансия
     */
    private void update(Post post) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("UPDATE post SET name = ?, description = ? WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            ps.setInt(3, post.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("update", e);
        }
    }
    /**
     * Method update. Изменение кандидата
     * @param candidate Кандидат
     */
    private void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("UPDATE candidate SET name = ?, photo_id = ?, city_id = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getPhotoId());
            ps.setInt(3, candidate.getCityId());
            ps.setInt(4, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            LOG.error("update", e);
        }
    }
    /**
     * Method truncatePost. Очистка коллекции вакансий
     */
    public void truncatePost() {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("TRUNCATE TABLE post");
             PreparedStatement pssq =  cn.prepareStatement("SELECT setval('post_sq', 0, true)")
        ) {
            ps.execute();
            pssq.execute();
        } catch (Exception e) {
            LOG.error("truncatePost", e);
        }
    }
    /**
     * Method truncateCandidate. Очистка коллекции кандидатов
     */
    public void truncateCandidate() {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("TRUNCATE TABLE candidate");
             PreparedStatement pssq =  cn.prepareStatement("SELECT setval('candidate_sq', 0, true)")
        ) {
            ps.execute();
            pssq.execute();
        } catch (Exception e) {
            LOG.error("truncateCandidate", e);
        }
    }
    /**
     * Method findPostById. Поиск по ID.
     * @param id ID вакансии
     * @return Вакансия
     */
    @Override
    public Post findPostById(int id) {
        Post res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM post WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res = new Post(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDate("created")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("findPostById", e);
        }
        return res;
    }
    /**
     * Method findUserByEmailPassword. Поиск пользователя.
     * @param email
     * @return Пользователь
     */
    @Override
    public User findUserByEmailPassword(String email, String password) {
        User res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res = new User(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("findUserByEmailPassword", e);
        }
        return res;
    }
    /**
     * Method findCandidateById. Поиск по ID.
     * @param id ID кандидата
     * @return Кандидат
     */
    @Override
    public Candidate findCandidateById(int id) {
        Candidate res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT t.*, p.name as photo_name, ct.name as city_name FROM candidate t, photo p, city ct WHERE t.id = ? AND p.id = t.photo_id AND ct.id = t.city_id", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res = new Candidate(rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getInt("photo_id"),
                                        rs.getString("photo_name"),
                                        rs.getInt("city_id"),
                                        rs.getString("city_name")
                    );
                }
            }
        } catch (Exception e) {
            LOG.error("findCandidateById", e);
        }
        return res;
    }
}