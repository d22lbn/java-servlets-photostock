package ru.kpfu.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet{
  private DBHelper_02 db;
  private String tableName;
  private Photo photo;


  @Override
  public void init() throws ServletException {
    super.init();
//    db = new DBHelper();
//    photo = new Photo();
//    tableName = "photos";
//    db.createTable(tableName, photo.getParam());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//    HttpSession session = req.getSession();
//    req.getServletContext().setAttribute("session", session);

//    db = (DBHelper) req.getServletContext().getAttribute("db");

    System.out.print("EMAIL :::::::::::::::::::");
    System.out.println(req.getServletContext().getAttribute("email"));

//    System.out.println(db.getUserDataByEmail("users", (String) req.getServletContext().getAttribute("email")));

//    User user = (User) session.getAttribute("user");
//    if (user == null) {
//      session.setAttribute("user", db.getUserDataByEmail("users", req.getParameter("email")));
//    } else if (!((User) session.getAttribute("user")).getEmail().equals(req.getParameter("email"))) {
//      session.setAttribute("user", db.getUserDataByEmail("users", req.getParameter("email")));
//    }



//    ArrayList<String> paths = db.getSmth(tableName, "path");
//    if (paths.size() == 0) {
//      System.out.println("ФОТОГРАФИЙ В БАЗЕ ДАННЫХ НОРМАЛЬНЫХ НЕТ, " +
//              "ПОЭТОМУ ЗАПОЛНИМ ЕЕ ФИГНЕЙ");
//      int n = 5;
//      for (int i = 0; i < n; i++) {
//        photo.setRandomData();
//        db.addData(tableName, photo.getData());
//      }
//    }
//
//    System.out.println(Bob.user);
//
//    req.setAttribute("paths", paths);

    req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
  }
}
