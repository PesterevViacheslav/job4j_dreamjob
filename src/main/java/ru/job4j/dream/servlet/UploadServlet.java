package ru.job4j.dream.servlet;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.dream.model.Photo;
import ru.job4j.dream.store.PsqlStore;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * Class UploadServlet - Сервлет обработки загрузки файла.
 * Решение задач уровня Middle. Части 012. Servlet JSP.
 * 5.1. Form. 1. Загрузка и скачивание файла.[#282970]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 09.11.2020
 * @version 1
 */
public class UploadServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UploadServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        List<String> images = new ArrayList<>();
        for (File name : new File(
       System.getenv("CATALINA_HOME") + "\\bin\\images").listFiles()) {
            images.add(name.getName());
        }
        req.setAttribute("images", images);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/upload.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    File file = new File(folder + File.separator + item.getName());
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                        Photo photo = new Photo(0, item.getName());
                        System.out.println(photo);

                        PsqlStore.instOf().create(photo);
                    }
                }
            }
        } catch (FileUploadException e) {
            LOG.error(e.getStackTrace());
        }
        doGet(req, resp);
    }
}
