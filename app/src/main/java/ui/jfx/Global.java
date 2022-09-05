package ui.jfx;

// Third Party Libraries
// Logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Imports
import fareye.Account;

// Standard Library
import java.math.BigDecimal;
import java.text.NumberFormat;

public class Global {
  // Singletons
  private static Account acct = null;
  private static Logger logger = null;

  private Global() {
    acct = new Account("","","");

    Logger logger = LoggerFactory.getLogger("app");
  }

  public static Account getAcct() {
    if (acct == null) acct = new Account("", "", "");
    return acct;
  }

  public static Logger getLogger() {
    if (acct == null) logger = LoggerFactory.getLogger("app");
    return logger;
  }

  public static String currencyFormat(BigDecimal n) {
      return NumberFormat.getCurrencyInstance().format(n);
  }
}
