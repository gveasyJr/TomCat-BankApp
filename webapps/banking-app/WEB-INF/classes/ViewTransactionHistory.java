import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateAccount")
public class ViewTransactionHistory extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String      username = req.getParameter("login-username");
        HttpSession session  = req.getSession();

        if (username == null) {
            username = session.getAttribute("username").toString();
        } else {
            session.setAttribute("username", username);
        }

        PrintWriter out = resp.getWriter();
        DatabaseReader dbr = new DatabaseReader();
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

        List<Account> accounts = dbr.getUserObject(username).getAccounts();

        for (Account acc : accounts) {
            out.println("<p>" + acc.getAccountType() + " Transaction History (" + acc.getAccountId() + ")</p>");
            for (String trans : acc.getTransactionHistory()) {
                out.println("<p> " + trans + "</p>");
            }
            // out.println("<p>"+ acc.getAccountType() + ": $" + String.valueOf(acc.getBalance()) + " (" + acc.getAccountId() + ")</p>");
            out.println("<br>");
        }

        out.println("<form method=POST action=\"UserHome\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Return to User Home Page</button>");
        out.println("</form>");
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }
}
