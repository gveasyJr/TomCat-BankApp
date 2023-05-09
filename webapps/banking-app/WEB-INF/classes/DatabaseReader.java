import java.io.*;
import java.util.*;
import java.util.ArrayList;

import java.util.logging.*;


public class DatabaseReader implements Serializable {
    private static final long serialVersionUID = 1L;
    private Logger logger = Logger.getLogger("DatabaseReader");
    // private ArrayList<User> users;
    
    public DatabaseReader() {}

    // populate users list from serialized objects file and return it
    public List<User> getAllUserObjects() {
        List<User> users = new ArrayList<>();
        File file = new File("../../usersdb");
        
        try {
            if (!file.createNewFile()) {
                ObjectInputStream ostream = new ObjectInputStream(new FileInputStream(file));
                while (true) {
                    try {
                        users.add((User)ostream.readObject());
                        
                    } catch (Exception err) {
                        ostream.close();
                        break;
                    }
                }
            }
        } catch (Exception err) {
            // do nothing
        }
        return users;
    }

    public List<Account> getAllAccountsObjects() {
        List<Account> accounts = new ArrayList<>();
        File file = new File("../../accountsdb");

        try {
            if (!file.createNewFile()) {
                ObjectInputStream ostream = new ObjectInputStream(new FileInputStream(file));
                while (true) {
                    try {
                        accounts.add((Account)ostream.readObject());
                    } catch (Exception err) {
                        ostream.close();
                        break;
                    }
                }
            }
        } catch (Exception err) {
            // do nothing
        }
        return accounts;
    }


    public User getUserObject(String username) {
        List<User> users = this.getAllUserObjects();

        for (User u : users) {
            logger.info("found user: " + u.getUsername());
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;

    }

    public boolean isUserExist(String username) {
        return this.getUserObject(username) != null;
    }
    

}
