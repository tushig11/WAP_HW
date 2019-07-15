import Model.User;
import Model.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Login")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserData userData = new UserData();
        HashMap<String, User> users = userData.getUsers();

        String postedUsername = request.getParameter("username");
        String postedPassword = request.getParameter("password");
        String rememberMe = request.getParameter("remember");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Welcome Page</title></head>");
        out.println("<body>");


        if(users.containsKey(postedUsername) && users.get(postedUsername).getPassword().equals(postedPassword)){

            HttpSession session = request.getSession();
            session.setAttribute("username", postedUsername);
            session.setMaxInactiveInterval(10*24*60*60); //second

            Cookie b = new Cookie("coupon", "$100");
            b.setMaxAge(60*60*24*30);
            response.addCookie(b);

            if(rememberMe != null){
                //Creating new cookie
                Cookie c = new Cookie("userName", postedUsername);
                c.setMaxAge(1800);
                response.addCookie(c);
            }
            else{
                //Deleting existing cookie
                Cookie c = new Cookie("userName","");
                c.setMaxAge(0);
                response.addCookie(c);
            }

            out.print("<p>Thank you! <a href='welcome'> Click here to more details </a> Click here to <a href='logout'> Log out!</a></p>");

        }else{
            request.setAttribute("message","Invalid Credential");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

        out.print("</body></html>");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("message","Login First");

        for(Cookie cookie: request.getCookies()) {
            if(cookie.getName().equals("userName")) {
                String userName = cookie.getValue();
                request.setAttribute("user", userName);
                System.out.println(userName);
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
