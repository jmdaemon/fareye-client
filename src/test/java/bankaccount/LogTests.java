package test.log;

import app.bankAccount.*;
import app.log.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

public class LogTests {
  private BankAccount acct = new BankAccount();

  @Test
  public void search_ReturnsLogMsg() {
    acct.deposit(100);
    System.out.println(acct.getLog().toStringBuffer().toString());
    String res = acct.getLog().searchFor("Deposit Successful");
    assertNotNull(res, "Deposit should be logged in the log");
    assertEquals("Deposit Successful", res, "Log should be formatted correctly");
  }
}
