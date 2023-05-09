import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    List<User> userList;
    private boolean initCalled = false;

    public void init() throws ServletException {
        super.init();
        userList = new ArrayList<User>();
        userList.add(new User("adam1"));
        userList.add(new User("guy3"));
    }

    private User createUser(String username) {
        User newUser = new User(username);
        Checking acct1 = new Checking (100.0, "Default Checkings Account");
        Saving acct2 = new Saving (100.0, "Default Savings Account");
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
        boolean userExists = false;

        if (!initCalled) {
            super.init();
            initCalled = true;
        }

        for(User user : userList){
            if (user.getUsername().equals(username)){
                userExists = true;
                break;
            }
        }

        session.setAttribute("username", username);

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
        
        if (!userExists) {
            User newUser = this.createUser(username);
            userList.add(newUser);
            session.setAttribute("user", newUser);
            out.println("<h1>Sucessfully created user</h1>");
            out.println("<h1>Welcome " + username + "</h1>");
            out.println("<form method=POST action=\"UserHome\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Go to Home Page</button>");
            out.println("</form>");
        } else {
            out.println("<h1>Failed to create user</h1>");
            out.println("<h1>The username: " + username + "is not valid"+ "</h1>");
            out.println("<h1>Try again</h1>");
            out.println("<form method=POST action=\"AddUser\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Return to Add User</button>");
            out.println("</form>");
        }


        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

}
