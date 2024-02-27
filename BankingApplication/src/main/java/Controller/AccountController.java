package Controller;

import Model.Account;
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

public class AccountController {
    User sessionUser;

    private final AccountService accountService;
    Javalin app;

        public AccountController(Javalin app, AccountService accountService) {
        this.app = app;
        this.accountService = accountService;
    }

    public void accountEndpoint(Javalin app) {


        app.post("WithdrawDeposit",this::putWithdrawDepositHandler);

    }

    public void putWithdrawDepositHandler(Context context) throws JsonProcessingException, SQLException {
        ObjectMapper mapper = new ObjectMapper();
        WithdrawDeposit service = mapper.readValue(context.body(), WithdrawDeposit.class);
        if(UserController.sessionUser==null)
            context.json("No User is Logged in");
        else {
            String message = accountService.withdrawDeposit(service);
            context.json(message);
        }

    }
}
