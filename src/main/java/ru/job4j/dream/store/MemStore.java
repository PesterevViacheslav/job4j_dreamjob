package ru.job4j.dream.store;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.User;

/**
 * Class MemStore - Хранилище данных в памяти. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 2. JSP 2. Scriplet. Динамическая таблица.[#282978]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 09.10.2020
 * @version 1
 */
public class MemStore implements Store{
    private static final MemStore INST = new MemStore();
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    /**
     * Method Store. Конструктор
     */
    private MemStore() {
        posts.put(1, new Post(1, "Junior Java Job", "Junior", new Date()));
        posts.put(2, new Post(2, "Middle Java Job", "Middle", new Date()));
        posts.put(3, new Post(3, "Senior Java Job", "Senior", new Date()));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }
    /**
     * Method instOf. Получениие экземпляра
     * @return
     */
    public static MemStore instOf() {
        return INST;
    }
    /**
     * Method findAllPosts. Отображение постов
     * @return
     */
    public Collection<Post> findAllPosts() {
        return posts.values();
    }
    /**
     * Method findAllCandidates. Отображение кандидатов
     * @return
     */
    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }
    /**
     * Method save. Сохранение вакансии
     * @param post Вакансия
     */
    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(POST_ID.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }
    /**
     * Method findPostById. Поиск вакансии по ID
     * @param id ID
     * @return Вакансия
     */
    @Override
    public Post findPostById(int id) {
        return posts.get(id);
    }
    /**
     * Method findCandidateById. Поиск кандидата по ID
     * @param id ID
     * @return Вакансия
     */
    @Override
    public Candidate findCandidateById(int id) {
        return candidates.get(id);
    }
    /**
     * Method truncateCandidate. Очистка коллекции кандидатов
     */
    @Override
    public void truncateCandidate() {
        candidates.clear();
    }
    /**
     * Method truncatePost. Очистка коллекции вакансий
     */
    @Override
    public void truncatePost() {
        posts.clear();
    }
    /**
     * Method create. Создание фото
     * @param photo Фото
     * @return Фото
     */
    @Override
    public Photo create(Photo photo) {
        return null;
    }
    @Override
    public User create(User user) {
        return null;
    }

    /**
     * Method delete. Удаление кандидата
     * @param candidate
     */
    @Override
    public void delete(Candidate candidate) {
    }
    @Override
    public User findUserByEmailPassword(String email, String password) {
        return null;
    }

    /**
     * Method save. Сохранение кандидата
     * @param candidate Вакансия
     */
    public void save(Candidate candidate) {
        candidate.setId(CANDIDATE_ID.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }
}
