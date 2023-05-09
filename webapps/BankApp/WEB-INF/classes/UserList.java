import java.util.ArrayList;
import java.util.List;

public class UserList {
    private List<User> users;

    public UserList() {
        this.users = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean userExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }
}
