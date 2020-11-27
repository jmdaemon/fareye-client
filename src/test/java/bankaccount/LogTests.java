package test.log;

import app.bankAccount.*;
import app.log.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

public class LogTests {
  private BankAccount acct = new BankAccount();

  //@Test logAppend() {
  //}

  //@Test logTo() {
  //}

  //@Test logMessage_Msg() {
  //}

  //@Test logMessage_MsgAmt() {
  //}

  //@Test logMessage_AcctsAmt() {
  //}

  //@Test logMessage_AcctAmt() {
  //}

  //@Test public search() {
  //}

  //@Test public parseLog_NewlineDelim() {
  //}

  //@Test public parseLog_TabDelim() {
  //}

  @Test
  public void searchFor_ReturnsLogMsg() {
    acct.deposit(100);
    String res = acct.getLog().searchFor("Deposit Successful");
    assertNotNull(res, "Deposit should be logged in the log");
    assertEquals("Deposit Successful", res, "Log should be formatted correctly");
  }
}
