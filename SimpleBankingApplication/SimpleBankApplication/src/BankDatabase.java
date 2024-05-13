import java.util.ArrayList;
import java.util.Scanner;

public class BankDatabase {
    private ArrayList<UserAccount> accounts;
    private Scanner scanner;

    public BankDatabase(Scanner scanner) {
        accounts = new ArrayList<>();
        this.scanner = scanner;
    }

    public UserAccount createUserAccount() {
        System.out.println("Create an user account: ");
        String username = scanner.nextLine();
        System.out.println("Initialize your password: ");
        String password = scanner.nextLine();
        UserAccount newAccount = new UserAccount(username, password);

        return newAccount;
    }

    public void addUserAccount(UserAccount account) {
        accounts.add(account);
    }

    public void removeUserAccount(UserAccount account) {
        if (accounts.contains(account)) {
            System.out.println("Removing " + account.getName() + " account...");
            accounts.remove(account);
            System.out.println("Removal was a sucess!");
        } else {
            System.out.println("Account wasn't found...");
        }
    }

    public void transferBetweenAccounts(UserAccount firstAccount, UserAccount secondAccount, int amountToTransfer) {
        if (amountToTransfer < 0 || amountToTransfer > firstAccount.getBalance()) {
            System.out.println("INVALID OPERATION, TRY AGAIN WITH A VALID AMOUNT...");
        } else {
            firstAccount.removeBalance(amountToTransfer, scanner);
            secondAccount.addBalance(amountToTransfer, scanner);
        }
    }

    public boolean containsAccount(String name) {
        if (accounts.isEmpty()) {
            return false;
        }

        for (UserAccount account : accounts) {
            if (account.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public ArrayList<UserAccount> getAccounts() {
        return accounts;
    }

    public UserAccount getUserAccount(String name) {
        if (accounts.isEmpty()) {
            System.out.println("Empty database");
            return null;
        }

        for (UserAccount account : accounts) {
            if (account.getName().equals(name)) {
                return account;
            }
        }

        return null;
    }
}
