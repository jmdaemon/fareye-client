package test.bankaccount;

import app.bankAccount.*;
import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class BankAccountTests {

  private final BankAccount bankAccount = new BankAccount("Paul", "Allen");
  private final BankAccount targAccount = new BankAccount("Timothy", "Price");
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

    assertNotNull(newAcct.getAcctNum(), "Account Number is initialized");
    assertEquals(0, newAcct.getBalance(), "User has no money in account");
    assertEquals("Patrick", newAcct.getFName(), "User's first name is: " + newAcct.getFName());
    assertEquals("Bateman", newAcct.getLName(), "User's last name is: " + newAcct.getLName());
  }

  @Test
  public void Deposit_NegativeAmount_ReturnsFalse() {
    assertEquals(false, bankAccount.deposit(-1000), "Cannot deposit a negative amount"); 
    assertEquals("Deposit Unsuccessful", bankAccount.getLog().parseLog(3), "Account Log has recorded the failed deposit");
  }

  @Test
  public void Deposit_1000_ReturnsTrue() {
    assertEquals(true, bankAccount.deposit(1000), "Deposit of $1000 is successful");
    assertEquals("Deposit Successful", bankAccount.getLog().parseLog(3), "Account Log has successfully recorded the deposit");
  }

  @Test
  public void Withdraw_NegativeAmount_ReturnsFalse() {
    assertEquals(false, bankAccount.withdraw(-1000), "Cannot withdraw a negative amount");
    assertEquals("Withdrawal Unsuccessful", bankAccount.getLog().parseLog(3),"Account Log has recorded the failed withdrawal");
  }

  @Test
  public void Withdraw_1000_True() {
    bankAccount.deposit(1000); // Give our mock a starting balance
    // TODO: Deal with arbitrary index. Make it so that you don't have to pass in arbitrary number indexes
    assertEquals(true, bankAccount.withdraw(1000), "Withdrawal of $1000 is successful"); 
    assertEquals("Withdrawal Successful", bankAccount.getLog().parseLog(5),"Account Log has successfully recorded the withdrawal");
  }

  @Test
  public void transferTo_InvalidAccount_ReturnsFalse() {
    BankAccount imaginaryAccount = null;
    bankAccount.deposit(1000);
    assertEquals(false, bankAccount.transferTo(500, imaginaryAccount), "Cannot initiate transaction with nonexistent bank account");
    assertEquals("Transfer Failed", bankAccount.getLog().parseLog(5), "Account Log has successfully recorded the failed transaction");
    // *Note* that the target account isn't notified in failed transactions.
    // TODO: Change this behavior?
  }

  @Test
  public void transferTo_Acct1000_ReturnsTrue() {
    bankAccount.deposit(1000);
    String expectedLog = ("Transfer [$500.0 to account " + targAccount.getAcctNum() + "]"); 
    assertEquals(true, bankAccount.transferTo(500, targAccount), "Transfer of $500 to account " + targAccount.getAcctNum() + " successful");
    assertEquals(expectedLog, bankAccount.getLog().parseLog(5), "Account Log has successfully recorded the transaction");
  }

  @Test
  public void transferTo_AcctNegativeAmount_ReturnsTrue() {
    assertEquals(false, bankAccount.transferTo(-500, targAccount), "Cannot transfer negative amount to BankAccount");
    assertEquals("Transfer Failed", bankAccount.getLog().parseLog(3), "Account Log has successfully recorded the failed transaction");
  }

  @Test
  public void checkPswd_IsEmpty_ReturnsFalse() {
    assertEquals(false, bankAccount.checkPswd(""), "Password cannot be empty");
  }
  
  @Test
  public void genAcctNum_UpperBoundIsEqualToLowerBound_ThrowsException() {
    String expected = "upperBound cannot be less than or equal to the lowerBound";
    assertTrue(getExceptionMsg_AcctNum(1).contains(expected), "IllegalArgumentException is thrown when upperBound == lowerBound");
  }

  @Test
  public void genAcctNum_UpperBoundIsLessThanLowerBound_ThrowsException() {
    String expectedMessage = "upperBound cannot be less than or equal to the lowerBound";
    assertTrue(getExceptionMsg_AcctNum(-1).contains(expectedMessage), "IllegalArgumentException is thrown when upperBound < 1");
  }

  @Test
  public void genAcctNum_IfBoundIsSet_ReturnsAcctNum() {
    assertNotNull(bankAccount.genAcctNum(10), "Pseudorandom account number is generated");
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
        "Last Name: "   + bankAccount.getLName()     + "\n" +
        bankAccount.getLog().toStringBuffer() + "\n");
    assertEquals(expectedMessage, outContent.toString());
  }
}
