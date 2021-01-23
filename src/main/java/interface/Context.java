package app.ui;
import app.bankAccount.*;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() { return instance; } 

    private BankAccount account; 
    public void setUser(BankAccount acct) { this.account = acct; }
    public BankAccount currentUser() { return this.account; }
    //public void setUser(String userName, String password) { this.account = new BankAccount(userName, password); }
}
