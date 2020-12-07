package test.log;

import static app.log.csv.CSV.*;
import static app.log.Log.*;
import app.bankAccount.*;
import app.log.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*; 

public class LogTests {
  private static final String filepath = "./transaction_history.csv";
  private Log log;
  private BankAccount acct;
  private BankAccount targ;

  
  @BeforeEach
  public void setUp() {
    this.log = new Log();
    this.acct = new BankAccount();
    this.targ = new BankAccount();
  }

  @Test 
  public void logAppend_ShouldLogMessage() { 
  log.logAppend("\t" + "Testing logAppend");
  String res = searchLog("Testing logAppend");
  assertEquals("Testing logAppend", res);
  }

  @Test 
  public void logTo_ShouldLogToAcct() {
    acct.getLog().logTo("Testing logTo", acct);
    assertEquals("Testing logTo", searchLog("Testing logTo"));
  }

  @Test 
  public void logMessage_Msg_ShouldLogMsg() {
    log.logMessage("Testing logMessage");
    String res = searchLog("Testing logMessage");
    assertEquals("Testing logMessage", res);
  }

  @Test 
  public void logMessage_MsgAmt_ShouldLogMsgWithAmt() {
    log.logMessage("logMessage with amount", 100);
    assertEquals("logMessage with amount  [$100.0]", searchLog("logMessage with amount"));
  }

  @Test 
  public void logMessage_AcctsAmt_ShouldLogToBothAccts() {
    log.logMessage(acct, targ, 100);
    String acctResLog = searchLog("Transfer \\[\\$100.0 to account \\d{1,5}\\]");
    String targResLog = searchLog("Transfer \\[\\$100.0 received from account \\d{1,5}\\]");
    assertEquals("Transfer [$100.0 to account " + targ.getAcctNum() + "]", acctResLog);
    assertEquals("Transfer [$100.0 received from account " + acct.getAcctNum() + "]", targResLog);
  }

  @Test 
  public void logMessage_FailedAcctAmt_ShouldLogFailedTransfer() {
    acct.getLog().logMessage(acct, 100);
    String res = searchLog("Transfer Failed " + "\\[\\$100.0 to account \\d{1,5}\\]");
    assertEquals("Transfer Failed [$100.0 to account " + acct.getAcctNum() + "]", res);
  }
}
