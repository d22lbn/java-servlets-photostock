package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.*;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
        Cookie[] cookies = req.getCookies();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);
        user = AuthorizationVerification.rememberUser(user, cookies, db);
        if (user != null) {
            String s = user.getName() + " " + user.getSurname().charAt(0) + ".";
            req.setAttribute("userName", s);
        }
        session.setAttribute(ApplicationParameters.SESSION_USER, user);


        req.getRequestDispatcher("/WEB-INF/view/search.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String search = req.getParameter("search");
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);

        ArrayList<String> ids = db.getPhotoIdByName(ApplicationParameters.PHOTOS, search);
        ArrayList<String> paths = new ArrayList<>();
        if (ids != null && ids.size() > 0) {
            for (String id : ids) {
                Photo photo = db.getPhotoById(ApplicationParameters.PHOTOS, Integer.parseInt(id));
                paths.add(photo.getPath());
            }
            req.setAttribute("paths", paths);
        }

        doGet(req, resp);
    }
}
