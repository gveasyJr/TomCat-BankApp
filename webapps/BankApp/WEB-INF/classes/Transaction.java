import java.io.*;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import javax.servlet.*;
import javax.servlet.http.*;

public class Transaction extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException,ServletException
    {

    }
    public void sendPage(HttpServletResponse reply, String user)
	throws IOException
    {
        reply.setContentType("text/HTML");
        PrintWriter out = reply.getWriter();
        
        out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>" + "Transaction Screen" + "</TITLE>");
		out.println("</HEAD>");

        out.println("<BODY>");

		out.println("<CENTER>");
		out.println("<H1><FONT COLOR=Red>User Account</FONT></H1>");
		out.println("<BR><BR><BR>");

		out.println("<TABLE>");
    	out.println("<TR><TH>Account</TH><TH>Balance</TH></TR>");
    		//for (Account account : accounts) {

      		out.println("<TR>");
			out.println("<TD> New User Account #1</TD>");
      		out.println("<TD> 200.00 </TD>");
      		out.println("</TR>");

			out.println("<TR>");
			out.println("<TD> New User Account #2</TD>");
      		out.println("<TD> 200.00 </TD>");
      		out.println("</TR>");

			out.println("<TR>");
			out.println("<TD> New User Account #3</TD>");
      		out.println("<TD> 300.00 </TD>");
      		out.println("</TR>");
    		//}
    	out.println("</TABLE>");

		out.println("<FORM METHOD=POST ACTION='Selector'");

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
		out.println("</HTML>");
		out.flush();
    }
}