import java.util.*;
import java.io.*;



public class Test {
    public static void main(String [] args){
        User user1 = new User("John Smith", 32);
        Account acct1 = new Account(100.00, "Savings", "Account #1");
        Account acct2 = new Account(127.32, "Checking", "Account #2");
        Account acct3 = new Account(250.00, "Savings", "Account #3");

        //User Object Test//
        ////////////////////

        //test add account and get accounts
        user1.addAccount(acct1);
        System.out.println("After 1st addAccount() call: " + user1.getAccounts());

        user1.addAccount(acct2);
        System.out.println("After 2nd addAccount() call: " + user1.getAccounts());

        user1.addAccount(acct3);
        System.out.println("After 3rd addAccount() call: " + user1.getAccounts());

        //test transactions
        System.out.println("Transaction test...");

        //test getuserbalance
        System.out.println(user1.getUserBalance());

        //test getid
        System.out.println(user1.getID());

        //test username
        System.out.println(user1.getUsername());

        //test delete accounts and get accounts
        user1.deleteAccount(acct2);
        System.out.println("After 2nd addAccount() call: " + user1.getAccounts());

        //test delete accounts and get accounts
        user1.deleteAccount(acct2);
        System.out.println("After 2nd addAccount() call: " + user1.getAccountNames());

        //Account Object Tests//
        ///////////////////////

        //test transaction and get accounts
        Account.doTransaction(acct1, acct3, 50.00);
        System.out.println("After transaction: " + user1.getAccounts());


        //test transaction history
        System.out.println("The 1st transaction history: " + acct1.getTransactions());
        System.out.println("The 2nd transaction history: " + acct3.getTransactions());

        System.out.println("The 1st accounts name: " + acct1.getAccountName());
    }
}