package DAO;

import Controller.UserController;
import Model.Account;
import Model.User;
import Util.ConnectionFactory;
import Util.DTO.WithdrawDeposit;

import com.mysql.cj.x.protobuf.MysqlxPrepare;
import io.javalin.http.Context;
import io.javalin.Javalin;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountDAO {

    ResultSet rs = null;


    public AccountDAO() throws SQLException {
    }

    public String withdrawDeposit(WithdrawDeposit service) throws SQLException {

        Connection connection = ConnectionFactory.getConnectionFactory().getConnection();
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        stmt = connection.createStatement();
        String sql = "SELECT * FROM account WHERE user_id=" + UserController.sessionUser.getUserId() + ";";
        rs = stmt.executeQuery(sql);
        rs.next();
        Account account = new Account(rs.getInt(1), rs.getInt(2), rs.getDouble(3));

        double balance;
        if (service.getMethod().equalsIgnoreCase("Deposit")) {
            balance = Account.getBalance();
            if (service.getAmount() > 0.0) {
                balance += service.getAmount();
                account.setBalance(balance);
                sql = "UPDATE account SET balance=" + Account.getBalance() + " WHERE user_id=" + Account.getUserId() + ";";
                stmt.execute(sql);
                connection.close();
                return "Successfully deposited $" + service.getAmount() + " in your account";
            } else
                return "Invalid Amount";
        } else if (service.getMethod().equalsIgnoreCase("Withdraw")) {
            balance = Account.getBalance();
            if (service.getAmount() <= balance) {
                balance -= service.getAmount();
                account.setBalance(balance);
                sql = "UPDATE account SET balance=" + Account.getBalance() + " WHERE user_id=" + Account.getUserId() + ";";
                stmt.execute(sql);
                connection.close();
                return "Successfully withdrawn $" + service.getAmount() + " in your account";
            }
                return "Invalid Amount";
        }
        else if (service.getMethod().equalsIgnoreCase("balance")){
            return "Balance in your account is $"+Account.getBalance();
        }
        return "Not Successful";
    }
}
