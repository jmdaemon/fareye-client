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
    return amount.equals(money.amount)
      && currency().equals(money.currency());
  }

  public boolean greaterThan(double amount) {
    Money money = Money.dollar(amount);
    int result = this.amount.compareTo(money.amount);
    switch(result) {
      case -1: return false;
      case  0: return false;
      case  1: return true;
      default: return false;
    }
  }

  public boolean lessThan(Money money) {
    int result = this.amount.compareTo(money.amount);
    switch(result) {
      case -1: return true;
      case  0: return false;
      case  1: return false;
      default: return false;
    }
  }

  public boolean equalsTo(double amount) {
    Money money = Money.dollar(amount); 
    int result = this.amount.compareTo(money.amount);
    switch(result) {
      case -1: return false;
      case  0: return true;
      case  1: return false;
      default: return false;
    }
  }

  public static Money dollar(int amount) {
    return new Money(amount, "USD");
  }

  public static Money dollar(double amount) {
    return new Money(toBigDecimal(amount), "USD");
  }

  public static Money franc(int amount) {
    return new Money(amount, "CHF");
  }

  public static Money franc(double amount) {
    return new Money(toBigDecimal(amount), "CHF"); 
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
  
  public Expression minus(Expression subtrahend) {
    return new Difference(this, subtrahend);
  }

  public Money reduce(Bank bank, String to) {
    return new Money(this.amount.divide(bank.rate(currency, to)), to);
  } 
}
