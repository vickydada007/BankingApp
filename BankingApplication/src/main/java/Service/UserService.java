package Service;
import DAO.UserDAO;
import Model.User;
import Util.DTO.LoginCred;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    private final UserDAO userDAO;
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }



    public String userRegistration(User user) throws SQLException{
        return userDAO.createUser(user);

//        int userId = 0, count = 0;
//        String email, password, customerName;
//
//        Scanner sc = new Scanner(System.in);
//        //User Account Registration
//        System.out.println("Enter the below details to register your account");
////        System.out.println("Enter User Id (User Id must be a number).");
////        while (true) {
////            try {
////                userId = sc.nextInt();
////                break;
////            } catch (Exception E) {
////                if (count >= 3) {
////                    System.out.println("Failed to give a valid User ID which is only a number.");
////                    System.out.println("Start from the beginning. Thank you!");
////                    return;
////                }
////                System.out.println("Enter Valid User ID which consists of only numbers");
////                sc.nextLine();
////                count++;
////            }
////        }
////        sc.nextLine();
////        count = 0;
//        System.out.println("Enter E-mail address");
//        while (true) {
//
//            email = sc.nextLine();
//            System.out.println(email);
//            if (email.contains("@") && email.contains("."))
//                break;
//            else if (count >= 3) {
//                System.out.println("Failed to give a valid E-mail Address");
//                System.out.println("Start from the beginning. Thank you!");
//                return false;
//            } else
//                System.out.println("Enter a valid E-mail address\nRequirements:\n1. It must consist @ and '.'");
//            count++;
//
//        }
//        count = 0;
//        System.out.println("Enter Password");
//        while (true) {
//            System.out.println("Requirements:\n1. It must contain a special character\n" +
//                    "2. It must contain a uppercase\n" +
//                    "3. It must contain a lowercase\n" +
//                    "4. Minimum length is 8 characters. ");
//            password = sc.nextLine();
//            if (validatePassword(password))
//                break;
//            else if (count>=3) {
//                System.out.println("Failed to give a valid Password");
//                System.out.println("Start from the beginning. Thank you!");
//                return false;
//            }
//            else{
//                System.out.println("Enter Valid Password");
//                count++;
//            }
//
//        }
//        System.out.println("Enter Customer name");
//        customerName = sc.nextLine();
//        User SessionUser;
//            SessionUser = new User(userId, email, password, customerName);
//        System.out.println("Hurray! Your User account is created successfully.");
//        return null;
    }

    public User userLogin(LoginCred user) throws SQLException {

        return UserDAO.loginUser(user);
//        String password;
//        int userId,count=0;
//        Scanner sc = new Scanner(System.in);
//        //User Login
//        System.out.println("User Login:");
//        for (int i = 3; i >= 0; i--) {
//            System.out.println("Enter User Id");
//            while (true) {
//            try {
//                userId = sc.nextInt();
//                break;
//            } catch (Exception E) {
//                if (count >= 3) {
//                    System.out.println("Failed to give a valid User ID which is only a number.");
//                    System.out.println("Start from the beginning. Thank you!");
//                    return false;
//                }
//                System.out.println("Enter Valid User ID which consists of only numbers");
//                sc.nextLine();
//                count++;
//            }
//        }
//        sc.nextLine();
//        count = 0;
//            System.out.println("Enter password");
//            password = sc.nextLine();
//            rs.beforeFirst();
//            while(rs.next()) {
//                if ((userId == rs.getInt(1)) && password.equals(rs.getString(3))) {
//                    System.out.println("LOGIN SUCCESSFUL!!!\nWelcome " + rs.getString(4) + " to  OF Vthe BANKIKAS!");
//                    User SessionUser = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
//                    return true;
//                }
//                }
//                if (i > 0) {
//                    System.out.println("User ID or Password is Incorrect. Try again\nYou have " + i + " attempts left.");
//                } else {
//                    System.out.println("Sorry! Try again from beginning");
//                    return false;
//                }
//
//        }
//        return false;
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


