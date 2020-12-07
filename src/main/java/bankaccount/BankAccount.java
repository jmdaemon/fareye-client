package app.bankAccount;

import static app.log.csv.CSV.*;
import static app.log.Log.*;
import app.log.*;

import java.util.Random;

public class BankAccount { 
  private final int MAX_ACCTNUM_LENGTH = 10000;
  private final int DEFAULT_PASS_LENGTH = 32;
  private int acctNum;
  private double balance;
  private String fName;
  private String lName;
  private String pswd;
  private Log log;

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
    this.fName = null;
    this.lName = null;
    this.pswd = genPswd(DEFAULT_PASS_LENGTH);
    this.log = new Log();
    initLog("New Bank Account Created");
  }

  private boolean cancelProcess(String msg) {
    logMessage(msg);
    return false;
  }

	public boolean deposit(double amount) {
    if (amount > 0) { 
      balance += amount;
      logMessage("Deposit Successful", amount);
      return true;
    } else if (amount == 0) {
      return true;
    } else 
      return cancelProcess("Deposit Unsuccessful");
	}

  public boolean withdraw(double amount) {
		if (amount > 0 && hasFunds(amount)) {
      balance -= amount;
      logMessage("Withdrawal Successful", amount);
			return true;
		} else 
      return cancelProcess("Withdrawal Unsuccessful");
	}

  public boolean transferTo (double amount, BankAccount target){ 
    if (amount > 0 && (hasFunds(amount)) && target != null) { 
      setBalance(balance -= amount);
      target.setBalance(target.getBalance() + amount);
      logMessage(this, target, amount);
      return true; 
    } else if (amount == 0) {
      return true; 
    } else 
      return cancelProcess("Transfer Failed");
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
    if (checkPswd(currPass)) {
      this.pswd = newPass;
      logMessage("Password Successfully Changed");
      return true;
    } else 
      return cancelProcess("Password Reset Failed");
  }

  void setFName(String fName) { this.fName = fName; }
  void setLName(String lName) { this.lName = lName; }
  void setBalance(double newBalance) { this.balance = newBalance; }

  private static int genRandNum(int len) { 
    Random randGen = new Random();
    int result = randGen.nextInt(len);
    return result;
  }

  public static int genAcctNum(int upperBound) {
    int lowerBound = 1;
    if (upperBound <= lowerBound) {
      throw new IllegalArgumentException("upperBound cannot be less than or equal to the lowerBound");
    }
    int result = genRandNum(upperBound-lowerBound) + lowerBound;
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
    public int getAcctNum() { return this.acctNum; }
    public String getFName() { return this.fName; }
    public String getLName() { return this.lName; }
    public Log getLog() { return this.log; }

  public void display() { 
    System.out.println("Account #: " +  getAcctNum());
    System.out.println("Balance: " +    getBalance());
    System.out.println("First Name: " + getFName());
    System.out.println("Last Name: " +  getLName());
    String[] logEntries = searchLogAll("");
    for (String entry : logEntries) {
      System.out.println(entry);
    }
  }
} 
