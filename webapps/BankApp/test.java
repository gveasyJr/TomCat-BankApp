package webapps.BankApp;

import java.io.PrintWriter;

public class test {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");

        PrintWriter out = new PrintWriter(System.out);
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Current Directory</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Current Directory:</h1>");
        out.println("<p>" + currentDirectory + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
    }
}
