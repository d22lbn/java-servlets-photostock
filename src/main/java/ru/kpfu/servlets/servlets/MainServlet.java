package ru.kpfu.servlets.servlets;

import ru.kpfu.servlets.service.*;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/main")
public class MainServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
    Cookie[] cookies = req.getCookies();
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);
    user = AuthorizationVerification.rememberUser(user, cookies, db);
    if (user != null) {
      String s = user.getName() + " " + user.getSurname().charAt(0) + ".";
      req.setAttribute("userName", s);
    }
    session.setAttribute(ApplicationParameters.SESSION_USER, user);
    ArrayList<String> paths = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "path");
    req.setAttribute("paths", paths);
    ArrayList<String> names = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "name");
    req.setAttribute("names", names);
    ArrayList<String> descriptions = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "description");
    req.setAttribute("descriptions", descriptions);
    ArrayList<String> prices = (ArrayList<String>) db.getColumnEntries(ApplicationParameters.PHOTOS, "price");
    req.setAttribute("prices", prices);


    req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF8");
    DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute(ApplicationParameters.DB);
    HttpSession session = req.getSession();
    User user = (User) session.getAttribute(ApplicationParameters.SESSION_USER);

    String priceTxt = req.getParameter("price");

    int price = Integer.parseInt(priceTxt.substring(0, priceTxt.length() - 5));
    System.out.println("PRICE: " + price);

    String imgPath = req.getParameter("imgPath");
    System.out.println("PATH: " + imgPath);

    int id = Integer.parseInt(db.getPhotoIdByPath(ApplicationParameters.PHOTOS, imgPath));
    System.out.println("ID: " + id);

    Photo photo = db.getPhotoById(ApplicationParameters.PHOTOS, id);
    System.out.println("PHOTO: " + photo);

    System.out.println("Осталось: " + (photo.getCanBuy() - photo.getPurchasedCount()));

    if (photo.getCanBuy() - photo.getPurchasedCount() == 0) {
      System.out.println("Фотография распродана");
      doGet(req, resp);
      return;
    }


    System.out.println("ID НЫНЕШНЕГО ФОТО: " + id);
    ArrayList<String> usersPhoto = db.getPhotoIdByUserId(ApplicationParameters.PURCHASED, user.getId());
    if (usersPhoto != null && usersPhoto.size() > 0) {
      for (String p : usersPhoto) {
        if (Integer.parseInt(p) == id) {
          doGet(req, resp);
          return;
        }
      }
    }

    ArrayList<String> usersPhoto2 = db.getPhotoIdByUserId(ApplicationParameters.UPLOADED, user.getId());
    if (usersPhoto2 != null && usersPhoto2.size() > 0) {
      for (String p : usersPhoto2) {
        if (Integer.parseInt(p) == id) {
          doGet(req, resp);
          return;
        }
      }
    }

    ArrayList<String> sellers = db.getUserIdByPhotoId(ApplicationParameters.UPLOADED, photo.getId());
    if (sellers == null || sellers.size() == 0) {
      doGet(req, resp);
      return;
    }
    String seller = sellers.get(0);
    if (seller == null || seller.length() == 0) {
      System.out.println("Продавца такого не существует");
      doGet(req, resp);
      return;
    }

    System.out.println(user);
    System.out.println("Останется: " + (user.getBalance() - price));
    if (price <= user.getBalance()) {
      user.setBalance(user.getBalance() - price);
      System.out.println(user);
      session.setAttribute(ApplicationParameters.SESSION_USER, user);
      db.updateUserFieldById(ApplicationParameters.USERS, user.getId(), User.getCoup("balance", String.valueOf(user.getBalance())));

      ArrayList<ArrayList<String>> entryPurch = new ArrayList<>();


      entryPurch.add(User.getCoup("userId", String.valueOf(user.getId())));
      entryPurch.add(User.getCoup("photoId", String.valueOf(photo.getId())));
      db.addEntry(ApplicationParameters.PURCHASED, entryPurch);

      photo.setPurchasedCount(photo.getPurchasedCount() + 1);
      db.updateUserFieldById(ApplicationParameters.PHOTOS, photo.getId(),
              User.getCoup("purchasedCount", String.valueOf(photo.getPurchasedCount())));

      User seller1 = db.getUserById(ApplicationParameters.USERS, Integer.parseInt(seller));
      seller1.setBalance(seller1.getBalance() + price);
      db.updateUserFieldById(ApplicationParameters.USERS, seller1.getId(), User.getCoup("balance", String.valueOf(seller1.getBalance())));

    } else {
      System.out.println("Не хватает денег!");
      doGet(req, resp);
      return;
    }

    doGet(req, resp);
  }
}
