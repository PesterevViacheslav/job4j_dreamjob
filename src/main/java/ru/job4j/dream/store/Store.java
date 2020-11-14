package ru.job4j.dream.store;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.model.User;
import java.util.Collection;
/**
 * Interface Store - Хранилище. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 5. База данных. 1. PsqlStore. [#282960]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 21.10.2020
 * @version 1
 */
public interface Store {
    Collection<Post> findAllPosts();
    Collection<Candidate> findAllCandidates();
    void save(Post post);
    void save(Candidate candidate);
    Post findPostById(int id);
    Candidate findCandidateById(int id);
    void truncateCandidate();
    void truncatePost();
    Photo create(Photo photo);
    User create(User user);
    void delete(Candidate candidate);
    User findUserByEmailPassword(String email, String password);
}