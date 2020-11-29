package test.log;

import app.bankAccount.*;
import app.log.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import java.lang.StringBuffer;

public class LogTests {
  private Log log = new Log();
  private BankAccount acct = new BankAccount();
  private BankAccount targ = new BankAccount();

  @Test 
  public void logAppend_ShouldLogMessage() { 
  log.logAppend("Testing logAppend");
  String res = log.searchFor("Testing logAppend");
  assertNotNull(res, "Results should not be empty");
  assertEquals("Testing logAppend", res, "Log entry should be formatted correctly");
  }

  @Test 
  public void logTo_ShouldLogToAcct() {
    acct.getLog().logTo("Testing logTo", acct);
    String res = acct.searchFor("Testing logTo");
    assertNotNull(res, "Results should not be empty");
    assertEquals("Testing logTo", res, "Log entry should be formatted correctly");
  }

  @Test 
  public void logMessage_Msg_ShouldLogMsg() {
    log.logMessage("Testing logMessage");
    String res = log.searchFor("Testing logMessage");
    assertNotNull(res, "Results should not be empty");
    assertEquals("Testing logMessage", res, "Log entry should be formatted correctly");
  }

  @Test 
  public void logMessage_MsgAmt_ShouldLogMsgWithAmt() {
    log.logMessage("logMessage with amount", 100);
    String resLog = log.searchFor("logMessage with amount");
    String resAmt = log.searchFor("\\[\\$100.0\\]");
    assertNotNull(resLog);
    assertNotNull(resAmt);
    assertEquals("logMessage with amount", resLog);
    assertEquals("[$100.0]", resAmt);
  }

  @Test 
  public void logMessage_AcctsAmt_ShouldLogToBothAccts() {
    log.logMessage(acct, targ, 100);
    String acctResLog = acct.searchFor("Transfer \\[\\$100.0 to account \\d{1,5}\\]");
    String targResLog = targ.searchFor("Transfer \\[\\$100.0 received from account \\d{1,5}\\]");
    assertNotNull(acctResLog);
    assertNotNull(targResLog);
    assertEquals("Transfer [$100.0 to account " + targ.getAcctNum() + "]", acctResLog);
    assertEquals("Transfer [$100.0 received from account " + acct.getAcctNum() + "]", targResLog);
  }

  @Test 
  public void logMessage_FailedAcctAmt_ShouldLogFailedTransfer() {
    acct.getLog().logMessage(acct, 100);
    String acctResLog = acct.searchFor("Transfer Failed");
    String acctResAmt = acct.searchFor("\\[\\$100.0 to account \\d{1,5}\\]");
    assertEquals("Transfer Failed", acctResLog);
    assertEquals("[$100.0 to account " + acct.getAcctNum() + "]", acctResAmt);
  }

  @Test 
  public void parseLog_NewlineDelim_ShouldReturnLogEntry() {
    log.logMessage("Testing parseLog \nwith newline delimeter\n");
    String res = log.parseLog(log.search("Testing parseLog"), "\\r?\\n");
    assertEquals("Testing parseLog", res);
  }

  @Test 
  public void parseLog_TabDelim_ShouldReturnLogEntry() {
    log.logMessage("Testing parseLog \twith tab delimeter\t\n");
    String res = log.parseLog(log.search("Testing parseLog"), "\\t+");
    assertEquals("Testing parseLog", res);
  }

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

  //@Test
  //public void readFile_transaction_history_ReturnsTrue() {
    //boolean csvfileExists = acct.getLog().fileExists("./transaction_history.csv");
    //assertEquals(true, csvfileExists, "transaction_history should be created");
    //String logCSV = acct.getLog().readFile("./transaction_history.csv");
    //System.out.println(logCSV);
    ////String res = logCSV.searchFor("Deposit Successful");
    ////System.out.println(res);
    ////assertNotNull(res, "Transaction should be logged");
  //}
}
