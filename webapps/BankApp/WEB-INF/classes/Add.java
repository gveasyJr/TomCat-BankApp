import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Add extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException,ServletException
    {
        HttpSession session = request.getSession();
        String type = request.getParameter("Type");
        String name = request.getParameter("add-name");
        User currentUser = (User)session.getAttribute("User");

        doAdd(currentUser, request, type, name);
        response.sendRedirect("Balance");
    }

    private void doAdd(User currentUser, HttpServletRequest request, String type, String name){
        if(type.equals("Checkings")){
            currentUser.addAccount(new Checking (0.0, name));
        }
        if(type.equals("Savings")){
            currentUser.addAccount(new Saving (0.0, name));
        }
        if(type.equals("Mortgages")){
            currentUser.addAccount(new Mortgage (0.0, name));
        }

		/*String qty = request.getParameter("Qty");
		if (qty!=null)
			Session.setAttribute(currentProduct,qty);*/
	}
}
