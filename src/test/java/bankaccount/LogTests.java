package test.log;

import static app.utils.csv.CSV.*;
import static app.utils.log.Log.*;
import app.bankAccount.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*; 

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; 

public class LogTests {
  private static final String filepath = "./transaction_history.csv";
  private BankAccount acct;
  private BankAccount targ;
  
  @BeforeEach
  public void setUp() throws IOException {
    this.acct = new BankAccount();
    this.targ = new BankAccount(); 
    String logEntry1 = (genTimeStamp() + ", " + "New Bank Account Created, \n");
    String logEntry2 = (genTimeStamp() + ", " + "Deposit Successful\t[$100.0], \n" );
    initializeFile(logEntry1, logEntry2);

  }

  @AfterEach
  public void tearDown() { 
    File acctLog = new File("./" + acct.getAcctNum() + "-transaction_history.csv"); 
    File targLog = new File("./" + targ.getAcctNum() + "-transaction_history.csv"); 
    File logFile = new File(filepath);
    acctLog.delete();
    targLog.delete();
    logFile.delete();
  }

  private static void initializeFile(String msg1, String msg2) throws IOException { 
    BufferedWriter writer = new BufferedWriter(new FileWriter(filepath)); 
    writer.write(msg1); 
    writer.write(msg2);
    writer.close();
  }

  private static String getFilePath(BankAccount acct) {
    return ("./" + acct.getAcctNum() + "-transaction_history.csv");
  }

  @Test 
  public void logAppend_ShouldLogMessage() { 
    logAppend("\t" + "Testing logAppend", filepath); 
    assertEquals("Testing logAppend", searchLog("Testing logAppend", filepath));
  }

  @Test 
  public void logTo_ShouldLogToAcct() {
    logTo("Testing logTo", acct, filepath);
    assertEquals("Testing logTo", searchLog("Testing logTo", filepath));
  }

  @Test 
  public void logMessage_Msg_ShouldLogMsg() {
    logMessage("Testing logMessage", filepath);
    assertEquals("Testing logMessage", searchLog("Testing logMessage", filepath));
  }

  @Test 
  public void logMessage_MsgAmt_ShouldLogMsgWithAmt() {
    logMessage("logMessage with amount", 100, filepath);
    assertEquals("logMessage with amount [$100.0]", searchLog("logMessage with amount", filepath));
  }

  @Test 
  public void logMessage_AcctsAmt_ShouldLogToBothAccts() {
    logMessage(acct, targ, 100, getFilePath(acct));
    assertEquals("Transfer [$100.0 to account "             + targ.getAcctNum() + "]", searchLog("to account", getFilePath(acct)));
    assertEquals("Transfer [$100.0 received from account "  + acct.getAcctNum() + "]", searchLog("received from account", getFilePath(targ)));
  }

  @Test 
  public void logMessage_FailedAcctAmt_ShouldLogFailedTransfer() {
    logMessage(acct, 100, getFilePath(acct));
    assertEquals("Transfer Failed [$100.0 to account " + acct.getAcctNum() + "]", searchLog("Transfer Failed", getFilePath(acct) ));
  }
}
