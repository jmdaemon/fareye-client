package test.log;

import static app.log.csv.CSV.*;
import static app.log.Log.*;
import app.bankAccount.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*; 

public class LogTests {
  private static final String filepath = "./transaction_history.csv";
  private BankAccount acct;
  private BankAccount targ;
  
  @BeforeEach
  public void setUp() {
    this.acct = new BankAccount();
    this.targ = new BankAccount();
  }

  @Test 
  public void logAppend_ShouldLogMessage() { 
  logAppend("\t" + "Testing logAppend");
  assertEquals("Testing logAppend", searchLog("Testing logAppend"));
  }

  @Test 
  public void logTo_ShouldLogToAcct() {
    logTo("Testing logTo", acct);
    assertEquals("Testing logTo", searchLog("Testing logTo"));
  }

  @Test 
  public void logMessage_Msg_ShouldLogMsg() {
    logMessage("Testing logMessage");
    assertEquals("Testing logMessage", searchLog("Testing logMessage"));
  }

  @Test 
  public void logMessage_MsgAmt_ShouldLogMsgWithAmt() {
    logMessage("logMessage with amount", 100);
    assertEquals("logMessage with amount  [$100.0]", searchLog("logMessage with amount"));
  }

  @Test 
  public void logMessage_AcctsAmt_ShouldLogToBothAccts() {
    logMessage(acct, targ, 100);
    assertEquals("Transfer [$100.0 to account "             + targ.getAcctNum() + "]", searchLog("to account"));
    assertEquals("Transfer [$100.0 received from account "  + acct.getAcctNum() + "]", searchLog("received from account"));
  }

  @Test 
  public void logMessage_FailedAcctAmt_ShouldLogFailedTransfer() {
    logMessage(acct, 100);
    assertEquals("Transfer Failed [$100.0 to account " + acct.getAcctNum() + "]", searchLog("Transfer Failed"));
  }
}
