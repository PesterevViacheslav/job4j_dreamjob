package ru.job4j.dream.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Class IndexServlet - Сервлет обработки корневой страницы. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 5.1. Form. 1. Загрузка и скачивание файла.[#282970]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 09.11.2020
 * @version 1
 */
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
