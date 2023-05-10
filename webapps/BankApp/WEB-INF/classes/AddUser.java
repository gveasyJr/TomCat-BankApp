import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    private boolean initCalled = false;

    public HashMap<String, User> initUsers() throws ServletException {
		User user1 = new User("adam1");
		Checking acct1 = new Checking (200.0, "Adam's Checkings Account");
        Saving acct2 = new Saving (1200.0, "Adam's Savings Account");
        Mortgage acct3 = new Mortgage(300.0, "Adam's Mortgages Account");
        user1.addAccount(acct1);
        user1.addAccount(acct2);
        user1.addAccount(acct3);


		User user2 = new User("guy3");
		Checking acct4 = new Checking (10.0, "Guy's Checkings Account");
        Saving acct5 = new Saving (900.0, "Guy's Savings Account");
        Mortgage acct6 = new Mortgage(4000.0, "Guy's Mortgages Account");
        user2.addAccount(acct4);
        user2.addAccount(acct5);
        user2.addAccount(acct6);

		HashMap<String, User> users = new HashMap<>();
		users.put(user1.getUsername(), user1);
		users.put(user2.getUsername(), user2);

        return users;
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
        @SuppressWarnings("unchecked")
        HashMap<String, User> addedUsers = (HashMap<String, User>)session.getAttribute("addedUsers");

        HashMap<String, User> users = null;

        boolean userExists = false;

        if (!initCalled || addedUsers == null) {
            users = initUsers();
            session.setAttribute("addedUsers", users);
            initCalled = true;
        } else {
            users = addedUsers;
        }

        if(addedUsers != null){
            if(addedUsers.containsKey(username)){
                userExists = true;
            }
        }

        else if (users.containsKey(username)) {
            userExists = true;
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
        
        if (!userExists) {
            User newUser = this.createUser(username);
            users.put(newUser.getUsername(), newUser);
            session.setAttribute("username", username);
            session.setAttribute("addedUsers", users);
            session.setAttribute("user", newUser);

            out.println("<h1>Sucessfully created user</h1>");
            out.println("<h1>Welcome " + username + "</h1>");
            out.println("<form method=POST action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Go to Home Page</button>");
            out.println("</form>");
        } else {
            out.println("<h1>Failed to create user</h1>");
            out.println("<h1>The username: " + username + " is not valid"+ "</h1>");
            out.println("<h1>Try again</h1>");
            out.println("<form method=POST action=\"index.html\">");
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
