import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Transfer extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        String sourceAccountName = request.getParameter("account-source");
        String destinationAccountName = request.getParameter("account-dest");
        String transferAmountStr = request.getParameter("amount");

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
        
        
        try {
            double transferAmount = Double.parseDouble(transferAmountStr);
            Account sourceAccount = user.getAccount(sourceAccountName);
            Account destinationAccount = user.getAccount(destinationAccountName);
            
            if (sourceAccount == null || destinationAccount == null) {
                out.println("<h1>Failed to complete transfer: Invalid account(s)</h1>");
                out.println("<form method=\"POST\" action=\"HomePage\">");
                out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
                out.println("</form>");
            } else if (transferAmount > sourceAccount.getBalance()) {
                out.println("<h1>Failed to complete transfer: Insufficient funds in the source account</h1>");
                out.println("<form method=\"POST\" action=\"HomePage\">");
                out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
                out.println("</form>");
            } else {
                Account.doTransaction(sourceAccount, destinationAccount, transferAmount);
                session.setAttribute("currentUser", user);
                out.println("<h1>Transfer of $" + transferAmount + " from " + sourceAccountName + " to " + destinationAccountName + " was successful</h1>");
                out.println("<form method=\"POST\" action=\"HomePage\">");
                out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
                out.println("</form>");
            }
        } catch (NumberFormatException e) {
            out.println("<h1>Failed to complete transfer: Invalid amount</h1>");
            out.println("<form method=\"POST\" action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
            out.println("</form>");
        } catch (NoSuchElementException e) {
            out.println("<h1>Failed to complete transfer: Invalid account(s)</h1>");
            out.println("<form method=\"POST\" action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
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
