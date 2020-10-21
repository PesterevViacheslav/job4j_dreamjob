package ru.job4j.dream.store;
import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
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
     * @return
     */
    public static Store instOf() {
        return Lazy.INST;
    }
    /**
     * Method findAllPosts. Отображение постов
     * @return
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
            e.printStackTrace();
        }
        return posts;
    }
    /**
     * Method findAllCandidates. Отображение кандидатов
     * @return
     */
    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"), it.getString("name")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }
    /**
     * Method save. Сохранение вакансии
     * @param post
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
     * @param candidate
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
     * @param post
     * @return
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
            e.printStackTrace();
        }
        return post;
    }
    /**
     * Method create. Создание кандидата
     * @param candidate
     * @return
     */
    private Candidate create(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO candidate(name) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setString(1, candidate.getName());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }
    /**
     * Method update. Изменение вакансии
     * @param post
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
            e.printStackTrace();
        }
    }
    /**
     * Method update. Изменение кандидата
     * @param candidate
     */
    private void update(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("UPDATE candidate SET name = ? WHERE id = ?")
        ) {
            ps.setString(1, candidate.getName());
            ps.setInt(2, candidate.getId());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
    /**
     * Method findPostById. Поиск по ID.
     * @param id ID вакансии
     * @return Заявка
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
            e.printStackTrace();
        }
        return res;
    }
    /**
     * Method findCandidateById. Поиск по ID.
     * @param id ID вакансии
     * @return Заявка
     */
    @Override
    public Candidate findCandidateById(int id) {
        Candidate res = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM candidate WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res = new Candidate(rs.getInt("id"),
                            rs.getString("name")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}