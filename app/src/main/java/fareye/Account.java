package fareye;

// Standard Library
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

public class Account extends Client {
  /**
    * Bank Account
    *
    * Stores information about a client at our bank account
    * <p>
    *
    * Attributes:
    *
    * pin: The account id used for transferring funds to an account
    * balance: The current balance in the account
    * transactionLogId: The transaction log id used to lookup past transaction histories
    */

  /**
    * MAX_ACCTNUM_LENGTH: The maximum length of a bank account number
    * DEFAULT_PASS_LENGTH: The default length of a bank account password
   */
  private static final int MAX_ACCTNUM_LENGTH = 10000;
  private static final int DEFAULT_PASS_LENGTH = 32;

  private int pin;
  private BigDecimal balance;
  private String transactionLogId;
  private List<String> transactions;

  public Account(String fname, String mname, String lname) {
    super(fname, mname, lname, generatePassword(DEFAULT_PASS_LENGTH));
    this.pin = 0;
    this.balance = new BigDecimal(0);
    this.transactions = new ArrayList<String>();
    transactions.add("New Bank Account Created");
  }

  public Account(String fname, String mname, String lname, String password,
      int acctNum, BigDecimal bal) {
    super(fname, mname, lname, password);
    this.pin = acctNum;
    this.balance = bal;
    this.transactions = new ArrayList<String>();
    transactions.add("New Bank Account Created");
  }

  // Getters
  public int getPin()                   { return this.pin; }
  public BigDecimal getBalance()        { return this.balance; }
  public String getTransactionLogID()   { return this.transactionLogId; }
  public List<String> getTransactions() { return this.transactions; }

  // Setters
  public void setPin(int pin)                     { this.pin = pin; }
  public void setBalance(BigDecimal bal)          { this.balance = bal; }
  public void setTransactionLogID(String id)      { this.transactionLogId = id; }
  public void setTransactions(List<String> msgs)  { this.transactions = msgs; }

  private void logMsg(String msg) { this.transactions.add(msg); }

  /**
    * Check if the account has enough funds for a transaction
    * @param amount An amount to compare funds to
    * @return True if the account has enough funds for a transaction and False otherwise`
    */
  public boolean hasFunds(BigDecimal amount) {
    int compare = (this.getBalance().compareTo(amount));
    var result = (compare >= 0) ? true : false;
    return result;
  }

  public boolean hasFunds(Account account) {
    return hasFunds(account.getBalance());
  }

  /**
    * Returns true if the amount is valid and false otherwise
    * A valid amount is one that is greater than zero (unsigned)
    * @param transactionMessage A message containing the status of the attempted transaction
    */

  public boolean amountIsNotValid(BigDecimal amount, String transactionMessage) {
    if (!hasFunds(amount)) {
      logMsg(transactionMessage);
      return true;
    }
    return false;
  }

  /**
    * Adds additional funds to a bank account
    * @param amount Some number of funds
    * @return True if the transaction was successful and False if there was an error
   */
  public boolean deposit(BigDecimal amount) {
    if (!(amount.compareTo(BigDecimal.valueOf(0)) > 0)) {
      logMsg("Deposit Unsuccessful");
      return false;
    }

    setBalance(this.getBalance().add(amount));
    logMsg("Deposit Successful");
    return true;
  }

  /**
    * Takes away some funds from a bank account
    * @return True if the transaction was successful and False if there was an error
   */
  public boolean withdraw(BigDecimal amount) {
    if (amountIsNotValid(amount, "Withdrawal Unsuccessful")) return false;

    setBalance(this.getBalance().subtract(amount));
    logMsg("Withdrawal Successful");
    return true;
  }

  /**
    * Transfer funds to another bank account
    * @param amount Some number of funds
    * @param target An existing bank account
    * @return True if the transaction was successful and False if there was an error
    */
  public boolean transferTo(BigDecimal amount, Account target) {
    // If the target account doesn't exist or if we don't have money to transfer
    if ((target == null) || !hasFunds(this)) {
      logMsg("Transfer Failed");
      return false;
    }
    this.withdraw(amount);
    target.deposit(amount);
    this.logMsg("Transfer of $" + amount + " to account #" + target.getPin());
    target.logMsg("Transfer of $" + amount + " received from account #" + this.getPin());
    return true;
  }

  public boolean changePassword(String oldPass, String newPass) {
    if (!this.checkPassword(oldPass)) {
      logMsg("Password Reset Failed");
      return false;
    }
    this.setPassword(newPass);
    logMsg("Password Successfully Changed");
    return true;
  }
}
