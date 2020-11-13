package ru.job4j.dream.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if ("root@local".equals(email) && "root".equals(password)) {
            resp.sendRedirect(req.getContextPath() + "/post/posts.do");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp?error=").forward(req, resp);
        }
    }
}
