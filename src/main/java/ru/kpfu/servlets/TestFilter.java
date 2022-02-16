package ru.kpfu.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/main")
public class TestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            String path = ((HttpServletRequest) servletRequest).getServletPath()
            System.out.println(path);
            ((HttpServletResponse) servletResponse).sendRedirect(path + "/main");
        }
        String sessionId = "";
        for (Cookie cookie : cookies) {
            System.out.println(cookie.getName() + ": " + cookie.getValue());
            if (cookie.getName().equals("sessionId")) {
                sessionId = cookie.getValue();
                break;
            }
        }
        if (!sessionId.equals("")) {
            HttpSession session = req.getSession();
            System.out.println(session.getId());
            if (session.getId().equals(sessionId)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                String path = ((HttpServletRequest) servletRequest).getContextPath();
                System.out.println(path);
                ((HttpServletResponse) servletResponse).sendRedirect(path + "/main");
            }
        } else {
            String path = ((HttpServletRequest) servletRequest).getContextPath();
            System.out.println(path);
            ((HttpServletResponse) servletResponse).sendRedirect(path + "/main");
        }
    }

}
