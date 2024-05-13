import java.util.ArrayList;
import java.util.Scanner;

public class UserAccount {
    private String name;
    private String password;
    private int balance;
    private ArrayList<String> previousTransaction;

    public UserAccount(String initialName, String initialPassword) {
        this.name = initialName;
        this.password = initialPassword;
        this.balance = 0;
        this.previousTransaction = new ArrayList<>();
    }

    public void addBalance(int amount, Scanner scanner) {
        if (amount > 0) {
            this.balance += amount;
            this.previousTransaction.add("Added " + amount + " dollars");
        }
    }

    public void removeBalance(int amount, Scanner scanner) {
        if (amount <= this.balance) {     
            this.balance -= amount;
            this.previousTransaction.add("Removed " + amount + " dollars");
        }
    }

    public int getBalance() {
        return balance;
    }
    
    public ArrayList<String> getPreviousTransaction() {
        return previousTransaction;
    }

    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Username: "+ name + "\nBalance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        UserAccount compared = (UserAccount) obj;
        if (this.getName().equals(compared.getName()) &&
            this.getPassword().equals(compared.getPassword())) {
                return true;
            }
        
        return false;
    }
}
