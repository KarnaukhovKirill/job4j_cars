package ru.job4j.controller;

import ru.job4j.store.AdRepository;
import ru.job4j.store.PhotoRepository;
import ru.job4j.util.Config;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@WebServlet(value = "/deleteAdvert.do")
public class DeleteAdvertServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var userId = req.getParameter("userId");
        var photoId = req.getParameter("photoId");
        AdRepository.getInstance().delete(Integer.parseInt(userId));
        var photo = PhotoRepository.getInstance().delete(Integer.parseInt(photoId));
        var name = photo.getName();
        String folder = Config.getProperty("CarPhoto");
        File rsl = null;
        for (File file : new File(folder).listFiles()) {
            if (name.equals(file.getName())) {
                rsl = file;
                break;
            }
        }
        rsl.delete();
    }
}
