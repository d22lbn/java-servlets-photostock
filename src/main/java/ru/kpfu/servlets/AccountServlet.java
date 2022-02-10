package ru.kpfu.servlets;

        import java.io.IOException;
        import java.util.ArrayList;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.HttpServlet;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private DBHelper db;
    private String tableName;
    private User user;

    @Override
    public void init() throws ServletException {
        super.init();
        db = new DBHelper();
        user = new User();
        tableName = "users";
        db.createTable(tableName, user.getParam());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getUserData();


        req.setAttribute("surname", user.getSurname());
        req.setAttribute("name", user.getName());
        req.setAttribute("patronymic", user.getPatronymic());
        req.setAttribute("email", user.getEmail());
        req.setAttribute("balance", user.getBalance());


        req.getRequestDispatcher("/WEB-INF/view/account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF8");


        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String patronymic = req.getParameter("patronymic");
        String email = req.getParameter("email");

        user.setSurname(surname);
        user.setName(name);
        user.setPatronymic(patronymic);
        user.setEmail(email);

//        db.addData(tableName, user.getData());
//        db.updateDataById(tableName, "surname", user.getSurname(), "" + user.getId());
//        db.updateDataById(tableName, "name", user.getName(), "" + user.getId());
//        db.updateDataById(tableName, "patronymic", user.getPatronymic(), "" + user.getId());
        db.updateDataById(tableName, "email", user.getEmail(), "" + user.getId());

//        req.setAttribute("surname", surname);
//        req.setAttribute("name", name);
//        req.setAttribute("patronymic", patronymic);
//        req.setAttribute("email", email);

//        System.out.println(req.getParameter("surname"));
//        System.out.println(req.getParameter("name"));
//        System.out.println(req.getParameter("patronymic"));
//        System.out.println(req.getParameter("email"));



        doGet(req, resp);
    }

    private User getUserData() {
        String email = "d22lbn@gmail.com";
        ArrayList<String> userData = db.getUserDataByEmail(tableName, email);
        user = new User(userData);
        return user;
    }
}
