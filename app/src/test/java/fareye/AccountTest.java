package fareye;

// Standard Library
import java.math.BigDecimal;

// JUnit 5
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class AccountTests {
  private static BigDecimal ZERO = BigDecimal.valueOf(0);
  private static Account acct = null;
  private static Account targ = null;

  @BeforeEach
  public void setUp() {
    acct = new Account("Richard", "J.", "Dawson");
    targ = new Account("Magnus", "", "Chase");
  }

  // Helper Functions
  public String getLastMessage(Account account) {
    var msgs = account.getTransactions();
    var last = msgs.size() - 1;
    var res  = msgs.get(last);
    return res;
  }

  @Test
  void canDepositPositiveAmount() {
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);
    assertEquals(acct.getBalance(), amt);
    assertEquals("Deposit Successful", getLastMessage(acct));
  }

  @Test
  void cantDepositNegativeAmount() {
    var amt = BigDecimal.valueOf(-500);
    acct.deposit(amt);
    assertEquals(ZERO, acct.getBalance());
    assertEquals("Deposit Unsuccessful", getLastMessage(acct));
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
    assertEquals("Withdrawal Successful", getLastMessage(acct));
  }

  @Test
  void canWithdrawExactAmount() {
    // Deposit some amount into the account
    var amt = BigDecimal.valueOf(500);
    acct.deposit(amt);

    // Withdraw some of this amount required
    var withdrawal = BigDecimal.valueOf(500);
    acct.withdraw(withdrawal);
    assertEquals(amt.subtract(withdrawal), acct.getBalance());
    assertEquals("Withdrawal Successful", getLastMessage(acct));
  }

  @Test
  void cantWithdrawNegativeAmount() {
    var amt = BigDecimal.valueOf(-500);
    acct.withdraw(amt);
    assertEquals(ZERO, acct.getBalance());
    assertEquals("Withdrawal Unsuccessful", getLastMessage(acct));
  }

  @Test
  void cantOverdrawAmount() {
    var amt = BigDecimal.valueOf(500);
    acct.withdraw(amt);
    assertEquals(ZERO, acct.getBalance());
    assertEquals("Withdrawal Unsuccessful", getLastMessage(acct));
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
    assertEquals(expectedAmount, acct.getBalance());
    assertEquals(transferAmount, targ.getBalance());

    // Check the logs
    assertEquals("Transfer of $10 to account #2", getLastMessage(acct));
    assertEquals("Transfer of $10 received from account #1", getLastMessage(targ));
  }

  @Test
  void canGeneratePinNumbers() {
    int result = Account.generatePin(Account.MAX_ACCTNUM_LENGTH);
    assertNotNull(result);
    assertTrue(result < Account.MAX_ACCTNUM_LENGTH);
  }
}
