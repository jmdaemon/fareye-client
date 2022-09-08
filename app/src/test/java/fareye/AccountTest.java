package fareye;

// Standard Library
import java.math.BigDecimal;

// JUnit 5
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTests {
  private static Account acct = null;
  private static Account targ = null;

  @BeforeEach
  public void setUp() {
    acct = new Account("Richard", "J.", "Dawson");
    targ = new Account("Magnus", "", "Chase");
  }

  @Test
  void canDepositPositiveAmount() {
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), amt);
    assertEquals(acct.getTransactions().get(1), "Deposit Successful");
  }

  @Test
  void cantDepositNegativeAmount() {
    var amt = BigDecimal.valueOf(-500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), BigDecimal.valueOf(0));
    assertEquals(acct.getTransactions().get(1), "Deposit Unsuccessful");
  }

  @Test
  void canWithdrawPositiveAmount() {
    // Deposit some amount into the account
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);

    // Withdraw some of this amount required
    var withdrawal = BigDecimal.valueOf(50);
    acct.withdraw(withdrawal);
    assertEquals(amt.subtract(withdrawal), acct.getBalance());
    assertEquals("Withdrawal Successful", acct.getTransactions().get(2));
  }

  @Test
  void canWithdrawExactAmount() {
    // Deposit some amount into the account
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);

    // Withdraw some of this amount required
    var withdrawal = BigDecimal.valueOf(500);
    acct.withdraw(withdrawal);
    assertEquals(acct.getBalance(), amt.subtract(withdrawal));
    assertEquals(acct.getTransactions().get(2), "Withdrawal Successful");
  }

  @Test
  void cantWithdrawNegativeAmount() {
    var amt = BigDecimal.valueOf(-500);
    acct.withdraw(amt);
    assertEquals(BigDecimal.valueOf(0), acct.getBalance());
    assertEquals("Withdrawal Unsuccessful", acct.getTransactions().get(1));
  }

  @Test
  void cantOverdrawAmount() {
    var amt = BigDecimal.valueOf(500);
    //acct.deposit(amt);
    acct.withdraw(amt);
    assertEquals(acct.getBalance(), BigDecimal.valueOf(0));
    assertEquals(acct.getTransactions().get(1), "Withdrawal Unsuccessful");
  }

  @Test
  void canTransferToAccounts() {
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
    assertEquals(acct.getTransactions().get(3), "Transfer of $10 to account #2");
    assertEquals(targ.getTransactions().get(2), "Transfer of $10 received from account #1");
  }

  @Test
  void canGeneratePinNumbers() {
    int result = Account.generatePin(Account.MAX_ACCTNUM_LENGTH);
    assertNotNull(result);
    assertTrue(result < Account.MAX_ACCTNUM_LENGTH);
  }
}
