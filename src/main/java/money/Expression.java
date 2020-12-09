package app.money;

import java.math.BigDecimal;

public abstract class Expression {
  public abstract Money reduce(Bank bank, String to);
  //public Expression plus (Expression addend) { return null; }
  public abstract Expression times (int mult);

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

}
