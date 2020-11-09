package ru.job4j.dream.servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/**
 * Class DownloadServlet - Сервлет обработки скачивания файла. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 5.1. Form. 1. Загрузка и скачивание файла.[#282970]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 09.11.2020
 * @version 1
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("name=" + name);
        resp.setContentType("image/png");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
        File file = new File("images" + File.separator + name);
        try (FileInputStream in = new FileInputStream(file)) {
            resp.getOutputStream().write(in.readAllBytes());
        }
    }
}