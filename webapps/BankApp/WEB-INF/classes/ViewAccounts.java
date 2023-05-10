import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateAccount")
public class ViewAccounts extends HttpServlet{
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
        out.println("<center>");

        List<Account> accounts = user.getAccounts();
		double total = 0.0;
		out.println("<TABLE>");
    	out.println("<TR><TH>Account</TH><TH>Type</TH><TH>Balance</TH></TR>");
    	for(Account account : accounts) {
			out.println("<TR>");
			out.println("<TD>" + account.getAccountName() + "  " + "</TD>");
      		out.println("<TD>" + account.getType() + "  " + "</TD>");
      		out.println("<TD>" + account.getBalance() + "  " + "</TD>");
      		out.println("</TR>");
			total = total + account.getBalance();
    	}
		out.println("<h3>Total Account Balance: " + total + "</h3>");
		out.println("</TABLE>");
		out.println("<BR><BR><BR>");
	
        out.println("<form method=POST action=\"HomePage\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Return home");
        out.println("</form>");


        out.println("</center>");
        out.println("</body>");
        out.println("</html>");

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    
}