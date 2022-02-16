package ru.kpfu.servlets.servlets;

        import java.io.IOException;
        import javax.servlet.ServletException;
        import javax.servlet.annotation.WebServlet;
        import javax.servlet.http.*;

@WebServlet("/recommendation")
public class RecommendationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        HttpSession session = req.getSession();
//        System.out.println("id: " + session.getId());
//        Cookie cookie = new Cookie("sessionId", session.getId());
//        resp.addCookie(cookie);
//        System.out.println("Добавили новую куку. Теперь можешь заходить спокойно, старина");


//        DBHelperInterface db = (DBHelper) req.getServletContext().getAttribute("db");
//
//        User u = db.getUserByEmail(ApplicationParameters.USERS, "d22lbm@gmail.com");
//        System.out.println(u);

        req.getRequestDispatcher("/WEB-INF/view/recommendation.jsp").forward(req, resp);
    }
}
