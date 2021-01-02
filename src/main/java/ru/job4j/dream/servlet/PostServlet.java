package ru.job4j.dream.servlet;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PsqlStore;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
/**
 * Class PostServlet - Сервлет обработки вакансий. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 3. Servlet. 0. Servlet. Web.xml[#282990]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 13.10.2020
 * @version 1
 */
public class PostServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().save(new Post(Integer.valueOf((req.getParameter("id") != null) ? req.getParameter("id") :  Integer.toString(0)),
                                     req.getParameter("name"),
                                     req.getParameter("dsc"),
                                     new Date()));
        resp.sendRedirect(req.getContextPath() + "/post/posts.do");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("posts", new ArrayList<>(PsqlStore.instOf().findAllPosts()));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("posts.jsp").forward(req, resp);
    }
}