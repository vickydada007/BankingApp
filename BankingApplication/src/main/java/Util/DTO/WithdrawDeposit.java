package Util.DTO;

public class WithdrawDeposit {
    private String method;
    private double amount;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double  amount) {
        this.amount = amount;
    }
}
