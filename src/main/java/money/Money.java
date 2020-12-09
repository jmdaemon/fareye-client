package app.money;

import static app.money.convert.Convert.*;

import java.math.BigDecimal;
import java.math.BigInteger; 
import java.math.MathContext; 
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Money extends Expression {
  protected BigDecimal amount;
  protected String currency;

  public Money(int amount, String currency) {
    this.amount = toBigDecimal(amount);
    this.currency = currency;
  }

  public Money(double amount, String currency) {
    this.amount = toBigDecimal(amount);
    this.currency = currency;
  }

  public Money(BigDecimal amount, String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public String currency() {
    return this.currency;
  } 

  public String toString() {
    return amount + "" + currency; 
  }

  public boolean equals(Object object) {
    Money money = (Money) object;
    //return amount == money.amount
    return amount.equals(money.amount)
      && currency().equals(money.currency());
  }

  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  public Expression times(int multiplier) {
    BigDecimal mult = toBigDecimal(multiplier); 
    BigDecimal product = this.amount.multiply(mult);
    Expression result = new Money(product, currency);
    return result;
  } 

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Money reduce(Bank bank, String to) {
    return new Money(this.amount.divide(bank.rate(currency, to)), to);
  } 
}
