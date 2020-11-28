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
    String res = acct.searchFor("Deposit Successful");
    assertNotNull(res, "Deposit should be logged in the log");
    assertEquals("Deposit Successful", res, "Log should be formatted correctly");
  }

  @Test
  public void writeToFile_CreatesCSVFile() {
    acct.deposit(100);
    acct.writeToFile("./transaction_history.csv");
    boolean csvfileExists = acct.getLog().fileExists("./transaction_history.csv");
    assertEquals(true, csvfileExists, "transaction_history should be created");
  }

  @Test
  public void readFile_transaction_history_ReturnsTrue() {
    //boolean csvfileExists = acct.getLog().fileExists("./transaction_history.csv");
    //String logCSV = acct.getLog().readLog("./transaction_history");
    //String res = logCSV.searchFor("Deposit Successful");
    //assertNotNull(res, "Transaction should be logged");
  }
}
