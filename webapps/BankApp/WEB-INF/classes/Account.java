import java.util.List;
import java.util.ArrayList;

public class Account {
    public Double balance;
    public String type;
    public String name;
    public List<History> transactions;

    public Account(Double balance, String name){
        this.balance = balance;
        this.type = "DEFAULT";
        this.name = name;
        this.transactions = new ArrayList<>();
    }

    public static void doTransaction(Account fromAccount, Account toAccount, double amount){
        if(amount > fromAccount.getBalance()){
            System.out.println("Failure: Insufficient funds for transfer");
            return;
        }
        toAccount.depositAccount(amount);
        fromAccount.withdrawAccount(amount);
    }

    public double getBalance(){
        return balance;
    }

    public void withdrawAccount(double amount){
        if (amount <= 0) {
            System.out.println("Invalid amount for withdrawal");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds for withdrawal");
            return;
        }

        balance -= amount;
        transactions.add(new History(name, "Withdrew", amount));
    }

    public void depositAccount(double amount){
        if (amount <= 0) {
            System.out.println("Invalid amount for deposit");
            return;
        }

        balance += amount;
        transactions.add(new History(name, "Deposited", amount));
    }

    public String getAccountName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public List<History> getTransactions(){
        return transactions;
    }

    public void logTransactionState(){}
    public void getTransactionState(){}
}