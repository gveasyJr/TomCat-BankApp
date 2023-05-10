import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;
import java.util.HashMap;


public class HomePage extends HttpServlet{
	private boolean initCalled = false;

	public void giveBalance(User user, PrintWriter out){
		List<Account> accounts = user.getAccounts();
		double total = 0.0;
		out.println("<TABLE>");
    	out.println("<TR><TH>Account</TH><TH>Type</TH><TH>Balance</TH></TR>");
    	for(Account account : accounts) {
			out.println("<TR>");
			out.println("<TD>" + account.getAccountName() + "  " + "</TD>");
      		out.println("<TD>" + account.getType() + "  " + "</TD>");
      		out.println("<TD>" + account.getBalance() + "  " + "</TD>");
      		out.println("</TR>");
			total = total + account.getBalance();
    	}
		out.println("<h3>Total Account Balance: " + total + "</h3>");
		out.println("</TABLE>");
		out.println("<BR><BR><BR>");
	}

	public HashMap<String, User> initUsers(){
		User user1 = new User("adam1");
		Checking acct1 = new Checking (200.0, "Adam's Checkings Account");
        Saving acct2 = new Saving (1200.0, "Adam's Savings Account");
        Mortgage acct3 = new Mortgage(300.0, "Adam's Mortgages Account");
        user1.addAccount(acct1);
        user1.addAccount(acct2);
        user1.addAccount(acct3);


		User user2 = new User("guy3");
		Checking acct4 = new Checking (10.0, "Guy's Checkings Account");
        Saving acct5 = new Saving (900.0, "Guy's Savings Account");
        Mortgage acct6 = new Mortgage(4000.0, "Guy's Mortgages Account");
        user2.addAccount(acct4);
        user2.addAccount(acct5);
        user2.addAccount(acct6);

		HashMap<String, User> users = new HashMap<>();
		users.put(user1.getUsername(), user1);
		users.put(user2.getUsername(), user2);
		return users;
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
		String username = request.getParameter("login-username");
        HttpSession session = request.getSession();
		HashMap<String, User> users = null;


		/*User user = (User)session.getAttribute("user");
			String userObjName = user.getUsername();
			if(userObjName.equals(username) != true){
				session.invalidate();
			}*/

		@SuppressWarnings("unchecked")
		HashMap<String, User> addedUsers = (HashMap<String, User>)session.getAttribute("addedUsers");
		PrintWriter out = response.getWriter();

		if(username == null){
			this.loadInvalidUserHTML(out);
		}

		if (initCalled == false) {
            users = initUsers();
            initCalled = true;

			if(username.equals("adam1")){
				User user = users.get("adam1");
				session.setAttribute("user", user);
			}
			else if (username.equals("guy3")){
				User user = users.get("guy3");
				session.setAttribute("user", user);
			}
			if (addedUsers != null && addedUsers.containsKey(username)) {
				User user = addedUsers.get(username);
				session.setAttribute("user", user);
			} else {
				User user = users.get(username);
				session.setAttribute("user", user);
			}
        }


		User user = (User)session.getAttribute("user");
	
		response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
		out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");
		out.println("<META HTTP-EQUIV=\"Pragma\" CONTENT=\"no-cache\">");
        out.println("<META HTTP-EQUIV=\"Cache-control\" CONTENT=\"no-cache\">");
		out.println("<META HTTP-EQUIV=\"Expires\" CONTENT=\"0\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Visibility:Visible\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Left:50\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Width:30\">");
        out.println("<meta HTTP-EQUIV=\"BackButton\" Content=\"Height:30\">");
        out.println("<meta HTTP-EQUIV='Cache-Control' CONTENT='no-cache'>");
		out.println("<meta HTTP-EQUIV='Pragma' CONTENT='no-cache'>");
		out.println("<meta HTTP-EQUIV='Expires' CONTENT='0'>");
        out.println("</head>");
        out.println("<center>");


		/*logger.info("checking if " + username + " exists: " + dbr.isUserExist(username));*/

		if(user == null){
			this.loadInvalidUserHTML(out);
		}
		else{
			sendPage(out, username, user);
		}

		out.println("</center>");
        out.println("</body>");
        out.println("</html>");
		//session.invalidate();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void sendPage(PrintWriter out, String username, User user) throws IOException
    {
		out.println("<h1>Home Page</h1>");
        out.println("<h1>Welcome: " + username + "</h1>");
		giveBalance(user, out);
        
		out.println("<form method=POST action=\"ViewAccounts\">");
        out.println("<button name=\"login-username\" value=\"" + username + "\">View Balance");
        out.println("</form>");

		out.println("<form method=POST action=\"PreAddAccount\">");
		out.println("<button name=\"login-username\" value=\"" + username + "\">Create Account</button><br>");
		out.println("</form>");

		out.println("<form method=POST action=\"PreDeleteAccount\">");
		out.println("<button name=\"login-username\" value=\"" + username + "\">Delete Account</button><br>");
		out.println("</form>");
		
		out.println("<form method=POST action=\"PreTransfer\">");
		out.println("<button name=\"login-username\" value=\"" + username + "\">Transfer Funds</button><br>");
		out.println("</form>");
		
		out.println("<form method=POST action=\"PreView\">");
		out.println("<button name=\"login-username\" value=\"" + username + "\">View History</button><br>");
		out.println("</form>");

		out.println("<form method=POST action=\"index.html\">");
		out.println("<button name=\"login-username\" value=\" \">Return to Login</button><br>");
		out.println("</form>");
		out.flush();
    }

	private void loadInvalidUserHTML(PrintWriter out) {
        out.println("<h1>Username does not exist.</h1>");
        out.println("<h1>Please create an account</h1>");
        out.println("<form method=POST action=\"HomePage\">");
        out.println("<a href=\"adduser.html\"><input type=\"button\" value=\"Sign up\"></a>");
        out.println("</form>");
		out.flush();
    }

}