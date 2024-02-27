package Service;

import DAO.AccountDAO;
import Util.DTO.WithdrawDeposit;

import java.sql.SQLException;

public class AccountService {

    private final AccountDAO accountDAO;
    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public String withdrawDeposit(WithdrawDeposit service) throws SQLException {
        return accountDAO.withdrawDeposit(service);
    }

//    public int accountId;
//    public int userId;
//    public double  balance;
//
//
//    static String url = "jdbc:mysql://vikasdatabase.ch8mi460k18s.us-east-1.rds.amazonaws.com:3306/DB1";
//    static String username = "admin";
//    static String password = "admin123";
//    Account account = new Account();
//
//    public AccountService(UserService userService) {
//    }
//
//    public void deposit(double amount) {
//        balance = Account.getBalance();
//        if(amount>0.0) {
//            balance += amount;
//            account.setBalance(balance);
//            System.out.println("Successfully deposited $" + amount + " in your account");
//        }
//        else
//            System.out.println("Invalid Amount");
//    }
//
//    public void withdrawal(double amount) {
//        balance = Account.getBalance();
//        if(amount>0.0 && balance>=amount) {
//            balance -= amount;
//            account.setBalance(balance);
//            System.out.println("Successfully withdrawn $" + amount + " from your account");
//        } else if (balance<amount) {
//            System.out.println("you don't have sufficient amount in your account to withdrawal the requested amount");
//        } else
//            System.out.println("Invalid Amount");
//    }
//
//    public double getBalance(){
//        return Account.getBalance();
//    }
//
//
//    public void transactions() throws SQLException {
//        Scanner sc = new Scanner(System.in);
//        //Account Service
//       AccountService AS = new AccountService();
//        User SessionUser = new User();
//        while (true) {
//            System.out.println("TRANSACTIONS MENU\nSelect one option below:\n1) Check Balance\n2)Deposit Money\n3)Withdrawal Money\n4)Logout from your account");
//            int iInput;
//            try {
//                iInput = sc.nextInt();
//            } catch (Exception E) {
//                System.out.println("Invalid Entry.");
//                break;
//            }
//            switch (iInput) {
//                case 1:
//                    System.out.println("The balance in your account is $" + Account.getBalance());
//                    break;
//                case 2:
//                    System.out.println("Enter Amount to Deposit.");
//                    AS.deposit(sc.nextFloat());
//                    System.out.println("Current balance in your account is $" + Account.getBalance());
//                    break;
//                case 3:
//                    System.out.println("Enter Amount to Withdrawal.");
//                    AS.withdrawal(sc.nextFloat());
//                    System.out.println("Current balance in your account is $" + Account.getBalance());
//                    break;
//                case 4:
//                    Connection conn = DriverManager.getConnection(url, username, password);
//                    Statement stmt = conn.createStatement();
//                    String sql = "UPDATE account SET balance="+Account.getBalance()+" WHERE user_id="+Account.getUserId()+";";
//                    stmt.execute(sql);
//                    conn.close();
//
//                    System.out.println("Current user is logged out");
//                    SessionUser.setCustomerName(null);
//                    SessionUser.setEmail(null);
//                    SessionUser.setPassword(null);
//                    SessionUser.setUserId(0);
//
//
//                    break;
//                default:
//                    System.out.println("Invalid Entry.");
//                    break;
//            }
//            if(iInput==4)
//                return;
//        }
//
//    }

}
