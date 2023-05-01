import java.util.List;
import java.util.ArrayList;
//A "User" object will be used to represent a single bank user:
//Example: John Smith
//A User should have: An ID --> A Username --> A list of accounts
//A User should be able to: Add and Remove accounts

public class User {
    int ID;
    String username;
    List<Account> accounts;

    public User(String username, int id){
        this.username = username;
        this.ID = id;
        this.accounts = new ArrayList<Account>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public void deleteAccount(Account account){
        try{
            accounts.remove(account);
        }
        catch (IndexOutOfBoundsException ex){
            System.out.println("There are no accounts to delete");
        }
    }

    public int getID(){
        return ID;
    }

    public String getUsername(){
        return username;
    }

    public List<Account> getAccounts(){
        return accounts;
    }
}
