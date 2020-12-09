package app.money;

import static app.money.convert.Convert.*;

import java.math.BigDecimal;
import java.math.BigInteger; 
import java.math.MathContext; 
//import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.math.RoundingMode;

//public class Money implements Expression {
public class Money extends Expression {
  //protected BigDecimal amount = BigDecimal.ZERO;
  protected BigDecimal amount;
  protected String currency;

  public Money(int amount, String currency) {
    //this.amount = toBigDecimal(amount);
    //this.amount = new BigDecimal(amount);
    this.amount = BigDecimal.valueOf(amount);
    this.currency = currency;
    System.out.println("Running Money(int, String):");
    //System.out.println("Result of amount:" + NumberFormat.getCurrencyInstance().format(this.amount));
    //System.out.println("Result of amount:" + this.amount.setScale(2, RoundingMode.HALF_UP));
    System.out.println("Result of amount:" + this.amount.setScale(0, RoundingMode.UP));
  }

  public Money(double amount, String currency) {
    this.amount = toBigDecimal(amount);
    this.currency = currency;
  }

  public Money(BigDecimal amount, String currency) {
    //String newAmount = new DecimalFormat("#0.000000").format(amount);
    //this.amount = amount;

    //String newAmount = new DecimalFormat("#0.00").format(amount);
    //String newAmount = new DecimalFormat("#0.00").format(amount);
    //String newValue = String.format("%.0f", );
    //int i = Integer.parseInt(newAmount); 
    //BigDecimal rounded = bd.round(new MathContext(1, RoundingMode.HALF_EVEN));
    //int i = Integer.parseInt(newValue);
    //BigInteger newAmount = (amount.setScale(1, RoundingMode.HALF_UP)).toBigInteger();
    //this.amount = new BigDecimal(newAmount);
    //this.amount = new BigDecimal(i);
    //BigDecimal newRoundedAmt = amount.round(new MathContext(1, RoundingMode.HALF_UP));
    //BigDecimal newRoundedAmt = amount.round(new MathContext(1, RoundingMode.HALF_EVEN));
    //BigDecimal newRoundedAmt = amount.setScale(1, RoundingMode.HALF_UP);
    //BigDecimal newRoundedAmt = amount.setScale(0, RoundingMode.CEILING);
    //BigDecimal newRoundedAmt = amount.setScale(0, new MathContext(2, RoundingMode.HALF_UP));
    //BigDecimal newAmount = newRoundedAmt.intValue();
    //this.amount = newRoundedAmt;
    //this.amount = new BigDecimal(newRoundedAmt.intValue());
    //MathContext roundTo = new MathContext(2, RoundingMode.HALF_UP);
    //String roundOff = new DecimalFormat("#0.00").format(amount);
    //this.amount = new BigDecimal(roundOff, roundTo);
    //this.amount = BigDecimal.valueOf(newRoundedAmt.intValue());
    //this.amount = new BigDecimal(newAmount);
    this.amount = amount;
    this.currency = currency;
  }

  public String currency() {
    return this.currency;
  } 

  @Override
  public String toString() {
    //DecimalFormat df = new DecimalFormat("#");
    //String amt = df.format(amount);
    //this.amount = 
    return amount + "" + currency;
    //return "" + amt + "" + currency;
    //return "" + this.amount.intValue() + "" + currency;
    //int value = this.amount.intValue();
    //return value + "" + currency;
    //return currency;
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
    //BigDecimal mult = toBigDecimal(multiplier);
    //BigDecimal result = this.amount.multiply(new BigDecimal(multiplier));
    //return new Money(result, currency);
    BigDecimal mult = toBigDecimal(multiplier); 
    BigDecimal product = this.amount.multiply(mult);
    Expression result = new Money(product, currency);
    //return new Money (result, currency);
    return result;
  } 

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Money reduce(Bank bank, String to) {
    //BigDecimal rate = toBigDecimal(bank.rate(currency, to));
    BigDecimal rate = bank.rate(currency, to);
    //BigDecimal newAmount = this.amount.divide(rate, 2);
    //System.out.println("Result of bank.rate: " + NumberFormat.getCurrencyInstance().format(rate));
    //System.out.println("Result of amount:" + rate.setScale(2, RoundingMode.HALF_UP));
    //System.out.println("Result of new amount:" + NumberFormat.getCurrencyInstance().format(newAmount));
    //System.out.println("Result of amount:" + newAmount.setScale(2, RoundingMode.HALF_UP));
    //return new Money(amount.divide(rate), to);
    //return new Money(newAmount, to);
    return new Money(this.amount.divide(rate), to);
  } 
}
