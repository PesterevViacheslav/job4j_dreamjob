package ru.job4j.dream.servlet;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class JsonController extends HttpServlet {
    class Response {
        protected String text;
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Gson gson = new Gson();
        Enumeration en = req.getParameterNames();
        Response response = null;
        while (en.hasMoreElements()) {
            response = gson.fromJson((String) en.nextElement(), Response.class);
        }
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println("{\"name\" : \"" + response.text
                        + "\", \"text\" : \" Nice to meet you, \"}");
        writer.flush();
    }

}