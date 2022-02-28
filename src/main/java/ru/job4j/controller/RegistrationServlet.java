package ru.job4j.controller;

import com.google.gson.Gson;
import ru.job4j.store.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@WebServlet(value = "/registration.do")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var gson = (Gson) req.getServletContext().getAttribute("GSON");
        resp.setContentType("application/json; charset=utf-8");
        OutputStream outputStream = resp.getOutputStream();
        var name = req.getParameter("name");
        var email = req.getParameter("email");
        var password = req.getParameter("password");
        UserRepository store = UserRepository.getInstance();
        var user = store.findByEmail(email);
        if (user != null) {
            String json = gson.toJson("Пользователь с таким email уже существует");
            outputStream.write(json.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        } else {
            user = store.create(name, email, password);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        }
    }
}
