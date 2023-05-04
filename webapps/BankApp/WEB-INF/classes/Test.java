//import java.util.*;
//import java.io.*;



public class Test {
    public static void main(String [] args){
        User user1 = new User("John Smith", 32);
        //Account acct1 = new Account(100.00, "Account #1");

        String acct1Name = "First Account";
        String acct2Name = "Second Account";
        String acct3Name = "Third Account";

        Double balance1 = 100.00;
        Double balance2 = 200.00;
        Double balance3 = 300.00;

        // Double amt1 = 10.00;
        // Double amt2 = 20.00;
        // Double amt3 = 30.00;


        Checking acct1 = new Checking (balance1, acct1Name);
        Saving acct2 = new Saving (balance2, acct2Name);
        Mortgage acct3 = new Mortgage (balance3, acct3Name);

        user1.addAccount(acct1);
        user1.addAccount(acct2);
        user1.addAccount(acct3);

        //User Object Test//
        ////////////////////

        //test add account and get accounts
        /*
        user1.addAccount(acct1);
        System.out.println("[First Account] : " + user1.getAccountNames());
        System.out.println("100.00 : " + user1.getAccount(acct1Name).getBalance());
        System.out.println("First Account " + user1.getAccount(acct1Name).getAccountName());
        System.out.println("Checkings Account: " + user1.getAccount(acct1Name).getType());
        System.out.println("100.00 : " + user1.getUserBalance());
        System.out.println("");
        
        user1.addAccount(acct2);
        System.out.println("[First Account, Second Account] : " + user1.getAccountNames());
        System.out.println("127.32 : " + user1.getAccount(acct2Name).getBalance());
        System.out.println("Second Account " + user1.getAccount(acct2Name).getAccountName());
        System.out.println("Savings Account: " + user1.getAccount(acct2Name).getType());
        System.out.println("227.32 : " + user1.getUserBalance());
        System.out.println("");

        user1.addAccount(acct3);
        System.out.println("[First Account, Second Account, Third Account] : " + user1.getAccountNames());
        System.out.println("200.00 : " + user1.getAccount(acct3Name).getBalance());
        System.out.println("Third Account " + user1.getAccount(acct3Name).getAccountName());
        System.out.println("Mortgages Account: " + user1.getAccount(acct3Name).getType());
        System.out.println("427.32 : " + user1.getUserBalance());
        System.out.println("");
        */

        //TRANSACTION TEST

        /*System.out.println("acct1: " + user1.getAccount(acct1Name).getBalance());
        System.out.println("acct2: " + user1.getAccount(acct2Name).getBalance());
        System.out.println("acct3: " + user1.getAccount(acct3Name).getBalance());

        System.out.println("TRANSACTION: $10.00 acct3 --> acct1");
        Account.doTransaction(acct3, acct1, amt1);
        System.out.println("acct1: " + user1.getAccount(acct1Name).getBalance());
        System.out.println("acct2: " + user1.getAccount(acct2Name).getBalance());
        System.out.println("acct3: " + user1.getAccount(acct3Name).getBalance());

        Account.doTransaction(acct2, acct1, amt2);
        System.out.println("TRANSACTION: 20.00 acct2 --> acct1");
        System.out.println("acct1: " + user1.getAccount(acct1Name).getBalance());
        System.out.println("acct2: " + user1.getAccount(acct2Name).getBalance());
        System.out.println("acct3: " + user1.getAccount(acct3Name).getBalance());

        Account.doTransaction(acct3, acct1, amt3);
        System.out.println("TRANSACTION: 30.00 acct3 --> acct1");
        System.out.println("acct1: " + user1.getAccount(acct1Name).getBalance());
        System.out.println("acct2: " + user1.getAccount(acct2Name).getBalance());
        System.out.println("acct3: " + user1.getAccount(acct3Name).getBalance());*/

        /*REMOVE ACCOUNT TEST*/
        System.out.println("[First Account, Second Account, Third Account] : " + user1.getAccountNames());
        Account.doTransaction(acct2, acct3, balance2);
        System.out.println("100.0: " + user1.getAccount(acct1Name).getBalance());
        System.out.println("0.0: " + user1.getAccount(acct2Name).getBalance());
        System.out.println("500.0: " + user1.getAccount(acct3Name).getBalance());
        user1.deleteAccount(user1.getAccount(acct2Name));
        System.out.println("[First Account, Third Account] : " + user1.getAccountNames());
        System.out.println("600.00 : " + user1.getUserBalance());



        /* user1.addAccount(acct3);
        System.out.println("After 3rd addAccount() call: " + user1.getAccountNames());

        //test transactions
        System.out.println("Transaction test...");
        System.out.println();
        System.out.println("Transaction test...");
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

        System.out.println("The 1st accounts name: " + acct1.getAccountName()); */
    }
}