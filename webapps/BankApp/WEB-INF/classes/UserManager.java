import java.io.*;

public class UserManager implements Serializable{
    private static final String FILE_PATH = "../../user.ser";

    public static void writeUser(User user) throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (ObjectOutputStream ostream = new ObjectOutputStream(new FileOutputStream(file))) {
            ostream.writeObject(user);
            ostream.flush();
        }
    }

    public static User findUserByUsername(String username) throws IOException, ClassNotFoundException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj;
            while ((obj = inputStream.readObject()) != null) {
                if (obj instanceof User) {
                    User user = (User) obj;
                    if (user.getUsername().equals(username)) {
                        return user;
                    }
                }
            }
        } catch (EOFException e) {
            // Reached the end of the file
        }
        return null; // User not found
    }

    public static User readUser() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return null;
        }

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            User user = (User) inputStream.readObject();
            return user;
        }
    }
}
