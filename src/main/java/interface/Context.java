package app.ui;
import app.bankAccount.*;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private BankAccount account;

    public BankAccount currentUser() { return account; }
    public void setUser(String userName, String password) { this.account = new BankAccount(userName, password); }
}
