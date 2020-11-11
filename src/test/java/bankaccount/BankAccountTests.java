package test.bankaccount;

import app.bankAccount.*;
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.Double.*;


public class BankAccountTests {

  private final BankAccount bankAccount = new BankAccount("Paul", "Allen");
  private final BankAccount targAccount = new BankAccount("Timothy", "Price");
  private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private static final PrintStream originalOut = System.out;

  private String parseLog(String log, int index) {
    String[] parsedLog = log.split("\\t+");
    String logRes = parsedLog[index];
    return logRes;
  }
  
  @Test
  void BankAccount_IfInitialized_ReturnsBankAccount() {
    BankAccount newAcct = new BankAccount("Patrick", "Bateman");

    assertNotNull(newAcct.getAcctNum(), "Account Number is initialized");
    assertEquals(0, newAcct.getBalance(), "User has no money in account");
    assertNotNull(newAcct.getFName(), "User's first name is: " + newAcct.getFName());
    assertNotNull(newAcct.getLName(), "User's last name is: " + newAcct.getLName());
  }

  @Test
  public void Deposit_NegativeAmount_ReturnsFalse() {
    assertEquals(false, bankAccount.deposit(-1000), "Cannot deposit a negative amount"); 
    assertEquals("Deposit Unsuccessful", parseLog(bankAccount.getLog().toString(), 3), "Account Log has recorded the failed deposit");
  }

  @Test
  public void Deposit_AmountOverflow_ExceptionThrown() {
    boolean res = bankAccount.deposit(Double.MAX_VALUE); // Win the Lottery

    // TODO: Finish buffer overflow test
    // Need to check if amount is greater than MAX_VALUE?
    // Should this throw an Exception?
    // Or should we use BigDouble
    // Ignore for now

    double bal = bankAccount.getBalance();
    String log = bankAccount.getLog().toString();

  }

  @Test
  public void Deposit_1000_ReturnsTrue() {
    assertEquals(true, bankAccount.deposit(1000), "Deposit of $1000 is successful");
    assertEquals("Deposit Successful", parseLog(bankAccount.getLog().toString(), 3), "Account Log has successfully recorded the deposit");
  }

  @Test
  public void Withdraw_NegativeAmount_ReturnsFalse() {
    boolean res = bankAccount.withdraw(-1000);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 3);
    assertEquals(false, res, "Cannot withdraw a negative amount");
    assertEquals("Withdrawal Unsuccessful", logRes,"Account Log has recorded the failed withdrawal");

  }

  @Test
  public void Withdraw_AmountOverflow_ExceptionThrown() {

  }

  @Test
  public void Withdraw_1000_True() {
    bankAccount.deposit(1000); // Give our mock a starting balance
    boolean res = bankAccount.withdraw(1000);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 5); // 3 is the deposit msg from earlier
    assertEquals(true, res, "Withdrawal of $1000 is successful");
    assertEquals("Withdrawal Successful", logRes,"Account Log has successfully recorded the withdrawal");

  }


  @Test
  public void transferTo_InvalidAccount_ReturnsFalse() {
    BankAccount imaginaryAccount = null;
    bankAccount.deposit(1000);
    boolean res = bankAccount.transferTo(500, imaginaryAccount);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 5);
    assertEquals(false, res, "Cannot initiate transaction with nonexistent bank account");
    assertEquals("Transfer Failed", logRes, "Account Log has successfully recorded the failed transaction");
  }

  @Test
  public void transferTo_Acct1000_ReturnsTrue() {
    bankAccount.deposit(1000);
    boolean res = bankAccount.transferTo(500, targAccount);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 5);
    String expectedLog = ("Transfer [$500.0 to account " + targAccount.getAcctNum() + "]\n"); 

    assertEquals(true, res, "Transfer of $500 to account " + targAccount.getAcctNum() + " successful");
    assertEquals(expectedLog, logRes, "Account Log has successfully recorded the transaction");

  }

  @Test
  public void transferTo_AcctNegativeAmount_ReturnsTrue() {
    boolean res = bankAccount.transferTo(-500, targAccount);
    String log = bankAccount.getLog().toString();
    String logRes = parseLog(log, 3);

    assertEquals(false, res, "Cannot transfer negative amount to BankAccount");
    assertEquals("Transfer Failed", logRes, "Account Log has successfully recorded the failed transaction");

  }

  @Test
  public void transferTo_AcctAmountOverflow_ReturnsTrue() {

  }

  @Test
  public void checkPswd_IsEmpty_ReturnsFalse() {
    boolean res = bankAccount.checkPswd("");
    assertEquals(false, res, "Password cannot be empty");
  }
  
  @Test
  public void genAcctNum_UpperBoundIsEqualToLowerBound_ThrowsException() {
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      int acctNum = bankAccount.genAcctNum(1);
    });

    String expectedMessage = "upperBound cannot be less than or equal to the lowerBound";
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage), "IllegalArgumentException is thrown when upperBound == lowerBound");
  }

  @Test
  public void genAcctNum_UpperBoundIsLessThanLowerBound_ThrowsException() {
    Exception e = assertThrows(IllegalArgumentException.class, () -> {
      int acctNum = bankAccount.genAcctNum(-1);
    });

    String expectedMessage = "upperBound cannot be less than or equal to the lowerBound";
    String actualMessage = e.getMessage();
    assertTrue(actualMessage.contains(expectedMessage), "IllegalArgumentException is thrown when upperBound < 1");
  }

  @Test
  public void genAcctNum_IfBoundIsSet_ReturnsAcctNum() {
    int res =  bankAccount.genAcctNum(10);
    assertNotNull(res, "Pseudorandom account number is generated");
  }


  @BeforeAll
  public static void setUpStreams() { System.setOut(new PrintStream(outContent)); }

  @AfterAll
  public static void restoreStreams() { System.setOut(originalOut); }

  @Test 
  public void display_WhenCalled_OutputBankAccountInfo() {
    bankAccount.display();
    
    String expectedMessage = ("Account #: " + bankAccount.getAcctNum() + "\n" +
        "Balance: " + bankAccount.getBalance() + "\n" +
        "First Name: " + bankAccount.getFName() + "\n" +
        "Last Name: " + bankAccount.getLName() + "\n" +
        bankAccount.getLog() + "\n");
    assertEquals(expectedMessage, outContent.toString());
  }
}
