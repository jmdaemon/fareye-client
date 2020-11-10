package app.bankAccount;

import java.util.Random;

import java.text.SimpleDateFormat;  
import java.lang.StringBuilder;
import java.lang.StringBuffer;
import java.util.Date;  

public class BankAccount {
	private	int acctNum;
	private	double balance;
	private	String fName;
	private	String lName;
	private	String pswd;
  private StringBuffer log;
	
	public BankAccount() {
    this.acctNum = genAcctNum(10000); // TODO: Shove all constants into Constants.java and import
    this.balance = 0.0;
    this.fName = null;
    this.lName = null;
    this.pswd = genPswd(32); // 32 Characters Long 

    String timeStamp = genTimeStamp();
    this.log = new StringBuffer();
    this.log.append (timeStamp + "\tNew Bank Account Created.\t\n");
  }

  public BankAccount(String firstName, String lastName) {
    this.acctNum = genAcctNum(10000);
    this.balance = 0.0;
    this.fName = firstName;
    this.lName = lastName;
    this.pswd = genPswd(32); 

    String timeStamp = genTimeStamp();
    this.log = new StringBuffer();
    this.log.append (timeStamp + "\tNew Bank Account Created.\t\n");
  }

	public boolean deposit(double amount) {
    String timeStamp = genTimeStamp();
		if (amount >= 0) {
      balance += amount;
      String dAmt = ("[$" + amount + "]\n");
      this.log.append(timeStamp + "\tDeposit Successful\t" + dAmt);

			return true;
		} else
      this.log.append(timeStamp + "\tDeposit Unsuccessful\t\n");
			return false;
	}

  public boolean withdraw(double amount) {
    String timeStamp = genTimeStamp();
		if (amount > 0 && amount < balance) {
      balance -= amount;
      String wAmt = ("[$" + amount + "]");
      this.log.append(timeStamp + "\tWithdrawal Successful\t" + wAmt);
			return true;
		} else 
      
      this.log.append(timeStamp + "\tWithdrawal Unsuccessful\t\n");
			return false;
	}

  public boolean transferTo (double amount, BankAccount target){
    String timeStamp = genTimeStamp();
    if (this.getBalance() >= amount) {
      String callAcctMsg = ("[$" + amount + " to account " + target.getAcctNum() + "]\n"); 
      String targetAcctMsg = ("[$" + amount + " received from account " + this.getAcctNum() + "]\n");
      this.log.append(timeStamp + "\tTransfer " + callAcctMsg);
      target.log.append(timeStamp + "\tTransfer " + targetAcctMsg);
      return true;
    } else { 
      String failMsg = ("[$" + amount + " to account " + target.getAcctNum() + "]\n"); 
      this.log.append(timeStamp + "\tTransfer Failed\t" + failMsg);
    }
      return false;
  }

  public boolean checkPswd(String pass) {
    if (pass.equals(this.pswd)) {
      return true;
    } else
      return false;
  }

  public boolean resetPswd(String currPass, String newPass) {
    String timeStamp = genTimeStamp();
    if (currPass.equals(this.pswd)) {
      this.pswd = newPass;
      this.log.append(timeStamp + "\tPassword Successfully Changed\t\n"); 
      return true;
    } else
    this.log.append(timeStamp + "\tPassword Reset Failed\t\n");
    return false;
  }

  void setFName(String fName) { this.fName = fName; }
  void setLName(String lName) { this.lName = lName; }

  public static int genAcctNum(int upperBound) {
    int lowerBound = 1;
    Random randGen = new Random();
		int acctNum = randGen.nextInt(upperBound-lowerBound) + lowerBound;
		return acctNum;
  }

  private String genPswd(int len) {
    final String charset = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    StringBuilder newPswd = new StringBuilder();
    Random randGen = new Random();
    while (0 < len--) {
        newPswd.append(
            charset.charAt(
              randGen.nextInt(charset.length())
              )
            );
    }
    return newPswd.toString();
  }

  private String genTimeStamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd.HH.mm.sss");
    Date date = new Date();  
    return (formatter.format(date));
  }

    public double getBalance() { return this.balance; }
    public int getAcctNum() { return this.acctNum; }
    public String getFName() { return this.fName; }
    public String getLName() { return this.lName; }
    public StringBuffer getLog() { return this.log; }

  public void display() { 
    System.out.println("Account #: " + this.getAcctNum());
    System.out.println("Balance: " + this.getBalance());
    System.out.println("First Name:" + this.getFName());
    System.out.println("Last Name: " + this.getLName());
    System.out.println(this.getLog());
  }
} 
