package ru.job4j.dream.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;
/**
 * Class UserServlet - Сервлет создания пользователя. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 6. Filter, Security. 0. Страница Login.jsp[#282992]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 13.11.2020
 * @version 1
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usrName = req.getParameter("name");
        String usrEmail = req.getParameter("email");
        String usrPassword = req.getParameter("password");
        if (usrName != null && usrEmail != null && usrPassword != null) {
            User user = PsqlStore.instOf().findUserByEmailPassword(usrEmail, usrPassword);
            HttpSession sc = req.getSession();
            if (user != null) {
                sc.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/post/posts.do");
            } else {
                sc.setAttribute("user", PsqlStore.instOf().create(new User(0, usrName, usrEmail, usrPassword)));
                resp.sendRedirect(req.getContextPath() + "/post/posts.do");
            }
        } else {
            req.setAttribute("error", "Не указаны имя, email или пароль");
            req.getRequestDispatcher("login.jsp?error=").forward(req, resp);
        }
    }
}