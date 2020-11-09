package test.bankaccount

import bankaccount.BankAccount;
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;
import java.lang.Double.*;


public class BankAccountTests {

  private final BankAccount bankAccount = new BankAccount();

  
  @Test
  void BankAccount_IfInitialized_ReturnsBankAccount() {
    BankAccount newAcct = new BankAccount();
    
    int acctNum = newAcct.getAcctNum();
    double balance = newAcct.getBalance();
    String fName = newAcct.getFName();
    String lName = newAcct.getLName();

    assertNotEquals(acctNum, null, "Account Number is initialized");
    assertEquals(balance, 0, "User has no money in account");
    assertNotEquals(fName, null, "User's first name is: " + fName);
    assertNotEquals(lName, null, "User's last name is: " + lName);
  }

  @Test
  public void Deposit_NegativeAmount_ReturnsFalse() {
    boolean res = bankAccount.deposit(-1000);
    String log = bankAccount.getLog();
    assertEquals(res, false, "Cannot deposit a negative amount"); 

    // TODO: Cut Timestamp at first tab char
    // Add Log validation
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

    // TODO: Strip log function for assert statement.

    assertEquals(res, true, "Deposit of $1000 is successful");
    //assertEquals(log, "");

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
