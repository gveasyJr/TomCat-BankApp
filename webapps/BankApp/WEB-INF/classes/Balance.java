import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class Balance extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException,ServletException
    {
        String username = request.getParameter("User");
        HttpSession session = request.getSession();
        User user = new User(username, 12);
        session.setAttribute(username, user);

        sendPage(response, user.getUsername());
    }

    public void sendPage(HttpServletResponse reply, String user)
    throws IOException
    {
        reply.setContentType("text/HTML");
        PrintWriter out = reply.getWriter();
        
        out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>" + "User Main Page" + "</TITLE>");
		out.println("</HEAD>");

        out.println("<BODY>");

		out.println("<CENTER>");
		out.println("<H1><FONT COLOR=Red>" + "Welcome: " + user + "</FONT></H1>");
		out.println("<BR><BR><BR>");

		out.println("<FORM METHOD=POST ACTION='Weight'");

		out.println("<TABLE>");

		out.println("<TR>");
		out.println("	<TD>Quantity required (kg)");
		out.println(
			"	<INPUT TYPE='Text' NAME='Qty'  VALUE=''  SIZE=5></TD>");
		out.println("</TR>");
		out.println("</TABLE>");

		out.println("<BR><BR><BR>");

		out.println("<TABLE>");

		out.println("<TR>");
		out.println(
			"	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Add' CHECKED>");
		out.println("	<FONT COLOR=blue>	Add to cart.</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println(
			"	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Remove'>");
		out.println(
			"	<FONT COLOR=blue>	Remove item from cart.</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println(
			"	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Next'>");
		out.println(
			"	<FONT COLOR=blue>	Choose next item.</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println(
			"	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Checkout'>");
		out.println("	<FONT COLOR=blue>	Go to checkout.</FONT></TD>");
		out.println("</TR>");

		out.println("</TABLE>");

		out.println("<BR><BR><BR>");

		out.println("<INPUT TYPE='Submit' VALUE='Submit'>");

		out.println("</FORM>");
		out.println("</CENTER>");

		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
    }
}
