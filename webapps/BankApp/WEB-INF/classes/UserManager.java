import java.io.*;

public class UserManager implements Serializable{

    private static final String FILENAME = "users.ser";

    public static void writeUser(User user) throws IOException {
        File file = new File("../../users.ser");
        if (!file.exists()) {
            file.createNewFile();
        }

        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file))) {
            ostream.writeObject(user);
            ostream.flush();
        }
    }

    public static User readUser() throws IOException, ClassNotFoundException {
        File file = new File("../../users.ser");
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream in_stream = new ObjectInputStream(new FileInputStream(file))) {
            User user = (User) in_stream.readObject();
            return user;
        }
    }
}
