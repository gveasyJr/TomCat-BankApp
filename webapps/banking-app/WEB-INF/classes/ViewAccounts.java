import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateAccount")
public class ViewAccounts extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String      username = req.getParameter("login-username");
        String      accountType = req.getParameter("account-type");
        String      initBalance = req.getParameter("starting-balance");
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

        User user = dbr.getUserObject(username);
        List<Account> accounts = dbr.getUserObject(username).getAccounts();

        double sum = 0;
        for (Account acc : accounts) {
            sum += acc.getBalance();
            out.println("<p>"+ acc.getAccountType() + ": $" + String.valueOf(acc.getBalance()) + " (" + acc.getAccountId() + ")</p>");
            out.println("<br>");
        }
        out.println("<p>All Account Total Balance: $" + sum + "</p>");

        out.println("<form method=POST action=\"UserHome\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Back to Home Page");
        out.println("</form>");

        // out.println("<h1>Successfully created a " + accountType + " account with " + convertInitBalance.toString() + "</h1>");

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }
    
}
