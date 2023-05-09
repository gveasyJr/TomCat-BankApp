import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateUser")
public class TransferFundsManager extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();

        String username = req.getParameter("login-username");

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
        
        User user = dbr.getUserObject(username);
        List<Account> accounts = dbr.getUserObject(username).getAccounts();
        
        out.println("<body>");
        out.println("    <h1>Transfer Funds to Another Account</h1>");
        out.println("    <form  method=POST action=\"TransferFunds\">");
       
        // source account drop down selector
        out.println("        <label for=\"account-source\">Source Account:</label>");
        out.println("        <select id=\"account-source\" name=\"account-source\">");
        for (Account acc : accounts) {
            out.println("<option value =\"" + acc.getAccountId() + "\">" + acc.getAccountType() + ": $" + acc.getBalance() + " (" + acc.getAccountId() + ")</option>");
        }
        out.println("        </select>");
        out.println("        <br><br>");
        // destination accoutn drop down selector
        out.println("        <label for=\"account-dest\">Destination Account:</label>");
        out.println("        <select id=\"account-dest\" name=\"account-dest\">");
        for (Account acc : accounts) {
            out.println("<option value =\"" + acc.getAccountId() + "\">" + acc.getAccountType() + ": $" + acc.getBalance() + " (" + acc.getAccountId() + ")</option>");
        }
        out.println("        </select>");
        out.println("        <br><br>");

        out.println("        <label for=\"amount\">Transfer Amount: </label>");
        out.println("        <input type=\"number\" id=\"amount\" name=\"amount\" step=\"0.01\\\" min=\"0\" required>");
        out.println("        <br><br>");
        out.println("        <input type=\"submit\" value=\"Complete Transfer\">");
        out.println("    </form>");
        out.println("</body>");

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

}
