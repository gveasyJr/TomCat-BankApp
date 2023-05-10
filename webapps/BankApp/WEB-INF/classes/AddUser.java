import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    private User createUser(String username) {
        User newUser = new User(username);
        Checking acct1 = new Checking(100.0, "Default Checkings Account");
        Saving acct2 = new Saving(100.0, "Default Savings Account");
        Mortgage acct3 = new Mortgage(100.0, "Default Mortgages Account");
        newUser.addAccount(acct1);
        newUser.addAccount(acct2);
        newUser.addAccount(acct3);
        return newUser;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String username = request.getParameter("signup-username");

        if(session.getAttribute("adam1") == null || session.getAttribute("guy3") == null){
            User user1 = new User("adam1");
            Checking acct1 = new Checking(200.0, "Adam's Checkings Account");
            Saving acct2 = new Saving(1200.0, "Adam's Savings Account");
            Mortgage acct3 = new Mortgage(300.0, "Adam's Mortgages Account");
            user1.addAccount(acct1);
            user1.addAccount(acct2);
            user1.addAccount(acct3);
            session.setAttribute("adam1", user1);
    
            User user2 = new User("guy3");
            Checking acct4 = new Checking(10.0, "Guy's Checkings Account");
            Saving acct5 = new Saving(900.0, "Guy's Savings Account");
            Mortgage acct6 = new Mortgage(4000.0, "Guy's Mortgages Account");
            user2.addAccount(acct4);
            user2.addAccount(acct5);
            user2.addAccount(acct6);
            session.setAttribute("guy3", user2);
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Visibility:Visible\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Left:50\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Width:30\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Height:30\">");
        out.println("<meta HTTP-EQUIV='Cache-Control' CONTENT='no-cache'>");
        out.println("<meta HTTP-EQUIV='Pragma' CONTENT='no-cache'>");
        out.println("<meta HTTP-EQUIV='Expires' CONTENT='0'>");
        out.println("</head>");
        out.println("<center>");

        if(session.getAttribute(username) != null){
            out.println("<h1>User already exists!</h1>");
            out.println("<p>Please choose a different username.</p>");
            out.println("<form method=POST action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Go to Home Page</button>");
        }

       else{
        User newUser = createUser(username);
        session.setAttribute(username, newUser);
        out.println("<h1>User added successfully!</h1>");
        out.println("<p>Welcome, " + username + "!</p>");
        out.println("<form method=POST action=\"HomePage\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Go to Home Page</button>");

        out.println("</center>");
        out.println("</body></html>");
        out.close();
        }
    }
}
        
