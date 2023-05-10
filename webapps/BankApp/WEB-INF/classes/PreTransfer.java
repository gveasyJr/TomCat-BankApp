import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateUser")
public class PreTransfer extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        List<Account> accounts = user.getAccounts();

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
        
        
        out.println("<body>");
        out.println("<h1>Transfer Funds Between Your Accounts</h1>");
        out.println("<form method=\"POST\" action=\"Transfer\">");
        

        out.println("<label for=\"account-source\">Source Account:</label>");
        out.println("<select id=\"account-source\" name=\"account-source\">");
        for (Account acct : accounts) {
            out.println("<option value=\"" + acct.getAccountName() + "\">" + ": $" + acct.getBalance() + " (" + acct.getAccountName() + ")</option>");
        }
        out.println("</select>");
        out.println("<br><br>");
        
        out.println("<label for=\"account-dest\">Destination Account:</label>");
        out.println("<select id=\"account-dest\" name=\"account-dest\">");
        for (Account acct : accounts) {
            out.println("<option value=\"" + acct.getAccountName() + "\">" + ": $" + acct.getBalance() + " (" + acct.getAccountName() + ")</option>");
        }
        out.println("</select>");
        out.println("<br><br>");
        
        out.println("<label for=\"amount\">Transfer Amount: </label>");
        out.println("<input type=\"number\" id=\"amount\" name=\"amount\" step=\"0.01\" min=\"0\" required>");
        out.println("<br><br>");
        
        out.println("<input type=\"submit\" value=\"Complete Transfer\">");
        out.println("</form>");
        out.println("</body>");
        
        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
