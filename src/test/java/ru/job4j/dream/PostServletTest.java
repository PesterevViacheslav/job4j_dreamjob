package ru.job4j.dream;
import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.servlet.PostServlet;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;
import ru.job4j.dream.store.Store;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;
/**
 * Class PostServletTest - Автотест сервлета обработки вакансий. Решение задач уровня Middle. Части 012. Servlet JSP.
 * 7. Mockito. 1. Произвести автоматическое тестирование всех сервлетов.[282999]
 *
 * @author Viacheslav Pesterev (pesterevvv@gmail.com)
 * @since 02.01.2021
 * @version 1
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
//@PowerMockIgnore({"org.mockito.*"})
public class PostServletTest {
    /**
     * Тест метода сохранения вакансии.
     */
    @Test
    public void whenAddPostThenStoreIt() throws IOException {
        Store store = new MemStore();
        PowerMockito.mockStatic(PsqlStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(store);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Junior Java Job");
        when(req.getParameter("dsc")).thenReturn("Junior");
        new PostServlet().doPost(req, resp);
        assertThat(store.findAllPosts().iterator().next().getName(), is("Junior Java Job"));
    }
}