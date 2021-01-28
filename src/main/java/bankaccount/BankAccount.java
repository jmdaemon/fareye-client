package app.bankAccount;

import static app.utils.csv.CSV.*;
import static app.utils.log.Log.*;
import static app.money.Transaction.*;
import app.utils.log.*;
import app.money.*;

import java.util.Random;

public class BankAccount { 
  private final int MAX_ACCTNUM_LENGTH = 10000;
  private final int DEFAULT_PASS_LENGTH = 32;
  private int acctNum;
  private Money balance;
  private String fName, mName, lName, pswd, filepath;

  public BankAccount(String firstName, String lastName) { createAccount(firstName, lastName, false); }
  public BankAccount(String firstName, String lastName, boolean makeLog) { createAccount(firstName, lastName, makeLog); }

  private void createAccount(String firstName, String lastName, boolean makeLog) {
    this.acctNum = genAcctNum(MAX_ACCTNUM_LENGTH);
    this.balance = Money.dollar(0);
    this.fName = firstName;
    this.lName = lastName;
    this.pswd = genPswd(DEFAULT_PASS_LENGTH);
    this.filepath = ("./" + acctNum + "-transaction_history.csv");
    if (makeLog) 
      initLog("New Bank Account Created", getFilePath());
  }

  private boolean cancelProcess(String msg) {
    logMessage(msg, getFilePath());
    return false;
  }

  public boolean amountIsZero(double amount)          { return (amount == 0)  ? true : false;   }
  public boolean amountLessThanZero(double amount)    { return (amount < 0)   ? true : false;   }
  public boolean accountExists(BankAccount acct)      { return (acct != null) ? true : false;   }

  public boolean acctHasFunds(double amount, BankAccount acct) { 
    Money acctBalance = acct.getBalance();
    boolean result = (acctBalance.greaterThan(amount) || acctBalance.equalsTo(amount)) ? true : false;
    return result;
  }
  
  public boolean quitEarly(double amount, BankAccount acct) {
    boolean result = false;
    if (amountIsZero(amount) || amountLessThanZero(amount)) {
      result = true;
    } else if (accountExists(acct) && acctHasFunds(amount, acct)) {
      result = false;
    }
    return result;
  }

	public boolean deposit(double amount) {
    if (quitEarly(amount, this)) { return cancelProcess("Deposit Unsuccessful"); }

    updateBalance((processPayment(this, amount)).depositFunds("USD"));
    logMessage("Deposit Successful", amount, getFilePath());
    return true;
	}

  public boolean withdraw(double amount) {
    if (quitEarly(amount, this)) { return cancelProcess("Withdrawal Unsuccessful"); }

    updateBalance((processPayment(this, amount)).withdrawFunds("USD"));
    logMessage("Withdrawal Successful", amount, getFilePath());
    return true;
	}

  public boolean transferTo (double amount, BankAccount target) { 
    if (quitEarly(amount, this) || !accountExists(target)) { 
      return cancelProcess("Transfer Failed");
    }
    updateBalance((processPayment(this, amount)).withdrawFunds("USD"));
    target.updateBalance((processPayment(target, amount)).depositFunds("USD"));
    logMessage(this, target, amount, getFilePath());
    return true; 
  }

  public boolean checkPswd(String pass) {
    boolean result = (pass.equals(this.pswd)) ? true : false;
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
  void updateBalance(Money newBalance) { this.balance = newBalance; }

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

    public Money getBalance() { return this.balance; }
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
