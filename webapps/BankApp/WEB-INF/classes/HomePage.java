import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.io.Serializable;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

	public class HomePage extends HttpServlet {

    	private void giveBalance(User user, PrintWriter out) {
        	List<Account> accounts = user.getAccounts();
        	double total = 0.0;
        	out.println("<table>");
        	out.println("<tr><th>Account</th><th>Type</th><th>Balance</th></tr>");
        	for (Account account : accounts) {
            	out.println("<tr>");
            	out.println("<td>" + account.getAccountName() + "</td>");
            	out.println("<td>" + account.getType() + "</td>");
            	out.println("<td>" + account.getBalance() + "</td>");
            	out.println("</tr>");
            	total += account.getBalance();
        	}
        	out.println("<h3>Total Account Balance: " + total + "</h3>");
        	out.println("</table>");
        	out.println("<br><br><br>");
    	}

    	@Override
    	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        	String username = request.getParameter("login-username");
        	HttpSession session = request.getSession();
			ServletContext context = getServletContext();
			String filePath = context.getRealPath("/users.dat");
        	PrintWriter out = response.getWriter();
			UserManager uM = new UserManager();
			Boolean init = false;
			
			if(init == false){
				uM.initUsers(filePath);
				loadInit(out);
				init = true;
			}
			else{
			//uM.initUsers(filePath); //check if need to initialize file, and also does
			
			if(username == null || session.getAttribute(username) == null){ //is un blank or does it not --> User obj?
				loadInvalidUserHTML(out); //go to sign in
			}
			else{
				//so a user exists
				User userFile = uM.returnUserByUsername(username, filePath);//Is there a user with that username in the file
				User currentUser = (User)session.getAttribute(username);//get the user object associated with that username 
				if(userFile == null){ //if they didn't find a match
					uM.writeUser(currentUser, filePath); //write the user to the file
					session.setAttribute(username, currentUser); //set session user
				}
				else{ //if user was found
					session.setAttribute(username, userFile); //set it as the session user
				}

			User user = (User)session.getAttribute(username);

			if((Logger)session.getAttribute(user.getLogName()) == null){
				Logger log = new Logger();
				user.setLogger(log);
				session.setAttribute(user.getLogName(), user.getLogger());
			}

			Logger log = (Logger)session.getAttribute(user.getLogName());
			session.setAttribute("currentUser", user);
			log.logAction(user.getUsername() + " entered the home page");
     


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

			out.println("<h1>Home Page</h1>");
			out.println("<h1>Welcome: " + username + "</h1>");
			giveBalance(user, out);

			out.println("<form method=\"POST\" action=\"PreAddAccount\">");
			out.println("<button name=\"login-username\" value=\"" + username + "\">Create Account</button><br>");
			out.println("</form>");

			out.println("<form method=\"POST\" action=\"PreDeleteAccount\">");
			out.println("<button name=\"login-username\" value=\"" + username + "\">Delete Account</button><br>");
			out.println("</form>");

			out.println("<form method=\"POST\" action=\"PreTransfer\">");
			out.println("<button name=\"login-username\" value=\"" + username + "\">Transfer Funds</button><br>");
			out.println("</form>");

			out.println("<form method=\"POST\" action=\"PreView\">");
			out.println("<button name=\"login-username\" value=\"" + username + "\">View History</button><br>");
			out.println("</form>");

			out.println("<form method=\"POST\" action=\"ViewLog\">");
			out.println("<button name=\"login-username\" value=\"" + username + "\">View Log</button><br>");
			out.println("</form>");

			out.println("<form method=\"POST\" action=\"index.html\">");
			out.println("<button name=\"login-username\" value=\"\">Return to Login</button><br>");
			out.println("</form>");
			out.flush();
			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			session.setAttribute(user.getLogName(), log);
		}
	}
	}

		public void loadInvalidUserHTML(PrintWriter out) {
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
			out.println("<body>");
			out.println("<center>");

			out.println("<h1>Username does not exist.</h1>");
			out.println("<h1>Please create an account</h1>");
			out.println("<form method=\"POST\" action=\"HomePage\">");
			out.println("<a href=\"adduser.html\"><input type=\"button\" value=\"Sign up\"></a>");
			out.println("</form>");

			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			out.flush();
		}

		public void loadInit(PrintWriter out) {
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
			out.println("<body>");
			out.println("<center>");

			out.println("<h1>Creating default user objects...</h1>");
			out.println("<h1>Create a new user before gaining acess</h1>");
			out.println("<form method=\"POST\" action=\"HomePage\">");
			out.println("<a href=\"adduser.html\"><input type=\"button\" value=\"Sign up\"></a>");
			out.println("</form>");

			out.println("</center>");
			out.println("</body>");
			out.println("</html>");
			out.flush();
		}
}



			/*if(session.getAttribute("adam1") == null || session.getAttribute("guy3") == null){
				User user1 = new User("adam1");
				Checking acct1 = new Checking(200.0, "Adam's Checkings Account");
				Saving acct2 = new Saving(1200.0, "Adam's Savings Account");
				Mortgage acct3 = new Mortgage(300.0, "Adam's Mortgages Account");
				user1.addAccount(acct1);
				user1.addAccount(acct2);
				user1.addAccount(acct3);
				session.setAttribute("adam1", user1);
		
				User user2 = new User("guy3");
				Checking acct4 = new Checking(10.0, "Guy's Checkings Account");
				Saving acct5 = new Saving(900.0, "Guy's Savings Account");
				Mortgage acct6 = new Mortgage(4000.0, "Guy's Mortgages Account");
				user2.addAccount(acct4);
				user2.addAccount(acct5);
				user2.addAccount(acct6);
				session.setAttribute("guy3", user2);
			}*/