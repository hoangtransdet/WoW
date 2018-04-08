package Website;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Min on 4/16/2016.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet" })
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID;
        String password;

        userID = request.getParameter("userID");
        password = request.getParameter("password");

        LoginService loginService = new LoginService();
        boolean result = loginService.authenticate(userID, password);
        if (result) {
            User user = loginService.getUserDetails(userID);
            request.setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("success.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            response.sendRedirect("login.jsp");
        }
    }

}
