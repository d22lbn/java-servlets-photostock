package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");
        String path = "http://localhost:8080" + req.getContextPath();
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
        HttpSession session = req.getSession();


        String email = req.getParameter("email");

        User user1 = db.getUserByEmail(ApplicationParameters.USERS, email);
        if (user1 != null) {
            req.setAttribute("err", "Пользователь с такой почтой уже существует");
            doGet(req, resp);
            return;
        }

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        if (password.length() < 6) {
            req.setAttribute("err", "Пароль должен быть не менее 6 символов");
            doGet(req, resp);
            return;
        }
        String userCookie = getRandStr();

        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setPassword(password);
        user.setCookie(userCookie);
        user.setBalance(1000);

        db.addEntry(ApplicationParameters.USERS, user.getData());
        user = db.getUserByEmail(ApplicationParameters.USERS, email);

        Cookie userEmail = new Cookie("userEmail", user.getEmail());
        Cookie userCookiePassword = new Cookie("userCookiePassword", user.getCookie());
        resp.addCookie(userEmail);
        resp.addCookie(userCookiePassword);

        session.setAttribute(ApplicationParameters.SESSION_USER, user);
        resp.sendRedirect(path + "/main");
        return;
    }

    private String getRandStr() {
        int n = 10;
        String alp = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < n; i++) {
            s.append(alp.charAt((int) (Math.random() * alp.length())));
        }
        return s.toString();
    }
}
