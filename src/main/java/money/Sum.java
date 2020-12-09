package app.money;

import static app.money.convert.Convert.*;
import java.math.BigDecimal;

public class Sum extends Expression {
  public Expression augend;
  public Expression addend;

  public Sum(Expression augend, Expression addend) {
    this.augend = augend;
    this.addend = addend;
  }

  public Expression plus(Expression addend) {
    return new Sum(this, addend);
  }

  public Expression times(int mult) {
    return new Sum(augend.times(mult), addend.times(mult));
  }

  public Money reduce(Bank bank, String to) {
    BigDecimal amount = augend.reduce(bank, to).amount.add( addend.reduce(bank, to).amount );
    return new Money(amount, to);
  }
}
