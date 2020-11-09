package test.bankaccount

import bankaccount.BankAccount;
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;


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
  public void Deposit_NegativeAmount_ExceptionThrown() {

  }

  @Test
  public void Deposit_AmountOverflow_ExceptionThrown() {

  }

  @Test
  public void Deposit_1000_True() {

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
