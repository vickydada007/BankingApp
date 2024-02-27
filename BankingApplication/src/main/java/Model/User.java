package Model;

public class User {

    private int userId;
    private String email;
    private String password;
    private String customerName;

    public User() {

    }
    public User (String email, String password, String customerName){
        this.email = email;
        this.password = password;
        this.customerName = customerName;
    }
    public User (int userId, String password){
        this.userId = userId;
        this.password = password;
    }
    public User(int userId, String email, String password, String customerName) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.customerName = customerName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
