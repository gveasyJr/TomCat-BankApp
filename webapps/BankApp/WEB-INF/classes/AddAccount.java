import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/AddAcount")
public class AddAccount extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session  = request.getSession();
        String username = request.getParameter("login-username");
        String type = request.getParameter("account-type");
        String acctName = request.getParameter("account-name");
        User user = (User)session.getAttribute("currentUser");
        Logger log = (Logger)session.getAttribute(user.getLogName());
        log.logAction(user.getUsername() + " attempting add account...");
        ServletContext context = getServletContext();
        String filePath = context.getRealPath("/users.dat");
        UserManager uM = new UserManager();

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
        out.println("<meta http-equiv=\"Pragma\" content=\"no-cache\">");
        out.println("<meta http-equiv=\"Cache-control\" content=\"no-cache\">");
        out.println("<meta http-equiv=\"Expires\" content=\"0\">");
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
            if (type.equals("savings")) {
                Account acct = new Saving(0.0, acctName);
                user.addAccount(acct);
            } else if (type.equals("checking")) {
                Account acct = new Checking(0.0, acctName);
                user.addAccount(acct);
            } else if (type.equals("mortgages")) {
                Account acct = new Mortgage(0.0, acctName);
                user.addAccount(acct);
            }

        
            session.setAttribute("currentUser", user);
            log.logAction(user.getUsername() + " succeeded in the attempt to create an account");
        
            out.println("<h1>" + type + " account named '" + acctName + "' was successfully created " + "</h1>");
        
            out.println("<form method=POST action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Return Home</button>");
            out.println("</form>");
        
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } catch (IllegalArgumentException e) {
            log.logAction(user.getUsername() + " failed to delete an account");
            out.println("<h1>Failed to add the account - an account with the same name already exists</h1>");
        
            out.println("<form method=POST action=\"HomePage\">");
            out.println("<button name=\"login-username\" value=\"" + username + "\">Return Home</button>");
            out.println("</form>");
        
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
            session.setAttribute(user.getLogName(), log);
        }
        uM.writeUser(user, filePath);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
    
}
