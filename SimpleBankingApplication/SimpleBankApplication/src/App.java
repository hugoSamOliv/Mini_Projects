import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        BankDatabase database = new BankDatabase(scanner);

        while (true) {
            System.out.println("Welcome to the Super Bank!");
            System.out.println("Enter the command to perform an operation:");
            System.out.println("1 - Create and add a new account");
            System.out.println("2 - Login into a existing account");
            System.out.println("3 - Exit the program");
            String command = scanner.nextLine();

            if (command.equals("3")) {
                break;
            }

            if (command.equals("1")) {
                if (!createAccount(scanner, database)) {
                    continue;
                }
            }

            if (command.equals("2")) {
                System.out.println("Search for the name of your account: ");
                String name = scanner.nextLine();
                if (database.containsAccount(name)) {
                    System.out.println("Give your password: ");
                    String password = scanner.nextLine();
                    if (database.getUserAccount(name).getPassword().equals(password)) {
                        doAccountOperations(scanner, database.getUserAccount(name), database);
                    } else {
                        System.out.println("Wrong password!");
                        continue;
                    }
                } else {
                    System.out.println("This account does not exist...");
                    continue;
                }
            }
        }
    }

    public static boolean createAccount(Scanner scanner, BankDatabase database) {
            System.out.println("Enter a name: ");
            String name = scanner.nextLine();
            if (database.containsAccount(name)) {
                System.out.println("This account arealdy exists...");
                return false;
            }

            System.out.println("Enter a password: ");
            String password = scanner.nextLine();

            database.addUserAccount(new UserAccount(name, password));
            return true;
    }

    public static void doAccountOperations(Scanner scanner, UserAccount account, BankDatabase database) {
        while (true) {   
            System.out.println("You managed to log in to your account!");
            System.out.println("Now, select an operation:");
            System.out.println("1 - Check Balance");
            System.out.println("2 - Add to balance");
            System.out.println("3 - Transfer balance");
            System.out.println("4 - List personal account information");
            System.out.println("5 - Delete account");
            System.out.println("6 - Exit personal account");
            String operationCommand = scanner.nextLine();
            
            if (operationCommand.equals("6")) {
                break;
            }
            
            if (operationCommand.equals("1")) {
                System.out.println("Balance: " + account.getBalance());
            }

            if (operationCommand.equals("2")) {
                System.out.println("How much do you wanna add to your balance?");
                int amount = Integer.parseInt(scanner.nextLine());
                account.addBalance(amount, scanner);
            }

            if (operationCommand.equals("3")) {
                System.out.println("How much do you to transfer?");
                int amount = Integer.parseInt(scanner.nextLine()); 
                System.out.println("What is the name of the account you want to transfer?");
                String nameOfTransferAccount = scanner.nextLine(); 
                if (database.containsAccount(nameOfTransferAccount)) {
                    UserAccount accountToTransfer = database.getUserAccount(nameOfTransferAccount);
                    database.transferBetweenAccounts(account, accountToTransfer, amount);
                } else {
                    System.out.println("The account you want to transfer to does not exist...");
                }
            }

            if (operationCommand.equals("4")) {
                System.out.println(account);
                System.out.println("Password: " + account.getPassword());
                System.out.println("Tranfer List: " + account.getPreviousTransaction());
            }

            if (operationCommand.equals("5")) {
                System.out.println("Are you really sure you want to delete your account? (yes/no)");
                String answer = scanner.nextLine();
                if (answer.equals("yes")) {
                    database.removeUserAccount(account);
                    break;
                }
            }
        }
    }
}
