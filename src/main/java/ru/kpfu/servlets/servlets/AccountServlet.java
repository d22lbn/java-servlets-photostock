package ru.kpfu.servlets.servlets;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.kpfu.servlets.service.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/account")
@MultipartConfig
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);
        user = AuthorizationVerification.rememberUser(null, cookies, db);
        if (user != null) {
            String s = user.getName() + " " + user.getSurname().charAt(0) + ".";
            req.setAttribute("userName", s);
        } else {
            String path = "http://localhost:8080" + req.getContextPath();
            resp.sendRedirect(path + "/authorization");
            return;
        }
        session.setAttribute(ApplicationParameters.SESSION_USER, user);

        String surname = user.getSurname();
        String name = user.getName();
        String email = user.getEmail();
        String balance = String.valueOf(user.getBalance());

        req.setAttribute("surname", surname);
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("balance", balance);

        ArrayList<String> uploaded = new ArrayList<>();
        ArrayList<String> ids = db.getPhotoIdByUserId(ApplicationParameters.UPLOADED, user.getId());
        if (ids != null && ids.size() > 0) {
            System.out.println("AAAAAAAAAAAAAAA");
            for (String id : ids) {
                Photo photo = db.getPhotoById(ApplicationParameters.PHOTOS, Integer.parseInt(id));
                System.out.println(photo.getPath());
                uploaded.add(photo.getPath());
            }
            System.out.println("AAAAAAAAAAAAAAA");
            req.setAttribute("uploaded", uploaded);
        }

        ArrayList<String> purchased = new ArrayList<>();
        ids = db.getPhotoIdByUserId(ApplicationParameters.PURCHASED, user.getId());
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                Photo photo = db.getPhotoById(ApplicationParameters.PHOTOS, Integer.parseInt(id));
                purchased.add(photo.getPath());
            }
            req.setAttribute("purchased", purchased);
        }

        req.getRequestDispatcher("/WEB-INF/view/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        HttpSession session = req.getSession();
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);

        System.out.println(req.getParameter("upl"));
        System.out.println(req.getParameter("enter"));


        if (req.getParameter("upl") != null) {
            Part filePart = req.getPart("filename");
            InputStream fileContent = filePart.getInputStream();

            String path = "C:\\Users\\d22lb\\semwor3\\servletExampleFirst\\src\\main\\webapp\\photo\\";
            File uploads = new File(path);


            String photoPath = "item" + db.getTableSize(ApplicationParameters.PHOTOS) + ".jpg";
            File file = new File(uploads, photoPath);
            Files.copy(fileContent, file.toPath());


            String photoName = req.getParameter("photoname");
            String photoDescription = req.getParameter("photodescription");
            String photoPrice = req.getParameter("photoprice");
            String photoCanBuy = req.getParameter("photocanbuy");

            System.out.println(photoName + " " + photoDescription + " " +
                    photoPrice + " " + photoCanBuy);

            Photo photo = new Photo("photo/" + photoPath, photoName, photoDescription,
                    Integer.parseInt(photoPrice), Integer.parseInt(photoCanBuy), 0);
            db.addEntry(ApplicationParameters.PHOTOS, photo.getData());
            String photoId = db.getPhotoIdByPath(ApplicationParameters.PHOTOS, "photo/" + photoPath);
            photo = db.getPhotoById(ApplicationParameters.PHOTOS, Integer.parseInt(photoId));


            System.out.println("PHOTOID" + photoId);
            System.out.println("USER: " + user);
            System.out.println("PHOTO: " + photo);


            ArrayList<ArrayList<String>> entryUpl = new ArrayList<>();
            entryUpl.add(User.getCoup("userId", String.valueOf(user.getId())));
            entryUpl.add(User.getCoup("photoId", String.valueOf(photo.getId())));
            db.addEntry(ApplicationParameters.UPLOADED, entryUpl);
        }

        if (req.getParameter("enter") != null) {
            String surname = req.getParameter("surname");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            if (val(surname)) {
                user.setSurname(surname);
            }
            if (val(name)) {
                user.setName(name);
            }
            if (val(email)) {
                user.setEmail(email);
            }
            if (val(password)) {
                user.setPassword(password);
            }

            db.updateUserFieldById(ApplicationParameters.USERS, user.getId(), User.getCoup("surname", user.getSurname()));
            db.updateUserFieldById(ApplicationParameters.USERS, user.getId(), User.getCoup("name", user.getName()));
            db.updateUserFieldById(ApplicationParameters.USERS, user.getId(), User.getCoup("email", user.getEmail()));
            db.updateUserFieldById(ApplicationParameters.USERS, user.getId(), User.getCoup("password", user.getPassword()));

            session.setAttribute(ApplicationParameters.SESSION_USER, user);
        }

        doGet(req, resp);
    }

    private boolean val(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() == 0) {
            return false;
        }
        return true;
    }
}
