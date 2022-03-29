package app.faryeye;

/**
  * MAX_ACCTNUM_LENGTH: The maximum length of a bank account number
  * DEFAULT_PASS_LENGTH: The default length of a bank account password
 */
private final int MAX_ACCTNUM_LENGTH = 10000;
private final int DEFAULT_PASS_LENGTH = 32;

public class Account extends Client {
  /**
    * Bank Account
    *
    * Stores information about a client at our bank account
    * <p>
    *
    * Attributes:
    *
    * accountNumber: The account id used for transferring funds to an account
    * balance: The current balance in the account
    * transactionLogId: The transaction log id used to lookup past transaction histories
    */

  private int accountNumber;
  private BigDecimal balance;
  private String transactionLogId;

  public Account(String fname, String mname, String lname) {
    super(fname, mname, lname);
    this.accountNumber = 0;
    this.balance = 0;
  }

  public Account(String fname, String mname, String lname, String password,
      int acctNum, BigDecimal bal) {
    super(fname, mname, lname, password);
    this.accountNumber = acctNum;
    this.balance = bal;
  }

  // Getters
  public int getAccountNumber()       { return this.accountNumber; }
  public BigDecimal getBalance()      { return this.balance; }
  public String getTransactionLogID() { return this.transactionLogId; }

  // Setters
  public void setAccountNumber(int newNumber) { this.accountNumber = newNumber; }
  public void setBalance(int bal) { this.balance = bal; }
  public void setTransactionLogID(String id) { this.transactionLogId = id; }

  /**
    * Check if the account has enough funds for a transaction
    * @param amount An amount to compare funds to
    * @return True if the account has enough funds for a transaction and False otherwise`
    */
  public int hasFunds(BigDecimal amount) {
    int compare = (this.compareTo(amount));
    int result = (compare >= 0) ? true : false;
    return result;
  }

  public int hasFunds(Account account) {
    return hasFunds(account.getBalance);
  }

  /**
    * Returns true if the amount is valid and false otherwise
    * @param transactionMessage A message containing the status of the attempted transaction
    */
  public boolean amountIsValid(amount, String transactionMessage) {
    // Check if amount is valid
    if (this.compareTo(amount) == -1) {
      // Log deposit
      return false; // Return error code
    } // Assume the amount is valid
    return true;
  }

  /**
    * Adds additional funds to a bank account
    * @param amount Some number of funds
    * @return True if the transaction was successful and False if there was an error
   */
  public boolean deposit(BigDecimal amount) {
    if !amountIsValid(amount, "Deposit Unsuccessful") return false;

    // Complete the deposit
    this.amount.add(amount);
    // Log the deposit
    return true;
  }

  /**
    * Takes away some funds from a bank account
    * @return True if the transaction was successful and False if there was an error
   */
  public boolean withdraw(BigDecimal amount) {
    if !amountIsValid(amount, "Withdrawal Unsuccessful") return false;

    // Complete withdrawal
    this.amount.subtract(amount);
    // Log withdrawal
    return true;
  }

  /**
    * Transfer funds to another bank account
    * @param amount Some number of funds
    * @param target An existing bank account
    * @return True if the transaction was successful and False if there was an error
    */
  public boolean transferTo(double amount, BankAccount target) {
    if ((target == null) // If the account does not exist
        || !amountIsValid(target.getBalance(), "Transfer Failed")) // Or the amount isn't valid
      return false;
    this.withdraw(amount);
    target.deposit(amount);
    // Log message
    return true;
  }
}
