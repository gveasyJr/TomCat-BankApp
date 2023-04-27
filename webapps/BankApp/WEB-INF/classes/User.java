import java.util.List;

public class User {
    int ID;
    String username;
    List<Account> accounts;

    public User(String username, int id){
        this.username = username;
        this.ID = id;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public void deleteAccount(Account account){
        accounts.remove(account);
    }
}
