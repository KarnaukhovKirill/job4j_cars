package ru.job4j.controller;

import ru.job4j.util.Config;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet(value = "/photoLoader.do")
public class PhotoLoaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String folder = Config.getProperty("CarPhoto");
        File photo = null;
        for (File file : new File(folder).listFiles()) {
            if (name.equals(file.getName())) {
                photo = file;
                break;
            }
        }
        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-Disposition", "attachment; filename=\"" + photo.getName() + "\"");
        try (FileInputStream stream = new FileInputStream(photo)) {
            resp.getOutputStream().write(stream.readAllBytes());
        }
    }
}
