import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {

    private void createUser(String username) {
        User newUser = new User(username);
        DatabaseWriter.writeUserToDatabase(newUser);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        String username = req.getParameter("signup-username");

        DatabaseReader dbr = new DatabaseReader();
        boolean userExists = dbr.isUserExist(username);

        session.setAttribute("username", username);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

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
        
        // user does not already exist, create one
        if (!userExists) {

            this.createUser(username);

            out.println("<h1>Sucessfully created account</h1>");
            out.println("<h1>Welcome " + username + "</h1>");
            out.println("<form method=POST action=\"UserHome\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Return to User Home Page</button>");
            out.println("</form>");
        } else {

        }


        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

}
