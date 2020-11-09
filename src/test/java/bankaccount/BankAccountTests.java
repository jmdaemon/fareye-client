package test.bankaccount

import bankaccount.BankAccount;
import static org.junit.jupiter.api.Assertions.*; 
import static org.junit.jupiter.api.Assertions.*; 

import org.junit.jupiter.api.Test;


public class BankAccountTests {

  private final BankAccount bankAccount = new BankAccount();

  @Test
  public void BankAccount_EmptyStringAsFirstName_ExceptionThrown() {

  }

  @Test
  public void BankAccount_EmptyStringAsLastName_ExceptionThrown() {

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
