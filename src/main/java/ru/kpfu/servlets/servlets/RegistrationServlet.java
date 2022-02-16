package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.ApplicationParameters;
import ru.kpfu.servlets.service.DBHelper;
import ru.kpfu.servlets.service.DBHelperInterface;
import ru.kpfu.servlets.service.User;

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

        HttpSession session = req.getSession();
        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);

        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);
        if (user == null) {
            System.out.println("Пользователя нет в сессии. А может и самой сессии. Сейчас всё добавим");
            user = new User();
            System.out.println("Новый пользователь готов");


            String email = req.getParameter("email");
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            String password = req.getParameter("password");
            String userCookie = getRandStr(); // вынести фунцию генерации в отдельный класс и сделать ее нормальной

            System.out.println("Из полей получили данные и сгенерировали ключ куки");

            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            user.setPassword(password);
            user.setCookie(userCookie);
            System.out.println("Заполнили нового юзера инфой");

            if (db.addEntry(ApplicationParameters.USERS, user.getData())) {
                System.out.println("Записали юзера в бд");
            } else {
                System.out.println("Хоть куков и сессии нет, но пользователь с такой почтой есть. " +
                        "Поэтому просто добавлю старые куки и сессию сделаю");
            }

            user = db.getUserByEmail(ApplicationParameters.USERS, email);
            System.out.println("Получили экземпляр юзера из бд для синхронизации");

            Cookie userEmail = new Cookie("userEmail", user.getEmail());
            Cookie userCookiePassword = new Cookie("userCookiePassword", user.getCookie());
            resp.addCookie(userEmail);
            resp.addCookie(userCookiePassword);
            System.out.println("Добавили куки для пользователя");

            session.setAttribute(ApplicationParameters.SESSION_USER, user);
            System.out.println("Добавили пользователя в сессию");
        } else {
            System.out.println("Пользователь есть в сессии. Сейчас просто перенаправим");
        }
        doGet(req, resp);
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
