package ru.kpfu.servlets.servlets;

        import ru.kpfu.servlets.service.User;

        import java.io.IOException;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
//    private DBHelper db;
//    private String tableName;
    private User user;
//    private boolean isFirst;

    @Override
    public void init() throws ServletException {
        super.init();
//        db = new DBHelper();
//        MainUser.user = new User();
//        tableName = "users";
//        db.createTable(tableName, Bob.user.getParam());
//        isFirst = true;




//        String tableName2 = "users_photo";
//        String tableName3 = "photos";
//
//        ArrayList<ArrayList<String>> param = new ArrayList<>();
//        ArrayList<String> list1 = new ArrayList<>();
//        ArrayList<String> list2 = new ArrayList<>();
//        list1.add("user_id"); list1.add("TEXT");
//        list2.add("photo_id"); list2.add("TEXT");
//        param.add(list1);
//        param.add(list2);
//        db.createTable(tableName2, param);
//        ArrayList<String> usersId = db.getSmth(tableName, "id");
//        ArrayList<String> photoId = db.getSmth(tableName3, "id");
//
//        System.out.println(usersId);
//        System.out.println(photoId);
//        for (String u : usersId) {
//            for (String p : photoId) {
//                ArrayList<ArrayList<String>> daata = new ArrayList<>();
//                ArrayList<String> list3 = new ArrayList<>();
//                ArrayList<String> list4 = new ArrayList<>();
//                list3.add("user_id"); list3.add(u);
//                list4.add("photo_id"); list4.add(p);
//                daata.add(list3);
//                daata.add(list4);
//                db.addData(tableName2, daata);
//            }
//        }
//        System.out.println("EEEENNNNNDDDD");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        user = (User) ((HttpSession) req.getAttribute("session")).getAttribute("user");
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
//        System.out.println(user);


        System.out.println(req.getSession().getAttribute("user"));

//        if (isFirst) {
//            getUserData();
//        }
//
//        req.setAttribute("surname", user.getSurname());
//        req.setAttribute("name", user.getName());
//        req.setAttribute("patronymic", user.getPatronymic());
//        req.setAttribute("email", user.getEmail());
//        req.setAttribute("balance", user.getBalance());

        req.getRequestDispatcher("/WEB-INF/view/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");

//        String surname = req.getParameter("surname");
//        String name = req.getParameter("name");
//        String patronymic = req.getParameter("patronymic");
//        String email = req.getParameter("email");
//
//        if (!surname.equals(Bob.user.getSurname())) {
//            if (!surname.equals("")) {
//                Bob.user.setSurname(surname);
//            }
//        }
//        if (!name.equals(Bob.user.getName())) {
//            if (!name.equals("")) {
//                Bob.user.setName(name);
//            }
//        }
//        if (!patronymic.equals(Bob.user.getPatronymic())) {
//            if (!patronymic.equals("")) {
//                Bob.user.setPatronymic(patronymic);
//            }
//        }
//        if (!email.equals(Bob.user.getEmail())) {
//            if (!email.equals("")) {
//                Bob.user.setEmail(email);
//            }
//        }
//
//        db.updateDataById(tableName, "surname", Bob.user.getSurname(), "" + Bob.user.getId());
//        db.updateDataById(tableName, "name", Bob.user.getName(), "" + Bob.user.getId());
//        db.updateDataById(tableName, "patronymic", Bob.user.getPatronymic(), "" + Bob.user.getId());
//        db.updateDataById(tableName, "email", Bob.user.getEmail(), "" + Bob.user.getId());

        doGet(req, resp);
    }

//    private void getUserData() {
//        String email = "d22lbn@gmail.com";
//        ArrayList<String> userData = db.getUserDataByEmail(tableName, email);
//        Bob.user = new User(userData);
//        isFirst = false;
//    }
}
