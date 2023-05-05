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

		out.println("<TABLE>");
    	out.println("<TR><TH>Account</TH><TH>Type</TH><TH>Balance</TH></TR>");
    		//for (Account account : accounts) {

      		out.println("<TR>");
			out.println("<TD> New User Account #1</TD>");
      		out.println("<TD> CHECKING </TD>");
      		out.println("<TD> 200.00 </TD>");
      		out.println("</TR>");

			out.println("<TR>");
			out.println("<TD> New User Account #2</TD>");
      		out.println("<TD> MORTGAGE </TD>");
      		out.println("<TD> 200.00 </TD>");
      		out.println("</TR>");

			out.println("<TR>");
			out.println("<TD> New User Account #3</TD>");
      		out.println("<TD> SAVINGS </TD>");
      		out.println("<TD> 300.00 </TD>");
      		out.println("</TR>");
    		//}
    	out.println("</TABLE>");
		out.println("<BR><BR><BR>");

		out.println("<H2>Add Account<H2>");
		out.println("<FORM  METHOD=POST  ACTION='Add'>");
		out.println("<label for='add-name'> Name: </label>");
		out.println("<input type='text' id='add-name' name='add-name'>");
		out.println("<INPUT TYPE='Submit' VALUE='Submit'>");
	    out.println("</form>");
		
		
		/*out.println("<input type="text" id="from-name" name="from-name">");
		out.println("<label for="to-name">To:</label>");
		out.println("<input type="text" id="to-name" name="to-name">");
		out.println("<button type="submit">Submit</button>");*/




		out.println("<FORM  METHOD=POST  ACTION='Balance'");
		out.println("<INPUT TYPE='Text' NAME='Name' VALUE='Add'>");
		out.println("<INPUT TYPE='Submit' VALUE='Submit'>");




		
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
    }
}



/*        out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>" + "User Main Page" + "</TITLE>");
		out.println("</HEAD>");

        out.println("<BODY>");

		out.println("<CENTER>");
		out.println("<H1><FONT COLOR=Red>" + "Welcome: " + user + "</FONT></H1>");
		out.println("<BR><BR><BR>");

		out.println("<TABLE>");
    	out.println("<TR><TH>Account</TH><TH>Type</TH><TH>Balance</TH></TR>");
    		//for (Account account : accounts) {

      		out.println("<TR>");
			out.println("<TD> New User Account #1</TD>");
      		out.println("<TD> CHECKING </TD>");
      		out.println("<TD> 200.00 </TD>");
      		out.println("</TR>");

			out.println("<TR>");
			out.println("<TD> New User Account #2</TD>");
      		out.println("<TD> MORTGAGE </TD>");
      		out.println("<TD> 200.00 </TD>");
      		out.println("</TR>");

			out.println("<TR>");
			out.println("<TD> New User Account #3</TD>");
      		out.println("<TD> SAVINGS </TD>");
      		out.println("<TD> 300.00 </TD>");
      		out.println("</TR>");
    		//}
    	out.println("</TABLE>");

		out.println("<FORM METHOD=POST ACTION='Add'");

		out.println("<BR><BR><BR>");

		out.println("<TABLE>");

		out.println("<TR>");
		out.println("	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Add' CHECKED>");
		out.println("	<FONT COLOR=blue>Add Account</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Remove'>");
		out.println("	<FONT COLOR=blue>Remove Account</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Transfeer'>");
		out.println("	<FONT COLOR=blue>Transfeer Funds</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='History'>");
		out.println("	<FONT COLOR=blue>View Account History</FONT></TD>");
		out.println("</TR>");

		out.println("<TR>");
		out.println("	<TD><INPUT TYPE='Radio' NAME='Option' VALUE='Log'>");
		out.println("	<FONT COLOR=blue>View User Log History</FONT></TD>");
		out.println("</TR>");

		out.println("</TABLE>");

		out.println("<BR><BR><BR>");

		out.println("<INPUT TYPE='Submit' VALUE='Submit'>");

		out.println("</FORM>");
		out.println("</CENTER>");

		out.println("</BODY>");
		out.println("</HTML>");*/