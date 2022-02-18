package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.ApplicationParameters;
import ru.kpfu.servlets.service.DBHelper;
import ru.kpfu.servlets.service.DBHelperInterface;
import ru.kpfu.servlets.service.User;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);

        String surname = user.getSurname();
        String name = user.getName();
        String email = user.getEmail();
        String balance = String.valueOf(user.getBalance());

        req.setAttribute("surname", surname);
        req.setAttribute("name", name);
        req.setAttribute("email", email);
        req.setAttribute("balance", balance);

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
