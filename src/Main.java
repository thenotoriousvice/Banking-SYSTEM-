import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String url = "jdbc:mysql://localhost:3306/BANK";
        final String username = "root";
        final String password = "Admin@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException c) {
            System.out.println(c.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Scanner scanner = new Scanner(System.in);
            User user = new User(connection, scanner);
            Accounts accounts = new Accounts(connection, scanner);
            AccountManager accountsManager = new AccountManager(connection, scanner);

            String email = "";
            long account_number;

            while (true) {
                System.out.println("*** WELCOME TO VK BANKS ***");
                System.out.println();
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        user.register();
                        System.out.print("\0033[H\033[23");
                        System.out.flush();
                        break;

                    case 2:

                        email = user.login();
                        if (email != null) {
                            System.out.println();
                            System.out.println("User Logged In! ");
                            if (!accounts.account_exist(email)) {
                                System.out.println();
                                System.out.println("1. Open a new Bank Account ");
                                System.out.println("2. Exit");
                                if (scanner.nextInt() == 1) {
                                    account_number = accounts.openAccount(email);
                                    System.out.println("Account Created Successfully! ");
                                    System.out.println("Your Account Number is: " + account_number);

                                } else {
                                    break;
                                }
                            }
                            account_number = accounts.getAccount_number(email);
                            int choice2 = 0;
                            while (choice2 != 5) {
                                System.out.println();
                                System.out.println("1. Debit Money");
                                System.out.println("2. Credit Money");
                                System.out.println("3. Transfer Money");
                                System.out.println("4. Check Balance");
                                System.out.println("5. Log Out");
                                System.out.println("Enter your choice: ");
                                choice2 = scanner.nextInt();

                                switch (choice2) {
                                    case 1:
                                        accountsManager.debit_money(account_number);
                                        break;
                                    case 2:
                                        accountsManager.credit_money(account_number);
                                        break;

                                    case 3:
                                        accountsManager.transfer_money(account_number);
                                        break;
                                    case 4:
                                        accountsManager.getBalance(account_number);
                                        break;
                                    case 5:
                                        break;
                                    default:
                                        System.out.println("Enter a valid choice!");
                                        break;
                                }
                            }
                        } else {
                            System.out.println("Incorrect Email or Password!!");

                        }
                    case 3:
                        System.out.println("THANK YOU FOR USING VK BANKS");
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


