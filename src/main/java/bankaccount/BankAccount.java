package bankaccount;

import java.util.Random;

import java.text.SimpleDateFormat;  
import java.util.Date;  

public class BankAccount {
	private	int acctNum;
	private	double balance;
	private	String fName;
	private	String lName;
	private	String pswd;
	private	String log; 
	
	BankAccount() {
    this.acctNum = genAcctNum(10000); // TODO: Shove all constants into Constants.java and import
    this.balance = 0.0;
    this.fName = null;
    this.lName = null;
    this.pswd = genPswd(32); // 32 Characters Long 
    this.log = "";
  }

  BankAccount(String firstName, String lastName) {
    this.acctNum = genAcctNum(10000);
    this.balance = 0.0;
    this.fName = firstName;
    this.lName = lastName;
    this.pswd = genPswd(32); 
    this.log = "";
  }

	boolean deposit(double amount) {
    String timeStamp = genTimeStamp();
		if (amount >= 0) {
      balance += amount;
      String dAmt = ("[$" + amount + "]");
      log.concat(timeStamp + "\tDeposit Sucessful\t" + dAmt);

			return true;
		} else
      log.concat(timeStamp + "\tDeposit Unsuccessful\t");
			return false;
	}

  boolean withdraw(double amount) {
    String timeStamp = genTimeStamp();
		if (amount < balance) {
      balance -= amount;
      String wAmt = ("[$" + amount + "]");
      log.concat(timeStamp + "\tWithdrawal Successful\t" + wAmt);
			return true;
		} else 
      
      log.concat(timeStamp + "\tWithdrawal Unsuccessful\t");
			return false;
	}

  boolean transferTo (double amount, BankAccount target){
    String timeStamp = genTimeStamp();
    if (this.getBalance() >= amount) {
      String callAcctMsg = ("[$" + amount + " to account " + target.getAcctNum() + "]"); 
      String targetAcctMsg = ("[$" + amount + " received from account " + this.getAcctNum() + "]");
      this.log.concat(timeStamp + "\tTransfer " + callAcctMsg);
      target.log.concat(timeStamp + "\tTransfer " + targetAcctMsg);
      return true;
    } else { 
      String failMsg = ("[$" + amount + " to account " + target.getAcctNum()); 
      log.concat(timeStamp + "\tTransfer Failed\t" + failMsg);
    }
      return false;
  }

  boolean checkPswd(String pass) {
    if (pass.equals(this.pswd)) {
      return true;
    } else
      return false;
  }

  boolean resetPswd(String currPass, String newPass) {
    String timeStamp = genTimeStamp();
    if (currPass.equals(this.pswd)) {
      this.pswd = newPass;
      log.concat(timeStamp + "\tPassword Successfully Changed\t"); 
      return true;
    } else
    log.concat(timeStamp + "\tPassword Reset Failed\t");
    return false;
  }

  void setFName(String fName) { this.fName = fName; }
  void setLName(String lName) { this.lName = lName; }

  static int genAcctNum(int upperBound) {
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

  double getBalance() { return this.balance; }
  int getAcctNum() { return this.acctNum; }
  String getFName() { return this.fName; }
  String getLName() { return this.lName; }
  String getLog() { return this.log; }

  void display() { 
    System.out.println("Account #: " + this.getAcctNum());
    System.out.println("Balance: " + this.getBalance());
    System.out.println("First Name:" + this.getFName());
    System.out.println("Last Name: " + this.getLName());
    System.out.println(this.getLog());
  }
} 
