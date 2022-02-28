package ru.job4j.controller;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.model.Photo;
import ru.job4j.store.AdRepository;
import ru.job4j.util.Config;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(value = "/photoUpload.do")
public class PhotoUploadServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(PhotoUploadServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        String id = req.getParameter("id");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        var folderPath = Config.getProperty("CarPhoto");
        Photo photo = new Photo();
        try {
            var items = upload.parseRequest(req);
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdir();
            }
            var item = items.get(0);
            if (!item.isFormField()) {
                var array = item.getName().split(Pattern.quote("."));
                var ext = array[array.length - 1];
                File file = new File(folder + File.separator + id + "." + ext);
                try (FileOutputStream out = new FileOutputStream(file)) {
                    out.write(item.getInputStream().readAllBytes());
                }
                photo.setName(id + "." + ext);
            }
        } catch (FileUploadException e) {
            LOG.error("Photo upload error", e);
        }
        AdRepository.getInstance().setPhoto(Integer.parseInt(id), photo);
    }
}
