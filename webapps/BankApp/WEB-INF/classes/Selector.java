import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Selector extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException,ServletException
    {
        String option = request.getParameter("Option");
        if(option.equals("Add")){
            response.sendRedirect("Checkout");
        }
        if(option.equals("Remove")){
            response.sendRedirect("Checkout");
        }
        if(option.equals("Transfeer")){
            response.sendRedirect("Transaction");
        }
        if(option.equals("History")){
            response.sendRedirect("Checkout");
        }
        if(option.equals("Log")){
            response.sendRedirect("Checkout");
        }
    }
}
