package ru.kpfu.servlets.filters;

import ru.kpfu.servlets.service.ApplicationParameters;
import ru.kpfu.servlets.service.DBHelper;
import ru.kpfu.servlets.service.DBHelperInterface;
import ru.kpfu.servlets.service.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/main")
public class VisitSiteFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String userEmail = "";
        String userCookiePassword = "";

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        if (req.getSession().getAttribute(ApplicationParameters.SESSION_USER) == null) {
            System.out.println("Сессии нет. Сейчас посмотрим куки");
            Cookie[] cookies = req.getCookies();
            String path = "http://localhost:8080" + req.getContextPath() + "/registration";
            if (cookies == null) {
                System.out.println("Куков тоже нет. Будем сейчас тебя заполнять");
                ((HttpServletResponse) servletResponse).sendRedirect(path);
                return;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userEmail")) {
                    userEmail = cookie.getValue();
                }
                if (cookie.getName().equals("userCookiePassword")) {
                    userCookiePassword = cookie.getValue();
                }
            }
            if (userEmail.length() * userCookiePassword.length() == 0) {
                System.out.print("Куки нашлись, но в них чего-то не хватает: ");
                System.out.println(userEmail + ": " + userCookiePassword);
                ((HttpServletResponse) servletResponse).sendRedirect(path);
                return;
            }

            DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
            User user = db.getUserByEmail(ApplicationParameters.USERS, userEmail);
            if (user == null) {
                System.out.println("С куками все норм, а вот с твоей бд явно нет");
                ((HttpServletResponse) servletResponse).sendRedirect(path);
                return;
            }

            if (!user.getCookie().equals(userCookiePassword)) {
                System.out.println("Куки на месте. Запись в бд есть, но они не совпадают");
                System.out.println("Куки: " + userCookiePassword + "; из бд: " + user.getCookie());
                ((HttpServletResponse) servletResponse).sendRedirect(path);
                return;
            }

            req.getSession().setAttribute(ApplicationParameters.SESSION_USER, user);
            System.out.println("Куки есть, а теперь еще и создал сессию");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
