import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Vector;

public class UserManager {
    //private static final String FILE_PATH = "user.dat";
    //private static final int VECTOR_POSITION = 0; // Position of the Vector in the file

    public void writeUser(User changedUser, String FILE_PATH) {
    
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file.getFD()));
            @SuppressWarnings("unchecked")
            Vector<User> users = (Vector<User>) inputStream.readObject();
            inputStream.close();

            int index = users.indexOf(changedUser);
            if (index != -1) {
                users.set(index, changedUser);
            } else {
                users.add(changedUser);
            }
    
            file.setLength(0);
    
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file.getFD()));
            outputStream.writeObject(users);
            outputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    

    public User returnUserByUsername(String username, String FILE_PATH) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            
            // Create a FileInputStream using the FileDescriptor of the RandomAccessFile
            FileInputStream fis = new FileInputStream(file.getFD());
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            
            // Read the Vector<User> object from the ObjectInputStream
            @SuppressWarnings("unchecked")
            Vector<User> users = (Vector<User>) inputStream.readObject();
            
            inputStream.close();
            
            // Perform the search and return the user object
            int index = users.indexOf(new User(username));
            if (index != -1) {
                return users.get(index); // User was found
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initUsers(String FILE_PATH) {
        try (RandomAccessFile file = new RandomAccessFile(FILE_PATH, "rw")) {
            if (file.length() == 0) {
                Vector<User> users = new Vector<>();

                User user1 = new User("adam1");
                Checking acct1 = new Checking(200.0, "Adam's Checkings Account");
                Saving acct2 = new Saving(1200.0, "Adam's Savings Account");
                Mortgage acct3 = new Mortgage(300.0, "Adam's Mortgages Account");
                user1.addAccount(acct1);
                user1.addAccount(acct2);
                user1.addAccount(acct3);
                users.add(user1);

                User user2 = new User("guy3");
                Checking acct4 = new Checking(10.0, "Guy's Checkings Account");
                Saving acct5 = new Saving(900.0, "Guy's Savings Account");
                Mortgage acct6 = new Mortgage(4000.0, "Guy's Mortgages Account");
                user2.addAccount(acct4);
                user2.addAccount(acct5);
                user2.addAccount(acct6);
                users.add(user2);

                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
                outputStream.writeObject(users);
                outputStream.close();
            }
            else{
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}