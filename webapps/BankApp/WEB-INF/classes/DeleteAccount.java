import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.NoSuchElementException;

// @WebServlet("/AddAcount")
public class DeleteAccount extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session  = request.getSession();
        String username = request.getParameter("login-username");
        String acctName = request.getParameter("account-name");
        User user = (User)session.getAttribute("currentUser");
        Logger log = (Logger)session.getAttribute(user.getLogName());
        log.logAction(user.getUsername() + " attempting to delete account...");

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
        out.println("<body>");

        try {
            Account accountToDelete = user.getAccount(acctName);
            if (accountToDelete.getBalance() != 0.0) {
                log.logAction(user.getUsername() + " failed to delete an account");
                out.println("<h1>Failed to delete account: " + accountToDelete.getAccountName() + "</h1>");
                out.println("<form method=POST action=\"HomePage\">");
                out.println("<button name=\"login-username\" value=\"" + username + "\">Return Home</button>");
                out.println("</form>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            } else {
                user.deleteAccount(accountToDelete);
                session.setAttribute("user", user);
                log.logAction(user.getUsername() + " succeeded in the attempt to delete an account");
                out.println("<h1>" + accountToDelete.getAccountName() + " account was successfully deleted</h1>");
                out.println("<form method=POST action=\"HomePage\">");
                out.println("<button name=\"login-username\" value=\"" + username + "\">Return Home</button>");
                out.println("</form>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (NoSuchElementException e) {
            out.println("<h1>Failed to delete account: " + acctName + " was not found</h1>");
            log.logAction(user.getUsername() + " failed to delete an account");
            out.println("<form method=POST action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Return Home</button>");
            out.println("</form>");
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            session.setAttribute(user.getLogName(), log);
        }
}

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    
}
