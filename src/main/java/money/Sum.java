package app.money;

import static app.money.convert.Convert.*;
import java.math.BigDecimal;

//public class Sum implements Expression {
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
    //BigDecimal mult = toBigDecimal(mult);
    return new Sum(augend.times(mult), addend.times(mult));
  }

  public Money reduce(Bank bank, String to) {
    //BigDecimal amount = toBigDecimal(augend.reduce(bank, to).amount.add(addend.reduce(bank, to).amount));
    //BigDecimal amount = augend.reduce(bank, to).amount.add(addend.reduce(bank, to).amount);
    //BigDecimal newAugend = augend.reduce(bank, to); 
    //BigDecimal newAddend = addend.reduce(bank, to);
    //Money reduceMoney = augend.reduce(bank, to).plus(addend.reduce(bank, to));
    //BigDecimal amount = newAugend.add(newAddend);
    //int amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount;
    BigDecimal amount = augend.reduce(bank, to).amount.add( addend.reduce(bank, to).amount );
    return new Money(amount, to);
  }
}
