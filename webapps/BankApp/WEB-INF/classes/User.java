import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.io.Serializable;
//A "User" object will be used to represent a single bank user:
//Example: John Smith
//A User should have: An ID --> A Username --> A list of accounts
//A User should be able to: Add and Remove accounts

public class User implements Serializable{
    String username;
    List<Account> accounts;
    Logger logger;
    private static final long serialVersionUID = 1L;


    public User(String username){
        this.username = username;
        this.accounts = new ArrayList<Account>();
    }

    /*public User newUser(){
        User user = new User ("New User", 1);
        user.addAccount("");
        return user;
    }*/

    public void addAccount(Account acct) throws IllegalArgumentException {
        if (acct == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
    
        String acctName = acct.getAccountName();
        for (Account account : accounts) {
            if (account.getAccountName().equals(acctName)) {
                throw new IllegalArgumentException("An account with the same name already exists");
            }
        }
        accounts.add(acct);
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

    public String getUsername(){
        return username;
    }

    public List<Account> getAccounts(){
        return accounts;
    }

    public Account getAccount(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Account name cannot be null");
        }
    
        for (Account account : accounts) {
            if (name.equals(account.getAccountName())) {
                return account;
            }
        }
        throw new NoSuchElementException("The account named: " + name + " was not found");
    }

    public List<String> getAccountNames(){
        List<String> accountNames = new ArrayList<String>();
        for(int i = 0; i < accounts.size(); i++){
            accountNames.add(accounts.get(i).getAccountName());
        }
        return accountNames;
    }
    public List<History> getAllTransactions() {
        List<History> allTransactions = new ArrayList<>();

        for (Account account : accounts) {
            List<History> transactions = account.getTransactions();
            allTransactions.addAll(transactions);
        }

        return allTransactions;
    }
    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public String getLogName(){
        return username + " log";
    }
}
