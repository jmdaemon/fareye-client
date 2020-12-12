package app.bankAccount;

import static app.utils.csv.CSV.*;
import static app.utils.log.Log.*;
import app.utils.log.*;
import app.money.*;

import java.util.Random;

public class BankAccount { 
  private final int MAX_ACCTNUM_LENGTH = 10000;
  private final int DEFAULT_PASS_LENGTH = 32;
  private int acctNum;
  private double balance;
  private Money bal;
  private String fName, mName, lName, pswd, filepath;

  public BankAccount() {
    createAccount();
  }

  public BankAccount(String firstName, String lastName) {
    createAccount();
    this.fName = firstName;
    this.lName = lastName;
  }

  private void createAccount() {
    this.acctNum = genAcctNum(MAX_ACCTNUM_LENGTH);
    this.balance = 0.0;
    this.bal = Money.dollar(0);
    this.fName = null;
    this.lName = null;
    this.pswd = genPswd(DEFAULT_PASS_LENGTH);
    this.filepath = ("./" + acctNum + "-transaction_history.csv");
    initLog("New Bank Account Created", getFilePath());
  }

  private boolean cancelProcess(String msg) {
    logMessage(msg, getFilePath());
    return false;
  }

	public boolean deposit(double amount) {
    if (amount == 0)  { return true; }
    if (amount < 0)   { return cancelProcess("Deposit Unsuccessful"); }
    //balance += amount;
    Transaction newTransaction = new Transaction(this.bal, Money.dollar(amount));
    updateBalance(newTransaction.depositFunds("USD"));
    logMessage("Deposit Successful", amount, getFilePath());
    return true;
	}

  public boolean withdraw(double amount) {
    if (amount == 0)  { return true; }
    if (amount < 0)   { return cancelProcess("Withdrawal Unsuccessful"); }
    if (!hasFunds(amount)) { 
      logMessage("Withdrawal Unsuccessful", amount, getFilePath());
    }
    //balance -= amount;
    Transaction newTransaction = new Transaction(this.bal, Money.dollar(amount));
    updateBalance(newTransaction.withdrawFunds("USD"));
    logMessage("Withdrawal Successful", amount, getFilePath());
    return true;
	}

  public boolean transferTo (double amount, BankAccount target) { 
    if (amount == 0) { return true; }
    if (target == null || amount < 0) { return cancelProcess("Transfer Failed"); }
    //if (!hasFunds(amount)) { 
      //logMessage("Transfer Failed" + amount, getFilePath()); 
      //return false;
    //} 
    //setBalance(balance -= amount);
    //target.setBalance(target.getBalance() + amount);
    Transaction acct = new Transaction(this.bal, Money.dollar(amount));
    Transaction targ = new Transaction(target.bal, Money.dollar(amount));
    updateBalance(acct.withdrawFunds("USD"));
    target.updateBalance(targ.depositFunds("USD"));
    logMessage(this, target, amount, getFilePath());
    return true; 
  }

  public boolean checkPswd(String pass) {
    boolean result = (pass.equals(this.pswd)) ? true : false;
    return result;
  }

  public boolean hasFunds(double amount) {
    boolean result = (getBalance() >= amount) ? true : false;
    return result;
  }

  public boolean resetPswd(String currPass, String newPass) {
    if (!checkPswd(currPass)) { return cancelProcess("Password Reset Failed"); }
    this.pswd = newPass;
    logMessage("Password Successfully Changed", getFilePath());
    return true;
  }

  void setFName(String fName) { this.fName = fName; }
  void setLName(String lName) { this.lName = lName; }
  void setBalance(double newBalance) { this.balance = newBalance; }
  void updateBalance(Money newBalance) { this.bal = newBalance; }

  private static int genRandNum(int len) { 
    Random randGen = new Random();
    int result = randGen.nextInt(len);
    return result;
  }

  public static int genAcctNum(int upperBound) {
    if (upperBound <= 1) {
      throw new IllegalArgumentException("upperBound cannot be less than or equal to 1");
    }
    int result = genRandNum(upperBound-1) + 1;
		return result;
  }

  private static String genPswd(int len) {
    final String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    char[] genPswd = new char[len];
    for (int i = 0; i < len; i++) {
      genPswd[i] = charset.charAt(genRandNum(charset.length()));
    }
    String result = String.valueOf(genPswd);
    return result;
  }

    public double getBalance() { return this.balance; }
    public Money getBal() { return this.bal; }
    public int getAcctNum() { return this.acctNum; }
    public String getFName() { return this.fName; }
    public String getLName() { return this.lName; }
    public String getFilePath() { return this.filepath; }

  public void display() { 
    System.out.println("Account #: " +  getAcctNum());
    System.out.println("Balance: " +    getBalance());
    System.out.println("First Name: " + getFName());
    System.out.println("Last Name: " +  getLName());
    String[] logEntries = searchLogAll("", getFilePath());
    for (String entry : logEntries) {
      System.out.println(entry);
    }
  }
} 
