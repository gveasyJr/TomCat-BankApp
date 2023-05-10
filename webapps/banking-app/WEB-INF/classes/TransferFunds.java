import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.logging.*;

// @WebServlet("/CreateUser")
public class TransferFunds extends HttpServlet {

    private Logger logger = Logger.getLogger("TransferFunds");

    PrintWriter out;
    String username, srcAccountId, destAccountId;
    Account src, dest;
    Double convertAmount;
    User user;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        out = resp.getWriter();
        HttpSession session = req.getSession();

        username = req.getParameter("login-username");
        srcAccountId = req.getParameter("account-source");
        destAccountId = req.getParameter("account-dest");
        String amount = req.getParameter("amount");

        if (username == null) {
            username = session.getAttribute("username").toString();
        } else {
            session.setAttribute("username", username);
        }

        DatabaseReader dbr = new DatabaseReader();
        user = dbr.getUserObject(username);
        List<Account> accounts = dbr.getUserObject(username).getAccounts();

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
        
        src = null;
        dest = null;

        convertAmount = Double.parseDouble(amount);

        for (Account acc : accounts) {
            if (acc.getAccountId().equals(srcAccountId)) {
                src = acc;
            } else if (acc.getAccountId().equals(destAccountId)) {
                dest = acc;
            }
        }

        if (convertAmount <= src.getBalance()) {
            validTransferHTML();
        } else {
            invalidTransferHTML();
        }
        out.println("<form method=POST action=\"UserHome\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">Back to Home Page");
        out.println("</form>");

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
    }

    private void validTransferHTML() {
        out.println("<h1>Transfer Success</h1>");
        src.withdraw(convertAmount);
        dest.deposit(convertAmount);

        logger.info(username + " transfered " + String.valueOf(convertAmount) + " from " + src.getAccountType() + " (" + src.getAccountId() + ") to "  + dest.getAccountType() + " (" + dest.getAccountId() + ")");

        DatabaseWriter.rewriteModifiedUser(user);
        DatabaseWriter.rewriteModifiedAccount(src);
        DatabaseWriter.rewriteModifiedAccount(dest);
    }

    private void invalidTransferHTML() {
        out.println("<h1>Transfer Error</h1>");
        out.println("<h2>Source account has insufficent funds to transfer.</h2>");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        doPost(req, resp);
    }

}
