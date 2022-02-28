package ru.job4j.controller;

import com.google.gson.Gson;
import ru.job4j.model.User;
import ru.job4j.store.AdRepository;
import ru.job4j.store.CarRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/createAdvert.do")
public class CreateAdvertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var gson = (Gson) req.getServletContext().getAttribute("GSON");
        var brand = req.getParameter("brand");
        var model = req.getParameter("model");
        var body = req.getParameter("body");
        var production = req.getParameter("production");
        var engine = req.getParameter("engine");
        var vin = req.getParameter("vin");
        var description = req.getParameter("description");
        var user = (User) req.getSession().getAttribute("user");
        var car = CarRepository.getInstance().create(user.getId(), production, vin, Integer.parseInt(engine),
                Integer.parseInt(body), Integer.parseInt(brand), Integer.parseInt(model));

        var advert = AdRepository.getInstance().create(user.getId(), car.getId(), description);
        resp.setContentType("aplication/json; charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();
        String json = gson.toJson(advert.getId());
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
