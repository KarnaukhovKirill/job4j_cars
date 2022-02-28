package ru.job4j.controller;

import com.google.gson.Gson;
import ru.job4j.model.Advert;
import ru.job4j.model.User;
import ru.job4j.store.AdRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(value = "/loadMyAdverts.do")
public class LoadMyAdvertsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var gson = (Gson) req.getServletContext().getAttribute("GSON");
        var user = (User) req.getSession().getAttribute("user");
        List<Advert> adverts = AdRepository.getInstance().getByUser(user);
        resp.setContentType("aplication/json; charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();
        String json = gson.toJson(adverts);
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
