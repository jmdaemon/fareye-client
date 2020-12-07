package test.bankaccount;

import static app.log.csv.CSV.*;
import static app.log.Log.*;
import app.bankAccount.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.lang.StringBuilder;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class BankAccountTests {
  private static final String filepath = "./transaction_history.csv";

  //private static void initializeFile(String msg1, String msg2) throws IOException { 
    //BufferedWriter writer = new BufferedWriter(new FileWriter(filepath)); 
    //writer.write(msg1); 
    //writer.write(msg2);
    //writer.close();
  //}

  private BankAccount bankAccount;
  private BankAccount targAccount;

  @BeforeEach
  public void setUp() {
    this.bankAccount = new BankAccount("Paul", "Allen");
    this.targAccount = new BankAccount("Timothy", "Price");
  }

  //@BeforeAll
  //public void setUpLog() throws IOException {
    //String logEntry1 = (genTimeStamp() + ", " + "New Bank Account Created, ");
    //String logEntry2 = (genTimeStamp() + ", " + "Deposit Successful [$100.0], " );
    //initializeFile(logEntry1, logEntry2); 
  //}

  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final PrintStream originalOut = System.out;

  private String getExceptionMsg_AcctNum(int illegalUpperBound) {
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      int acctNum = bankAccount.genAcctNum(illegalUpperBound);
    });
    return e.getMessage();
  }

  @Test
  public void BankAccount_IfInitialized_ReturnsBankAccount() {
    BankAccount newAcct = new BankAccount("Patrick", "Bateman");

    assertNotNull(newAcct.getAcctNum());
    assertEquals(0, newAcct.getBalance());
    assertEquals("Patrick", newAcct.getFName());
    assertEquals("Bateman", newAcct.getLName());
  }

  @Test
  public void Deposit_NegativeAmount_ReturnsFalse() {
    assertEquals(false, bankAccount.deposit(-1000)); 
    assertEquals("Deposit Unsuccessful", searchLog("Deposit Unsuccessful"));
  }

  @Test
  public void Deposit_1000_ReturnsTrue() {
    assertEquals(true, bankAccount.deposit(1000));
    assertEquals("Deposit Successful  [$1000.0]", searchLog("Deposit Successful"));
  }

  @Test
  public void Withdraw_NegativeAmount_ReturnsFalse() {
    assertEquals(false, bankAccount.withdraw(-1000));
    assertEquals("Withdrawal Unsuccessful", searchLog("Withdrawal Unsuccessful"));
  }

  @Test
  public void Withdraw_1000_True() {
    bankAccount.deposit(1000); // Give our mock a starting balance
    assertEquals(true, bankAccount.withdraw(1000)); 
    assertEquals("Withdrawal Successful  [$1000.0]", searchLog("Withdrawal Successful"));
  }

  @Test
  public void transferTo_InvalidAccount_ReturnsFalse() {
    BankAccount imaginaryAccount = null;
    bankAccount.deposit(1000);
    assertEquals(false, bankAccount.transferTo(500, imaginaryAccount));
    assertEquals("Transfer Failed", searchLog("Transfer Failed"));
    // *Note* that the target account isn't notified in failed transactions.
    // TODO: Change this behavior?
  }

  @Test
  public void transferTo_Acct1000_ReturnsTrue() {
    bankAccount.deposit(1000);
    String expectedLog = ("Transfer [$500.0 to account " + targAccount.getAcctNum() + "]"); 
    assertEquals(true, bankAccount.transferTo(500, targAccount), "Transfer of $500 to account " + targAccount.getAcctNum() + " successful");
    assertEquals(500.0, bankAccount.getBalance(), "Sender's new balance should be $500"); 
    assertEquals(500.0, targAccount.getBalance(), "Receiver's new balance should be $500");
    assertEquals(expectedLog, searchLog("Transfer \\[\\$500.0 to account \\d{1,5}]"));
  }

  @Test
  public void transferTo_AcctNegativeAmount_ReturnsTrue() {
    assertEquals(false, bankAccount.transferTo(-500, targAccount));
    assertEquals("Transfer Failed", searchLog("Transfer Failed"));
  }

  @Test
  public void checkPswd_IsEmpty_ReturnsFalse() {
    assertEquals(false, bankAccount.checkPswd(""));
  }
  
  @Test
  public void genAcctNum_UpperBoundIsEqualToLowerBound_ThrowsException() {
    String expected = "upperBound cannot be less than or equal to the lowerBound";
    assertTrue(getExceptionMsg_AcctNum(1).contains(expected), "Exception thrown when upperBound == lowerBound");
  }

  @Test
  public void genAcctNum_UpperBoundIsLessThanLowerBound_ThrowsException() {
    String expectedMessage = "upperBound cannot be less than or equal to the lowerBound";
    assertTrue(getExceptionMsg_AcctNum(-1).contains(expectedMessage), "Exception thrown when upperBound < 1");
  }

  @Test
  public void genAcctNum_IfBoundIsSet_ReturnsAcctNum() {
    assertNotNull(bankAccount.genAcctNum(10));
  }

  @BeforeAll
  public static void setUpStreams() { System.setOut(new PrintStream(outContent)); }

  @AfterAll
  public static void restoreStreams() { System.setOut(originalOut); }

  @Test 
  public void display_WhenCalled_OutputBankAccountInfo() {
    bankAccount.display();
    
    String expectedMessage = (
        "Account #: "   + bankAccount.getAcctNum()   + "\n" +
        "Balance: "     + bankAccount.getBalance()   + "\n" +
        "First Name: "  + bankAccount.getFName()     + "\n" +
        "Last Name: "   + bankAccount.getLName()     + "\n");
    
    StringBuilder sb = new StringBuilder();
        String[] logEntries = searchLogAll("");
        for (String entry : logEntries) {
          sb.append(entry + "\n");
        }
    assertEquals(expectedMessage + sb, outContent.toString());
  }
}
