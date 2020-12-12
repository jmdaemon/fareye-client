package app.money;

import java.math.BigDecimal;

public abstract class Expression {
  public abstract Money reduce(Bank bank, String to);
  public abstract Expression times (int multiplier);
  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Expression minus(Expression subtrahend) {
    return new Difference(this, subtrahend);
  }

}
