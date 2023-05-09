import java.io.*;
import java.util.*;
// import java.util.logging.LogManager;
import java.util.logging.LogManager;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;
// import org.apache.logging.log4j.Logger;

public class UserHome extends HttpServlet {
    private Logger logger = Logger.getLogger("UserHome"); 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        // super.doPost(req, resp);
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

        logger.info("checking if " + username + " exists: " + dbr.isUserExist(username));

        // check if user login exists
        if (!dbr.isUserExist(username)) {
            this.loadInvalidUserHTML(out);
        } else {
            this.loadUserHomeHTML(out, username);
        }

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

    private void loadUserHomeHTML(PrintWriter out, String username) {
        out.println("<h1>Anderson Banking Home Page</h1>");
        out.println("<h1>Hello " + username + "</h1>");
        out.println("<form method=POST action=\"ViewAccounts\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">View Account Balances");
        out.println("</form>");

        out.println("<form method=POST action=\"AccountManager\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Create an Account");
        out.println("</form>");

        out.println("<form method=POST action=\"TransferFundsManager\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Transfer Funds to Another Account");
        out.println("</form>");

        out.println("<form method=POST action=\"ViewAccountsHistory\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">View Accounts History");
        out.println("</form>");
    }

    private void loadInvalidUserHTML(PrintWriter out) {
        out.println("<h1>Username does not exist.</h1>");
        out.println("<h1>Please create an Anderson Banking user to continue</h1>");
        out.println("<form method=POST action=\"UserHome\">");
        out.println("<a href=\"signup.html\"><input type=\"button\" value=\"Sign up for an Account\"></a>");
        out.println("</form>");
    }


}