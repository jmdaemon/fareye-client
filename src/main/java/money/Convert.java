package app.money.convert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class Convert {
  public static BigDecimal toBigDecimal(double amount) { 
    //return new BigDecimal(amount, MathContext.DECIMAL64);
    return BigDecimal.valueOf(amount);
    //return new BigDecimal(amount);
  }

  public static BigDecimal toBigDecimal(int amount) {
    //return BigDecimal.valueOf(amount);
    //double d = amount;
    //DecimalFormat df = new DecimalFormat("#.00");
    //String value = df.format(d);
    ////return BigDecimal.valueOf(value);
    //return new BigDecimal(value);
    //return new BigDecimal(amount.toString());
    //return new BigDecimal(amount);
    return BigDecimal.valueOf(amount);
  }

  public static BigDecimal toBigDecimal(String amount) {
    return new BigDecimal(amount);
  }
}
