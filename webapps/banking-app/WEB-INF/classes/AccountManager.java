import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// @WebServlet("/CreateAccount")
public class AccountManager extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String      username = req.getParameter("login-username");
        HttpSession session  = req.getSession();

        if (username == null) {
            username = session.getAttribute("username").toString();
        } else {
            session.setAttribute("username", username);
        }

        PrintWriter out = resp.getWriter();
        // DatabaseReader dbr = new DatabaseReader();
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
        out.println("</head>");
        out.println("<center>"); 

        out.println("<body>");
        out.println("    <h1>Create a Bank Account</h1>");
        out.println("    <form  method=POST action=\"CreateAccount\">");
        out.println("        <label for=\"account-type\">Account Type:</label>");
        out.println("        <select id=\"account-type\" name=\"account-type\">");
        out.println("            <option value=\"checking\">Checking</option>");
        out.println("            <option value=\"savings\">Savings</option>");
        out.println("            <option value=\"money-market\">Money Market</option>");
        out.println("        </select>");
        out.println("        <br><br>");
        out.println("        <label for=\"starting-balance\">Initial Starting Balance:</label>");
        out.println("        <input type=\"number\" id=\"starting-balance\" name=\"starting-balance\" step=\"0.01\\\" min=\"0\" required>");
        out.println("        <br><br>");
        out.println("        <input type=\"submit\" value=\"Create Account\">");
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
