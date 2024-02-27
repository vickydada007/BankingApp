package Model;

public class Account {
    private static int accountId;
    private static int userId;
    private static double balance;

    public Account(int accountId, int userId, double balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public Account(){}


    public static int getUserId(){
        return userId;
    }

    public static double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }
}
