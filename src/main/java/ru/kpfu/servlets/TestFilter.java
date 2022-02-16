//package ru.kpfu.servlets;
//
//import javax.servlet.*;
//import java.io.IOException;
//
//public class TestFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        System.out.println("STARTSTARTSTARTSTART");
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        ServletContext servletContext = servletRequest.getServletContext();
//
//        servletContext.setAttribute("db", new DBHelper_02());
//        servletContext.setAttribute("email", "d22lbn@gmail.com");
//
//        if (true) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        }
//
//        System.out.println("Filter is end");
//    }
//
//    @Override
//    public void destroy() {
//        System.out.println("ENDENDENDENDEND");
//    }
//}
