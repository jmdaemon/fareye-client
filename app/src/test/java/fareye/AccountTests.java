package fareye;

// Standard Library
import java.math.BigDecimal;

// JUnit 5
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class AccountTests {
  private static Account acct = null;
  private static Account targ = null;

  @BeforeEach
  public void setUp() {
    acct = new Account("Richard", "J.", "Dawson");
    targ = new Account("Magnus", "", "Chase");
  }

  @Test
  public static void canDepositPositiveAmount() {
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), amt);
    assertEquals(acct.getTransactions().get(1), "Deposit Successful");
  }

  @Test
  public static void cantDepositNegativeAmount() {
    var amt = BigDecimal.valueOf(-500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), BigDecimal.valueOf(0));
    assertEquals(acct.getTransactions().get(1), "Deposit Unsuccessful");
  }

  @Test
  public static void canWithdrawPositiveAmount() {
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), amt);
    assertEquals(acct.getTransactions().get(1), "Withdrawal Successful");
  }

  @Test
  public static void cantWithdrawNegativeAmount() {
    var amt = BigDecimal.valueOf(-500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), BigDecimal.valueOf(0));
    assertEquals(acct.getTransactions().get(1), "Withdrawal Unsuccessful");
  }

  @Test
  public static void canTransferToAccounts() {
    acct.setPin(1);
    targ.setPin(2);
    var transferAmount = BigDecimal.valueOf(10);
    var expectedAmount = BigDecimal.valueOf(490);
    acct.deposit(BigDecimal.valueOf(500));
    acct.transferTo(transferAmount, targ);

    // Check the balances
    assertEquals(acct.getBalance(), expectedAmount);
    assertEquals(targ.getBalance(), transferAmount);

    // Check the logs
    assertEquals(acct.getTransactions().get(1), "Transfer of $10 to account #2");
    assertEquals(targ.getTransactions().get(1), "Transfer of $10 received from account #1");
  }

  @Test
  public static void canGeneratePinNumbers() {
    int result = Account.generatePin(Account.MAX_ACCTNUM_LENGTH);
    assertNotNull(result);
    assertTrue(result < Account.MAX_ACCTNUM_LENGTH);
    assertTrue(false);
  }
}
