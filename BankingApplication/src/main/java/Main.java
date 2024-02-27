import Controller.AccountController;
import Controller.UserController;
import DAO.AccountDAO;
import DAO.UserDAO;
import Model.User;
import Model.Account;
import Service.AccountService;
import Service.UserService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import io.javalin.Javalin;
import jdk.dynalink.beans.StaticClass;

import java.sql.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.StreamSupport;

public class Main {
    public static void main(String[] args) throws SQLException {

        Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors ->{
                cors.add(it ->{
                    it.anyHost();
                    it.exposeHeader("Authorization");
                });
            });
        }).start(8081);



        UserDAO userDAO = new UserDAO();
        AccountDAO accountDAO = new AccountDAO();

        UserService userService = new UserService(userDAO);
        AccountService accountService = new AccountService(accountDAO);


        UserController userController = new UserController(app, userService);
        AccountController accountController = new AccountController(app,accountService);

        userController.userEndpoint(app);
        accountController.accountEndpoint(app);

//        //DATABASE
//                String url = "jdbc:mysql://vikasdatabase.ch8mi460k18s.us-east-1.rds.amazonaws.com:3306/DB1";
//                String username = "admin";
//                String password = "admin123";
//         //       try (Connection conn = DriverManager.getConnection(url, username, password);/*Statement stmt = conn.createStatement();*/) {
//        //            String sql1 = "CREATE TABLE user(user_id INT AUTO_INCREMENT PRIMARY KEY, email VARCHAR(255), password VARCHAR(255), customer_name VARCHAR(255))";
//        //            String sql2 = "CREATE TABLE account(account_id INT AUTO_INCREMENT PRIMARY KEY, user_id INT, balance DECIMAL(10,2) DEFAULT ('0.00'), FOREIGN KEY(user_id) REFERENCES user(user_id))";
//        //            stmt.execute(sql1);
//        //            stmt.execute(sql2);
////                } catch (SQLException e) {
////                    System.out.println("Error connecting to the database");
////                    e.printStackTrace();
////                }
//        // System.exit(0);
//
//        Scanner sc = new Scanner(System.in);
//        while(true) {
//            System.out.println("Welcome to Bank of Vikas! & Grow with Us!\n1) Press 1 to register.\n2) Press 2 to login.\n3)Press any other key to Exit.");
//            String sInput = sc.nextLine();
//            switch (sInput) {
//                case "1":
//                    boolean successFlag = false;
//                    //User Account Registration
//                    UserService register = new UserService();
//                    successFlag = register.userRegistration();
//
//                    if(successFlag) {
//                        //INSERT INTO DATABASE
//                        PreparedStatement pstmt = null;
//                        Connection conn = DriverManager.getConnection(url, username, password);
//                        ResultSet rs = null;
//
//                        String sql = " INSERT INTO user(email, password,customer_name)"
//                                + " values (?, ?,?)";
//                         pstmt = conn.prepareStatement(sql);
//                         pstmt.setString(1,User.getEmail() );
//                         pstmt.setString(2, User.getPassword());
//                         pstmt.setString(3,User.getCustomerName() );
//                         pstmt.executeUpdate();
//
//                        Statement stmt = conn.createStatement();
//                        sql = "SELECT * FROM user ORDER BY user_id DESC LIMIT 1;";
//                        rs = stmt.executeQuery(sql);
//
//                        //INITIALIZE USER DATA
//                        if (rs.next()) //get first result
//                            System.out.println("Your User ID is " + rs.getInt(1) + ". Keep a note of it");//coloumn 1
//                        User sessionUser = new User(rs.getInt(1),User.getEmail(),User.getPassword(),User.getCustomerName());
//
//                        //INITIALIZE ACCOUNT DATA
//                        sql = "INSERT INTO account(user_id)"+"VALUES ('"+User.getUserId()+"')";
//                        stmt.execute(sql);
//                        conn.close();
//                    }
//                    break;
//                case "2":
//                    successFlag = false;
//
//                    //GET USER DATA FROM DATABASE
//                    Connection conn = DriverManager.getConnection(url, username, password);
//                    ResultSet rs = null;
//
//                    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//                    String sql = "SELECT * FROM user;";
//                    rs = stmt.executeQuery(sql);
//
//
//                    //USER LOGIN
//                    UserService login = new UserService();
//                    successFlag = login.userLogin(rs);
//
//                    //ACCOUNT SERVICE
//                    if(successFlag) {
//                        stmt = conn.createStatement();
//                        sql = "SELECT * FROM account WHERE user_id="+User.getUserId()+";";
//                        rs = stmt.executeQuery(sql);
//                        rs.next();
//                        Account account = new Account(rs.getInt(1),rs.getInt(2),rs.getDouble(3));
//                        AccountService service = new AccountService();
//                        service.transactions();
//                    }
//                    conn.close();
//                    break;
//                default:
//                    System.out.println("See you next time!!!");
//                    System.exit(0);
//                    break;
//            }
//        }
//
   }

}
