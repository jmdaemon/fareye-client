package app.money.convert;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

public class Convert {
  public static BigDecimal toBigDecimal(double amount) { 
    return BigDecimal.valueOf(amount);
  }

  public static BigDecimal toBigDecimal(int amount) {
      return BigDecimal.valueOf(amount);
  }

  public static BigDecimal toBigDecimal(String amount) {
    return new BigDecimal(amount);
  }
}
