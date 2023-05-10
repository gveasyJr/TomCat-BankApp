import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ViewLog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        User user = (User) session.getAttribute("currentUser");
        Logger log = (Logger) session.getAttribute(user.getLogName());
        log.logAction(user.getUsername() + " entered the view log page");

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

        out.println("<h1>Transaction History</h1>");

        List<String> actions = log.getActions();
        List<LocalDateTime> timestamps = log.getTimestamps();

        if (actions.size() == 0) {
            out.println("<p>No actions recorded.</p>");
        } else {
            out.println("<table>");
            out.println("<tr><th>Action</th><th>Timestamp</th></tr>");
            for (int i = 0; i < actions.size(); i++) {
                String action = actions.get(i);
                LocalDateTime timestamp = timestamps.get(i);
                out.println("<tr><td>" + action + "</td><td>" + timestamp + "</td></tr>");
            }
            out.println("</table>");
        }

        out.println("<form method=\"POST\" action=\"HomePage\">");
        out.println("<button name=\"login-username\" value=\"" + user.getUsername() + "\">Return Home</button>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
        session.setAttribute(user.getLogName(), log);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}