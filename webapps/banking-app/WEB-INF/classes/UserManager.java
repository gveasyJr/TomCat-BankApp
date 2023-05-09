// import java.util.Scanner;
import java.util.ArrayList;

public class UserManager {

    private ArrayList<User> users;

    public UserManager() {
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public void addUser(String username) {
        User user = new User(username);
        users.add(user);
    }
}