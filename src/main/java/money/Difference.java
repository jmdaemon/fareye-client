package app.money;

import static app.money.convert.Convert.*;

import java.math.BigDecimal;

public class Difference extends Expression {
  public Expression minuend;
  public Expression subtrahend;

  public Difference(Expression minuend, Expression subtrahend) {
    this.minuend = minuend;
    this.subtrahend = subtrahend;
  }

  public Expression minus(Expression subtrahend) {
    return new Difference(this, subtrahend);
  }

  public Expression times(int mult) {
    return new Difference(minuend.times(mult), subtrahend.times(mult));
  } 

  public Money reduce(Bank bank, String to) {
    BigDecimal amount = minuend.reduce(bank, to).amount.subtract( subtrahend.reduce(bank, to).amount );
    return new Money(amount, to);
  }

}
