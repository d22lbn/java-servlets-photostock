package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.*;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/account")
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
            for (String id : ids) {
                Photo photo = db.getPhotoById(ApplicationParameters.PHOTOS, Integer.parseInt(id));
                uploaded.add(photo.getPath());
            }
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

        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);

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
