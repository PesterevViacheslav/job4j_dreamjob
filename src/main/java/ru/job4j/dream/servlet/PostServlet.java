package ru.job4j.dream.servlet;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.Store;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Store.instOf().save(new Post(0, req.getParameter("name"), "dsc", new Date()));
        resp.sendRedirect(req.getContextPath() + "/post/posts.jsp");
    }
}