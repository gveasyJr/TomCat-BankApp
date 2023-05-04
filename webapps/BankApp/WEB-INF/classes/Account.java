import java.util.List;
import java.util.ArrayList;

public class Account {
    Double balance;
    String type;
    String name;
    List<History> transactions;


    public Account(Double balance, String name){
        this.balance = balance;
        this.type = "DEFAULT";
        this.name = name;
        this.transactions = new ArrayList<History>();
    }

    public static void doTransaction(Account fromAccount, Account toAccount, double amount){
        if(amount > fromAccount.getBalance()){
            System.out.println("Failure: Insufficient funds for transfeer");
            return;
        }
        toAccount.depositAccount(amount);
        fromAccount.withdrawAccount(amount);
    }

    public double getBalance(){
        return balance;
    }

    public void withdrawAccount(double amount){
        balance = balance - amount;
        transactions.add(new History("Withdrew", amount));
    }

    public void depositAccount(double amount){
        balance = balance + amount;
        transactions.add(new History("Deposited", amount));
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
