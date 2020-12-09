package app.money;

import static app.money.convert.Convert.*;
import java.math.BigDecimal;
import java.lang.Integer;
import java.util.HashMap;
//import java.util.Hashtable;

public class Bank {
  private HashMap<Pair, BigDecimal> rates = new HashMap<Pair, BigDecimal>();
  //private Hashtable<Pair, BigDecimal> rates = new Hashtable<Pair, BigDecimal>();

  public Money reduce(Expression source, String to) {
    return source.reduce(this, to);
  }
  
  public BigDecimal rate(String from, String to) {
    if (from.equals(to)) return toBigDecimal(1);
    //Integer rate = (Integer) rates.get(new Pair(from, to));
    //return toBigDecimal(rate.intValue());
    //String keyValue = rates.get(new Pair(from, to));
    //BigDecimal rate = toBigDecimal(keyValue);
    //BigDecimal storedRate = rates.get(new Pair(from, to));
    //BigDecimal rate = (BigDecimal) rates.get(new Pair(from, to));
    //BigDecimal rate = new BigDecimal(storedRate.toPlainString());
    //BigDecimal rate = new BigDecimal(storedRate);
    //System.out.println(rates.get(new Pair(from, to)));
    //System.out.printf("%.2f", rate);
    BigDecimal rate = rates.get(new Pair(from, to));
    return rate; 
    //Integer rate = (Integer) rates.get(new Pair(from, to));
    //return rate.intValue();
  }

  public void addRate(String from, String to, int rate) {
    //rates.put(new Pair(from, to), new Integer(rate));
    //BigDecimal bdRate = new BigDecimal(rate);
    BigDecimal bdRate = BigDecimal.valueOf(rate);
    rates.put(new Pair(from, to), bdRate);
  }
}
