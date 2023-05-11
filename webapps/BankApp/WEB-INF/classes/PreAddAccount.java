import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/AddAccount")
public class PreAddAccount extends HttpServlet{
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("login-username");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("currentUser");
        Logger log = (Logger)session.getAttribute(user.getLogName());
        log.logAction(username + " entered the add account selection page");
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
        out.println("<h1>Account Type</h1>");
        out.println("<form method=\"POST\" action=\"AddAccount\">");
        out.println("<input type=\"radio\" id=\"checking\" name=\"account-type\" value=\"checking\" checked>");
        out.println("<label for=\"checking\">Checking</label><br>");
        out.println("<input type=\"radio\" id=\"savings\" name=\"account-type\" value=\"savings\">");
        out.println("<label for=\"savings\">Savings</label><br>");
        out.println("<input type=\"radio\" id=\"mortgages\" name=\"account-type\" value=\"mortgages\">");
        out.println("<label for=\"mortgages\">Mortgages</label><br><br>");
        out.println("<label for=\"account-name\">Input Account Name: </label>");
        out.println("<input type=\"text\" id=\"account-name\" name=\"account-name\" required><br><br>");
        out.println("<input type=\"hidden\" name=\"login-username\" value=\"" + username + "\">");
        out.println("<input type=\"submit\" value=\"Create Account\">");        
        out.println("</form>");
        out.println("</body>");

        out.println("</center>");
        out.println("</body>");
        out.println("</html>");
        session.setAttribute(user.getLogName(), log);
        uM.writeUser(user, filePath);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }
}
