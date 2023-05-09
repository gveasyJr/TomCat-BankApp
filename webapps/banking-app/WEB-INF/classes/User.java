import java.io.*;
import java.util.UUID;
import java.util.*;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private String userId;
	private List<Account> accounts;


	public User(String username) {
		this.username = username;
		this.userId = UUID.randomUUID().toString();
		this.accounts = new ArrayList<>();
	}

    public String getUsername() {
        return this.username;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void listAccounts() {
        for (Account acc : this.accounts) {
			System.out.println(acc.getAccountType() + " (id: " + acc.getAccountId() + "): " + acc.getBalance());
		}
    }
    
	public List<Account> getAccounts() {
		DatabaseReader dbr = new DatabaseReader();
		List<Account> allAccounts = dbr.getAllAccountsObjects();
		List<Account> accs = new ArrayList<>();

		for (Account a : allAccounts) {
			if (a.getAccountOwner().equals(this.username)) {
				accs.add(a);
			}
		}
		
		return accs;
		// return this.accounts;
	}

	public int getAccountsSize() {
		return this.accounts.size();
	}

	public int transferBalance(String srcAccId, String destAccId, double amt) {
		if (srcAccId.equals(destAccId)) {
			System.out.println("Error, cannot transfer balance to same account.");
			return -1;
		}
		
		List<Account> accs = this.getAccounts();
		Account srcAcc = null;
		Account destAcc = null;
		
		for (Account acc : accounts) {
			if (acc.getAccountId().equals(srcAccId)) {
				srcAcc = acc;
			} else if (acc.getAccountId().equals(destAccId)) {
				destAcc = acc;
			}
		}

		if (srcAcc.getBalance() >= amt) {
			srcAcc.withdraw(amt);
			destAcc.deposit(amt);
		} else {
			System.out.println("Source account does not have enough funds to transfer.");
			return -1;
		}

		return 0;
	}

	public void addAccount2(Account acc) {
		this.accounts.add(acc);
	}

    public int addAccount(String accType, double balance) {
		if (balance < 0) {
			System.out.println("Invalid starting balance.");
			return -1;
		}
		
		Account acc = null;
        
		if (accType.equals("savings")) {
			acc = new SavingAccount(this.username, balance);
			this.accounts.add(acc);
		} else if (accType.equals("checking")) {
			acc = new CheckingAccount(this.username, balance);
			this.accounts.add(acc);
		} else if (accType.equals("money-market")) {
			acc = new MoneyMarketAccount(this.username, balance);
			this.accounts.add(acc);
		} else {
			return -1;
		}
		return 0;
    }

	public int removeAccount(String accId) {
		Account acc = null;
		for (Account a : this.accounts) {
			if (a.getAccountId().equals(accId)) {
				acc = a;
			}
		}

		if (acc == null) {
			System.out.println("Invalid account ID to remove.");
			return -1;
		}

		if (acc.getBalance() == 0.0) {
			this.accounts.remove(acc);
		}
		System.out.println("Sucessfully removed " + acc.getAccountType() + ": " + acc.getAccountId());
		return 0;
	}

	public void sumAccountBalances() {
		double sum = 0.0;
		for (Account acc : this.accounts) {
			sum += acc.getBalance();
		}
		System.out.println("All accounts balance sum: " + sum);
	}

	public void viewAccountHistory(String accountId) {
		if (!accountId.equals("all")) {
			for (Account acc : this.accounts) {
				acc.getTransactionHistory();
			}
		} else {
			boolean flag = false;
			for (Account acc : this.accounts) {
				if (acc.getAccountId().equals(accountId)) {
					flag = true;
					acc.getTransactionHistory();
				}
			}

			if (!flag) {
				System.out.println("Error finding requested account ID.");
			}
			
		}
	}
}
