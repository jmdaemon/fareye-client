package test.bankaccount;

import app.bankAccount.*;
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;
import java.lang.Double.*;


public class BankAccountTests {

  private final BankAccount bankAccount = new BankAccount("Paul", "Allen");
  private final BankAccount targAccount = new BankAccount("Timothy", "Price");

  private String parseLog(String log, int index) {
    String[] parsedLog = log.split("\\t+");
    String logRes = parsedLog[index];
    return logRes;
  }
  
  @Test
  void BankAccount_IfInitialized_ReturnsBankAccount() {
    BankAccount newAcct = new BankAccount("Patrick", "Bateman");
    
    int acctNum = newAcct.getAcctNum();
    double balance = newAcct.getBalance();
    String fName = newAcct.getFName();
    String lName = newAcct.getLName();

    assertNotNull(acctNum, "Account Number is initialized");
    assertEquals(0, balance, "User has no money in account");
    assertNotNull(fName, "User's first name is: " + fName);
    assertNotNull(lName, "User's last name is: " + lName);
  }

  @Test
  public void Deposit_NegativeAmount_ReturnsFalse() {
    boolean res = bankAccount.deposit(-1000);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 3); // Cut timeStamp from log
    assertEquals(false, res, "Cannot deposit a negative amount"); 
    assertEquals("Deposit Unsuccessful", logRes, "Account Log has recorded the failed deposit");
  }

  @Test
  public void Deposit_AmountOverflow_ExceptionThrown() {
    boolean res = bankAccount.deposit(Double.MAX_VALUE); // Win the Lottery

    // TODO: Finish buffer overflow test
    // Need to check if amount is greater than MAX_VALUE?
    // Should this throw an Exception?
    // Or should we use BigDouble
    // Ignore for now

    double bal = bankAccount.getBalance();
    String log = bankAccount.getLog().toString();

  }

  @Test
  public void Deposit_1000_ReturnsTrue() {
    boolean res = bankAccount.deposit(1000);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 3);
    assertEquals(true, res, "Deposit of $1000 is successful");
    assertEquals("Deposit Successful", logRes, "Account Log has successfully recorded the deposit");
  }

  @Test
  public void Withdraw_NegativeAmount_ReturnsFalse() {
    boolean res = bankAccount.withdraw(-1000);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 3);
    assertEquals(false, res, "Cannot withdraw a negative amount");
    assertEquals("Withdrawal Unsuccessful", logRes,"Account Log has recorded the failed withdrawal");

  }

  @Test
  public void Withdraw_AmountOverflow_ExceptionThrown() {

  }

  @Test
  public void Withdraw_1000_True() {
    bankAccount.deposit(1000); // Give our mock a starting balance
    boolean res = bankAccount.withdraw(1000);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 5); // 3 is the deposit msg from earlier
    assertEquals(true, res, "Withdrawal of $1000 is successful");
    assertEquals("Withdrawal Successful", logRes,"Account Log has successfully recorded the withdrawal");

  }


  @Test
  public void transferTo_InvalidAccount_ReturnsFalse() {
    BankAccount imaginaryAccount = null;
    bankAccount.deposit(1000);
    boolean res = bankAccount.transferTo(500, imaginaryAccount);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 5);
    assertEquals(false, res, "Cannot initiate transaction with nonexistent bank account");
    assertEquals("Transfer Failed", logRes, "Account Log has successfully recorded the failed transaction");
  }

  @Test
  public void transferTo_Acct1000_ReturnsTrue() {
    bankAccount.deposit(1000);
    boolean res = bankAccount.transferTo(500, targAccount);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 5);
    String expectedLog = ("Transfer [$500.0 to account " + targAccount.getAcctNum() + "]\n"); 

    assertEquals(true, res, "Transfer of $500 to account " + targAccount.getAcctNum() + " successful");
    assertEquals(expectedLog, logRes, "Account Log has successfully recorded the transaction");

  }

  @Test
  public void transferTo_AcctNegativeAmount_ReturnsTrue() {
    boolean res = bankAccount.transferTo(-500, targAccount);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 3);

    assertEquals(false, res, "Cannot transfer negative amount to BankAccount");
    assertEquals("Transfer Failed", logRes, "Account Log has successfully recorded the failed transaction");

  }

  @Test
  public void transferTo_AcctAmountOverflow_ReturnsTrue() {

  }

  @Test
  public void checkPswd_IsEmpty_ReturnsFalse() {
    boolean res = bankAccount.checkPswd("");
    assertEquals(false, res, "Password cannot be empty");
  }

}
