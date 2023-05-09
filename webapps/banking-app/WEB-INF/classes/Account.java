import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class Account implements Serializable{
    private static final long serialVersionUID = 1L;

    private double balance;
    private String accountType;
    private String accountId;
    private String accountOwner;
    private ArrayList<String> transactionHistory;

    public Account(String owner, double balance, String accountType) {
        this.accountOwner = owner;
        this.balance = balance;
        this.accountType = accountType;
        this.accountId = UUID.randomUUID().toString();
        this.transactionHistory = new ArrayList<String>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double val) {
        this.balance = val;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public String getAccountOwner() {
        return this.accountOwner;
    }

    public String getAccountId() {
        return this.accountId;
    }
    
    public void deposit(double amount) {
        this.balance += amount;
        this.transactionHistory.add("Deposit of $" + amount + " on " + new Date());
    }
    
    public void withdraw(double amount) {
        this.balance -= amount;
        this.transactionHistory.add("Withdrawal of $" + amount + " on " + new Date());
    }
    
    public ArrayList<String> getTransactionHistory() {
        return this.transactionHistory;
    }
}
