package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.*;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/recommendation")
public class RecommendationServlet extends HttpServlet {

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
        ArrayList<String> paths = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "path");
        req.setAttribute("paths", paths);
        ArrayList<String> names = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "name");
        req.setAttribute("names", names);
        ArrayList<String> descriptions = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "description");
        req.setAttribute("descriptions", descriptions);
        ArrayList<String> prices = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "price");
        req.setAttribute("prices", prices);

        req.getRequestDispatcher("/WEB-INF/view/recommendation.jsp").forward(req, resp);
    }
}
