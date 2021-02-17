package ru.job4j.dream.servlet;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.PsqlStore;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Class CityServlet - Сервлет обработки списка городов. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 3. Servlet. 0. Servlet. Web.xml[#282990]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 13.10.2020
 * @version 1
 */
public class CityServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String res = "";
        for (City city : PsqlStore.instOf().findAllCities()) {
            res = res + String.format("<option value=\"%s\">%s</option>", city.getId(), city.getName());
        }
        resp.setContentType("text/plane");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(res);
        writer.flush();
    }
}

