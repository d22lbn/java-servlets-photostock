//package ru.kpfu.servlets.filters;
//
//import ru.kpfu.servlets.service.*;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/main")
//public class MainFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        HttpSession session = req.getSession();
//        Cookie[] cookies = req.getCookies();
//        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
//        User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);
//
//        user = AuthorizationVerification.rememberUser(user, cookies, db);
//        if (user != null) {
//            String s = user.getName() + " " + user.getSurname().charAt(0) + ".";
//            servletRequest.setAttribute("userName", s);
//        }
//
//
//        ((HttpServletRequest) servletRequest).getSession().setAttribute(ApplicationParameters.USERS, user);
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
