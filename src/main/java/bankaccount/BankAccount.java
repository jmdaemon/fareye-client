package app.bankAccount;

import app.log.*;

import java.util.Random;

public class BankAccount { 
  private final int MAX_ACCTNUM_LENGTH = 10000;
  private final int DEFAULT_PASS_LENGTH = 32;
  private int acctNum = genAcctNum(MAX_ACCTNUM_LENGTH);
  private double balance = 0.0;
  private String fName = null;
  private String lName = null;
  private String pswd = genPswd(DEFAULT_PASS_LENGTH);
  private Log log = new Log();

  public BankAccount() {
    this.acctNum = genAcctNum(MAX_ACCTNUM_LENGTH); 
    this.pswd = genPswd(DEFAULT_PASS_LENGTH);
    this.log.logMessage("New Bank Account Created.");
  }

  public BankAccount(String firstName, String lastName) {
    this.fName = firstName;
    this.lName = lastName;
    this.log.logMessage("New Bank Account Created.");
  }

	public boolean deposit(double amount) {
    if (amount > 0) { 
      balance += amount;
      this.log.logMessage("Deposit Successful", amount);
      return true;
    } else if (amount == 0) {
      return true;
    } else 
      this.log.logMessage("Deposit Unsuccessful");
    return false;
	}

  public boolean withdraw(double amount) {
		if (amount > 0 && hasFunds(amount)) {
      balance -= amount;
      this.log.logMessage("Withdrawal Successful", amount);
			return true;
		} else 
      this.log.logMessage("Withdrawal Unsuccessful");
			return false;
	}

  public boolean transferTo (double amount, BankAccount target){ 
    if (amount > 0 && (hasFunds(amount)) && target != null) { 
      setBalance(balance -= amount);
      target.setBalance(target.getBalance() + amount);
      this.log.logMessage(this, target, amount);
      return true; 
    } else if (amount == 0) {
      return true; 
    } else {
      this.log.logMessage("Transfer Failed");
    }
    return false;
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
      this.log.logMessage("Password Successfully Changed");
      return true;
    } else
      this.log.logMessage("Password Reset Failed");
    return false;
  }

  void setFName(String fName) { this.fName = fName; }
  void setLName(String lName) { this.lName = lName; }
  void setBalance(double newBalance) { this.balance = newBalance; }

  public static int genAcctNum(int upperBound) {
    int lowerBound = 1;
    if (upperBound <= lowerBound) {
      throw new IllegalArgumentException("upperBound cannot be less than or equal to the lowerBound");
    }
    Random randGen = new Random();
		int acctNum = randGen.nextInt(upperBound-lowerBound) + lowerBound;
		return acctNum;
  }

  private static String genPswd(int len) {
    final String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    Random randGen = new Random();
    char[] genPswd = new char[len];
    for (int i = 0; i < len; i++) {
      genPswd[i] = charset.charAt( randGen.nextInt(charset.length()) );
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
    System.out.println(getLog().toStringBuffer().toString());
  }
} 
