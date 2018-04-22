package next.web;

import core.db.DataBase;
import next.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forward("/user/login.jsp", request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = DataBase.findUserById(userId);

        if(user == null) {
            request.setAttribute("loginFailed", true);
            forward("/user/login.jsp", request, response);
            return;
        }

        if(user.matchPassword(password)){
            HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtil.USER_SESSION_KEY, user);
            response.sendRedirect("/");
        } else {
            request.setAttribute("loginFailed", true);
            forward("/user/login.jsp", request, response);
        }
    }

    private static void forward(String forwardUrl, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(forwardUrl);
        rd.forward(req, resp);
    }
}
