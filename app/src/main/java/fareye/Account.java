package fareye;

import java.math.BigDecimal;

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

  public Account(String fname, String mname, String lname) {
    super(fname, mname, lname, generatePassword(DEFAULT_PASS_LENGTH));
    this.pin = 0;
    this.balance = new BigDecimal(0);
  }

  public Account(String fname, String mname, String lname, String password,
      int acctNum, BigDecimal bal) {
    super(fname, mname, lname, password);
    this.pin = acctNum;
    this.balance = bal;
  }

  // Getters
  public int getPin()                 { return this.pin; }
  public BigDecimal getBalance()      { return this.balance; }
  public String getTransactionLogID() { return this.transactionLogId; }

  // Setters
  public void setPin(int pin)               { this.pin = pin; }
  public void setBalance(BigDecimal bal)    { this.balance = bal; }
  public void setTransactionLogID(String id){ this.transactionLogId = id; }

  /**
    * Check if the account has enough funds for a transaction
    * @param amount An amount to compare funds to
    * @return True if the account has enough funds for a transaction and False otherwise`
    */
  public boolean hasFunds(BigDecimal amount) {
    int compare = (this.getBalance().compareTo(amount));
    boolean result = (compare >= 0) ? true : false;
    return result;
  }

  public boolean hasFunds(Account account) {
    return hasFunds(account.getBalance());
  }

  /**
    * Returns true if the amount is valid and false otherwise
    * @param transactionMessage A message containing the status of the attempted transaction
    */

  public boolean amountIsNotValid(BigDecimal amount, String transactionMessage) {
    // Check if amount is valid
    if (!hasFunds(amount)) {
      // Log deposit
      return true; // Return error code
    } // Assume the amount is valid
    return false;
  }

  /**
    * Adds additional funds to a bank account
    * @param amount Some number of funds
    * @return True if the transaction was successful and False if there was an error
   */
  public boolean deposit(BigDecimal amount) {
    //if (amountIsNotValid(amount, "Deposit Unsuccessful")) return false;

    // Complete the deposit
    BigDecimal newBalance = this.getBalance();
    newBalance = this.getBalance().add(amount);
    setBalance(newBalance);
    // Log the deposit
    return true;
  }

  /**
    * Takes away some funds from a bank account
    * @return True if the transaction was successful and False if there was an error
   */
  public boolean withdraw(BigDecimal amount) {
    if (amountIsNotValid(amount, "Withdrawal Unsuccessful")) return false;

    // Complete withdrawal
    this.getBalance().subtract(amount);
    // Log withdrawal
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
      // Log message "Transfer Failed"
      return false;
    }
    this.withdraw(amount);
    target.deposit(amount);
    // Log message of successful transaction
    return true;
  }
}
