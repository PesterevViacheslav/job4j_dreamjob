package ru.job4j.dream.store;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.model.Post;
import java.util.Date;
/**
 * Class PsqlMain - Демонстрация. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 5. База данных. 1. PsqlStore. [#282960]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 21.10.2020
 * @version 1
 */
public class PsqlMain {
    public static void main(String[] args) {
        Store store = PsqlStore.instOf();
        store.truncatePost();
        store.save(new Post(0, "Java Job", "dsc", new Date()));
        store.save(new Post(0, "Java Job2", "dsc2", new Date()));
        for (Post post : store.findAllPosts()) {
            System.out.println(post);
        }
        store.save(new Post(2, "Java Job2", "dscNew", new Date()));
        System.out.println(store.findPostById(2));
        store.truncateCandidate();
        store.save(new Candidate(0, "Candidate"));
        store.save(new Candidate(0, "Candidate2"));
        for (Candidate candidate : store.findAllCandidates()) {
            System.out.println(candidate);
        }
        store.save(new Candidate(2, "Candidate22"));
        System.out.println(store.findCandidateById(2));
    }
}