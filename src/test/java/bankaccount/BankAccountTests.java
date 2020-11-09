package test.bankaccount;

import app.bankAccount.*;
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;
import java.lang.Double.*;


public class BankAccountTests {

  private final BankAccount bankAccount = new BankAccount("Paul", "Allen");

  private String parseLog(String log, int index) {
    String[] parsedLog = log.split("\\t");
    String logRes = parsedLog[index];
    return logRes;
  }
  
  @Test
  void BankAccount_IfInitialized_ReturnsBankAccount() {
    BankAccount newAcct = new BankAccount();
    
    int acctNum = newAcct.getAcctNum();
    double balance = newAcct.getBalance();
    String fName = newAcct.getFName();
    String lName = newAcct.getLName();

    assertNotNull(acctNum, "Account Number is initialized");
    assertEquals(balance, 0, "User has no money in account");
    assertNotEquals(fName, null, "User's first name is: " + fName);
    assertNotEquals(lName, null, "User's last name is: " + lName);
  }

  @Test
  public void Deposit_NegativeAmount_ReturnsFalse() {
    boolean res = bankAccount.deposit(-1000);
    String log = bankAccount.getLog();
    assertEquals(res, false, "Cannot deposit a negative amount"); 
    String logRes = parseLog(log, 1); // Cut timeStamp from log
    assertEquals(logRes, "Deposit Unsuccessful", "Account log has successfully recorded the failed deposit");
  }

  @Test
  public void Deposit_AmountOverflow_ExceptionThrown() {
    boolean res = bankAccount.deposit(Double.MAX_VALUE);
    // TODO: Finish buffer overflow test
    // Need to check if amount is greater than MAX_VALUE?
    // Should this throw an Exception?
    // Or should we use BigDouble

    double bal = bankAccount.getBalance();
    String log = bankAccount.getLog();

  }

  @Test
  public void Deposit_1000_ReturnsTrue() {
    boolean res = bankAccount.deposit(1000);
    String log = bankAccount.getLog();
    String logRes = parseLog(log, 1);

    assertEquals(res, true, "Deposit of $1000 is successful");
    assertEquals(log, "Deposit Successful", "Account Log has successfuly recorded the deposit");
  }

  @Test
  public void Withdraw_NegativeAmount_ExceptionThrown() {

  }

  @Test
  public void Withdraw_AmountOverflow_ExceptionThrown() {

  }

  @Test
  public void Withdraw_1000_True() {

  }

}
