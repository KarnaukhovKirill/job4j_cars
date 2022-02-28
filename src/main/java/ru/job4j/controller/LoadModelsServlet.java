package ru.job4j.controller;

import com.google.gson.Gson;
import ru.job4j.store.ModelRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/loadModel.do")
public class LoadModelsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var gson = (Gson) req.getServletContext().getAttribute("GSON");
        int id = Integer.parseInt(req.getParameter("id"));
        var models = ModelRepository.getInstance().findModels(id);
        resp.setContentType("aplication/json; charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();
        String json = gson.toJson(models);
        outputStream.write(json.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
