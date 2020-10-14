package ru.job4j.dream.store;
import ru.job4j.dream.model.Post;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import ru.job4j.dream.model.Candidate;
/**
 * Class Store - Хранилище данных. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 2. JSP 2. Scriplet. Динамическая таблица.[#282978]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 09.10.2020
 * @version 1
 */
public class Store {
    private static final Store INST = new Store();
    private Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();
    private static AtomicInteger POST_ID = new AtomicInteger(4);
    private static AtomicInteger CANDIDATE_ID = new AtomicInteger(4);
    /**
     * Method Store. Конструктор
     */
    private Store() {
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
    public static Store instOf() {
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
        post.setId(POST_ID.incrementAndGet());
        posts.put(post.getId(), post);
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