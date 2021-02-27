package ru.job4j.dream.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.http.HttpSession;
/**
 * Class AuthServlet - Сервлет аутентификации. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 6. Filter, Security. 0. Страница Login.jsp[#282992]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 13.11.2020
 * @version 1
 */
public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (password != null && email != null) {
            User user = PsqlStore.instOf().findUserByEmailPassword(email, password);
            HttpSession sc = req.getSession();
            if (user == null) {
                req.setAttribute("error", "Пользователь не найден");
                req.getRequestDispatcher("login.jsp?error=").forward(req, resp);
            } else {
                sc.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/post/posts.do");
            }
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp?error=").forward(req, resp);
        }
    }
}