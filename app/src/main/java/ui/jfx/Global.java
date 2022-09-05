package ui.jfx;

// Third Party Libraries

// Logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import ch.qos.logback.classic.LoggerContext;
//import ch.qos.logback.core.util.StatusPrinter;

import fareye.Account;

//static Account acct;

//private static Global single_instance = null;
public class Global {
  // Account Singleton shared across the user interface
  private static Account acct = null;
  private static Logger logger = null;

  //Account get() {
    //return acct;
  //}

  private Global() {
    acct = new Account("","","");

    //Logger logger = LoggerFactory.getLogger("logback.example.App");
    Logger logger = LoggerFactory.getLogger("app");
    //logger.debug(greeting);
    //LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    //StatusPrinter.print(lc);

  }

  public static Account getAcct() {
    if (acct == null)
      acct = new Account("", "", "");

    return acct;
    }

  public static Logger getLogger() {
    if (acct == null)
      logger = LoggerFactory.getLogger("app");

    return logger;
  }
}
