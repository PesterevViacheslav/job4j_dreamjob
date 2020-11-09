package ru.job4j.dream.servlet;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
/**
 * Class CandidateServlet - Сервлет обработки кандидатов. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 3. Servlet. 1. CandidateServlet. Создание кандидата.[#282989]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 13.10.2020
 * @version 1
 */
public class CandidateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("candidates", PsqlStore.instOf().findAllCandidates());
        req.getRequestDispatcher("candidates.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("photoId").equals("delete")) {
            new File(System.getenv("CATALINA_HOME") + "\\bin\\images\\" + PsqlStore.instOf().findCandidateById(Integer.valueOf((req.getParameter("id")))).getPhotoName()).delete();
            PsqlStore.instOf().delete(new Candidate(Integer.valueOf((req.getParameter("id"))),
                    req.getParameter("name"),
                    0,
                    req.getParameter("photoName")));
        } else {
            PsqlStore.instOf().save(new Candidate(Integer.valueOf((req.getParameter("id") != null) ? req.getParameter("id") : Integer.toString(0)),
                    req.getParameter("name"),
                    Integer.valueOf(req.getParameter("photoId")),
                    req.getParameter("photoName")));
        }
        resp.sendRedirect(req.getContextPath() + "/candidate/candidates.do");
    }
}