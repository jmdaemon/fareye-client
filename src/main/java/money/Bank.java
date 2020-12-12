package app.money;

import static app.money.convert.Convert.*;
import java.math.BigDecimal;
import java.util.HashMap;

public class Bank {
  private HashMap<Pair, BigDecimal> rates = new HashMap<Pair, BigDecimal>();

  public Money reduce(Expression source, String to) {
    return source.reduce(this, to);
  }
  
  public BigDecimal rate(String from, String to) {
    if (from.equals(to)) return toBigDecimal(1);
    BigDecimal rate = rates.get(new Pair(from, to));
    return rate; 
  }

  public void addRate(String from, String to, int rate) {
    BigDecimal bdRate = BigDecimal.valueOf(rate);
    rates.put(new Pair(from, to), bdRate);
  }
}
