package Controller;

import Model.User;
import Service.AccountService;
import Service.UserService;
import Util.DTO.LoginCred;
import Util.DTO.WithdrawDeposit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.sql.SQLException;

public class UserController {
    public static User sessionUser;
    private final UserService userService;

    Javalin app;
    public UserController(Javalin app, UserService userService) {
        this.app = app;
        this.userService = userService;
    }




    public void userEndpoint(Javalin app) {
        app.post("register", this::postRegisterHandler);
        app.post("login", this::postLoginHandler);
        app.post("logout", this::deleteLogoutHandler);


    }

    private void postLoginHandler(Context context) throws JsonProcessingException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        LoginCred loginCred = mapper.readValue(context.body(), LoginCred.class);
        User user = userService.userLogin(loginCred);
        sessionUser=user;
            if (user == null){
                context.status(400);
                context.json("Login Failed");
            }
            else{
                context.status(200);
                context.json(user);
            }

    }

    private void postRegisterHandler(Context context) throws JsonProcessingException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        User registerUser = mapper.readValue(context.body(), User.class);
        String message=null;

            message = userService.userRegistration(registerUser);
            if (message!="Registration Successful") {
                context.status(400);
                context.json(message);
            } else {
                context.status(201);
                context.json("Registration Successful");
            }
    }
    private void deleteLogoutHandler(Context context) throws JsonProcessingException {
      //  ObjectMapper mapper = new ObjectMapper();
       // User logoutUser = mapper.readValue(context.body(),User.class);

        if (sessionUser!=null){
            sessionUser=null;
        }

        context.status(200);
        context.json("Logout Successful");
    }

}
