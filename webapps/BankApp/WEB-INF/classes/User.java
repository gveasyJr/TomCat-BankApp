import java.util.List;
import java.util.NoSuchElementException;
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
        if(account.getBalance() != 0.00){
            System.out.println("Failure: Cannot delete account with funds");
            return;
        }
        try{
            accounts.remove(account);
        }
        catch (NoSuchElementException ex){
            System.out.println("There are no accounts to delete");
        }
    }

    public double getUserBalance(){
        double userBalance = 0.00;

        for (int i = 0; i < accounts.size(); i++){
            userBalance += accounts.get(i).getBalance();
        }
        return userBalance;
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

    public Account getAccount(String name){
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getAccountName().compareTo(name) == 0){
                return accounts.get(i);
            }
        }
        throw new NoSuchElementException("The account named: " + name + "was not found");
    }

    public List<String> getAccountNames(){
        List<String> accountNames = new ArrayList<String>();
        for(int i = 0; i < accounts.size(); i++){
            accountNames.add(accounts.get(i).getAccountName());
        }
        return accountNames;
    }
}
