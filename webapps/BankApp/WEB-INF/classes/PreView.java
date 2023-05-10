import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
//import javax.servlet.*;
import javax.servlet.http.*;

public class PreView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session  = request.getSession();
        PrintWriter out = response.getWriter();
        // Retrieve the User object from the session
        User user = (User)session.getAttribute("currentUser");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Visibility:Visible\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Left:50\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Width:30\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Height:30\">");
        out.println("<meta HTTP-EQUIV='Cache-Control' CONTENT='no-cache'>");
        out.println("<meta HTTP-EQUIV='Pragma' CONTENT='no-cache'>");
        out.println("<meta HTTP-EQUIV='Expires' CONTENT='0'>");
        out.println("<title>Transaction History</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>Transaction History for User: " + user.getUsername() + "</h1>");

        List<Account> accounts = user.getAccounts();

        if (accounts.isEmpty()) {
            out.println("<p>No accounts available</p>");
        } else {
            for (Account account : accounts) {
                String accountName = account.getAccountName();
                List<History> transactions = account.getTransactions();

                if (transactions.isEmpty()) {
                    out.println("<h2>Account: " + accountName + "</h2>");
                    out.println("<p>No transactions available</p>");
                } else {
                    out.println("<h2>Account: " + accountName + "</h2>");
                    out.println("<ul>");
                    for (History transaction : transactions) {
                        out.println("<li>" + transaction.printHistory() + "</li>");
                    }
                    out.println("</ul>");
                }
            }
        }

        out.println("<form method=\"POST\" action=\"HomePage\">");
        out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}