//package ru.kpfu.servlets;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/registration")
//public class RegistrationServlet extends HttpServlet {
//
//    private DBHelper_02 db;
//    private String tableName;
//    private User user;
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        db = new DBHelper_02();
//        user = new User();
//        tableName = "users";
//        db.createTable(tableName, user.getParameters());
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
//
////        String tableName2 = "photos";
////        Photo photo = new Photo();
////        db.createTable(tableName2, photo.getParam());
////        for (int i = 1; i < 8; i++) {
////            photo.setName("item" + i + ".jpg");
////            db.addData(tableName2, photo.getData());
////        }
//
////        System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
////        ArrayList<String> list = db.getSmth(tableName2, "path");
////        for (String i : list) {
////            System.out.println(i);
////        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF8");
//
//        user.setEmail(req.getParameter("email"));
//        user.setName(req.getParameter("name"));
//        user.setSurname(req.getParameter("surname"));
//        user.setPassword(req.getParameter("password"));
//        db.addData(tableName, user.getData());
//
//        doGet(req, resp);
//    }
//}
