package DAO;

import Model.User;
import Util.ConnectionFactory;
import Util.DTO.LoginCred;
import com.mysql.cj.x.protobuf.MysqlxPrepare;
import io.javalin.http.Context;
import io.javalin.Javalin;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDAO {

  //  public User sessionUser;
    public static String createUser(User user) throws SQLException {

        if (!(user.getEmail().contains("@") && user.getEmail().contains(".")))
            return "Registration Failed.\nReason: Email requirement failed.";
        if(!(validatePassword(user.getPassword())))
            return "Registration Failed.\nReason: Password Requirement Failed.\nRequirements:\n" +
                    "1. It must contain a special character\n" +
                    "2. It must contain a uppercase\n" +
                    "3. It must contain a lowercase\n" +
                    "4. Minimum length is 8 characters. ";

        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            String sql = " INSERT INTO user(email, password,customer_name) values (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCustomerName());

            int checkInsert = preparedStatement.executeUpdate();

            if (checkInsert == 0) {
                return "User not added to DB";

            }
            Statement statement = connection.createStatement();
            sql = "SELECT * from user ORDER BY user_id DESC LIMIT 1;";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            user.setUserId(rs.getInt(1));

            //INITIALIZE ACCOUNT DATA
            sql = "INSERT INTO account(user_id)"+"VALUES ('"+user.getUserId()+"')";
            statement.execute(sql);
            connection.close();

            return "Registration Successful";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Registration Unsuccessful.\nInternal Error at database.";
        }
    }


    public static User loginUser(LoginCred user) {

        User sessionUser = new User();
        try (Connection connection = ConnectionFactory.getConnectionFactory().getConnection()) {

            //GET USER DATA FROM DATABASE
            ResultSet rs = null;
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM user;";
            rs = stmt.executeQuery(sql);
            rs.beforeFirst();
            while (rs.next()) {
                if ((user.getUserId() == rs.getInt(1)) && (user.getPassword()).equals(rs.getString(3))) {

                    sessionUser.setUserId(rs.getInt(1));
                    sessionUser.setEmail(rs.getString(2));
                    sessionUser.setPassword(rs.getString(3));
                    sessionUser.setCustomerName(rs.getString(4));

                    return sessionUser;
                }
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validatePassword(String password) {
        // Regular expression to enforce the password requirements
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Compile the pattern
        Pattern p = Pattern.compile(pattern);

        // Match the password with the pattern
        Matcher m = p.matcher(password);

        // Return true if password matches the pattern, otherwise false
        return m.matches();
    }
}
