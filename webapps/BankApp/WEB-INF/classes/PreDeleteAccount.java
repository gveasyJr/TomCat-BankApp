import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateAccount")
public class PreDeleteAccount extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("login-username");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (username == null) {
            username = session.getAttribute("username").toString();
        } else {
            session.setAttribute("username", username);
        }

        PrintWriter out = response.getWriter();
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
        out.println("<body>");
        out.println("<center>");

        List<Account> accounts = user.getAccounts();
	
		out.println("<TABLE>");
    	out.println("<TR><TH>Account</TH><TH>Type</TH><TH>Balance</TH></TR>");
    	for(Account account : accounts) {
            //if(account.getBalance() == 0.0){
			out.println("<TR>");
			out.println("<TD>" + account.getAccountName() + "  " + "</TD>");
      		out.println("<TD>" + account.getType() + "  " + "</TD>");
      		out.println("<TD>" + account.getBalance() + "  " + "</TD>");
      		out.println("</TR>");
            //}
    	}
        out.println("<h3>Only Accounts with Balances of 0.0 will appear</h3>");
		out.println("</TABLE>");
		out.println("<BR><BR><BR>");

        // add form for deleting an account
        out.println("<form method=POST action=\"DeleteAccount\">");
        out.println("<label for=\"account-name\">Give Account to Delete:</label>");
        out.println("<input type=\"text\" id=\"account-name\" name=\"account-name\">");
        out.println("<br>");
        //out.println("<input type=\"hidden\" name=\"login-username\" value=\"" + username + "\">");
        out.println("<input type=\"submit\" value=\"Delete Account\">");
        out.println("</form>");

	
        /*out.println("<form method=POST action=\"DeleteAccount\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Return home");
        out.println("</form>");*/


        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    
}