package app.money;

import app.money.*;
import app.bankAccount.*;

public class Transaction {
  private Bank bank;
  private Money accountFunds;
  private Money fundsTo;

  public Transaction(Money accountFunds, Money fundsTo){
    this.bank = new Bank();
    this.accountFunds = accountFunds;
    this.fundsTo = fundsTo;
  }

  public static Transaction processPayment (BankAccount acct, double amount) {
    
    Money accountFunds = acct.getBalance();
    Money fundsTo = Money.dollar(amount);
    return new Transaction(accountFunds, fundsTo);
  }


  public Money withdrawFunds(String to){
    return bank.reduce(new Difference(this.accountFunds, this.fundsTo), to);

  }

  public Money depositFunds(String to) { 
    return bank.reduce(new Sum(this.accountFunds, this.fundsTo), to);
  }
}
