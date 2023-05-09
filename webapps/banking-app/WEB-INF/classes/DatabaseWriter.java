import java.io.*;
import java.util.*;
import java.util.logging.*;
// import javax.lang.model.element.ModuleElement.UsesDirective;

public class DatabaseWriter implements Serializable {
    private static final long serialVersionUID = 1L;

    public DatabaseWriter() {
    }


    public static void writeUserToDatabase(User object) {
        Logger logger = Logger.getLogger("DatabaseWriter");
        File file = new File("../../usersdb");

        if (file.length() == 0) {
            try {
                ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file, true));
                ostream.writeObject(object);
                logger.info("Added " + object.getUsername() + " to usersdb");
                ostream.close();
            } catch (FileNotFoundException e) {
                logger.info("filee not found");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                logger.info(e.toString());
            }
        } else {
            try {
                AppendingObjectOutputStream aostream = new AppendingObjectOutputStream(new FileOutputStream(file, true));
                aostream.writeObject(object);
                logger.info("Appended " + object.getUsername() + " to usersdb");
                aostream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeAccountToDatabase(Account object) {
        Logger logger = Logger.getLogger("DatabaseWriter");;
        File file = new File("../../accountsdb");

        if (file.length() == 0) {
            try {
                ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file, true));
                ostream.writeObject(object);
                logger.info("Added " + object.getAccountOwner() + "'s " + object.getAccountType() + " accountsdb");
                ostream.close();
                
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                AppendingObjectOutputStream aostream = new AppendingObjectOutputStream(new FileOutputStream(file, true));
                aostream.writeObject(object);
                logger.info("Added " + object.getAccountOwner() + "'s " + object.getAccountType() + " accountsdb");                
                aostream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void rewriteModifiedUser(User moddedUser) {
        File file = new File("../../usersdb");
        DatabaseReader dbr = new DatabaseReader();
        List<User> users = dbr.getAllUserObjects();
        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file, true));
            ostream.reset();

            for (User u : users) {
                if (u.getUsername().equals(moddedUser.getUsername())) {
                    ostream.writeObject(moddedUser);
                } else {
                    ostream.writeObject(u);
                }
            }
            ostream.close();
        } catch (Exception err) {
            // do noithing
        }
    }

    public static void rewriteModifiedAccount(Account moddedAcc) {
        File file = new File("../../accountsdb");

        DatabaseReader dbr = new DatabaseReader();
        List<Account> accounts = dbr.getAllAccountsObjects();
        try {
            ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file, true));
            ostream.reset();

            for (Account a : accounts) {
                if (a.getAccountId().equals(moddedAcc.getAccountId())) {
                    ostream.writeObject(moddedAcc);
                } else {
                    ostream.writeObject(a);
                }
            }
            ostream.close();
        } catch (Exception err) {
            // do noithing
        }
    }

}
