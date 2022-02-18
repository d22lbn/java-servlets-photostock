package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.ApplicationParameters;
import ru.kpfu.servlets.service.DBHelper;
import ru.kpfu.servlets.service.DBHelperInterface;
import ru.kpfu.servlets.service.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String path = "http://localhost:8080" + req.getContextPath();

        HttpSession session = req.getSession();
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println(email + " " + password);


        User user = db.getUserByEmail(ApplicationParameters.USERS, email);

        System.out.println("User: " + user);
        if (user == null) {
            System.out.println("Ошибка в логине или пароле");
            doGet(req, resp);
        }

        if (user.getPassword().equals(password)) {
            Cookie userEmail = new Cookie("userEmail", user.getEmail());
            Cookie userCookiePassword = new Cookie("userCookiePassword", user.getCookie());
            resp.addCookie(userEmail);
            resp.addCookie(userCookiePassword);

            session.setAttribute(ApplicationParameters.SESSION_USER, user);
            resp.sendRedirect(path + "/main");
            return;
        }

        System.out.println("Ошибка в логине или пароле");
        doGet(req, resp);
    }
}
