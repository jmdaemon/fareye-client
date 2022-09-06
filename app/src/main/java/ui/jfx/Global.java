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
import java.io.IOException;

// JavaFX
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class Global {
  // Singletons
  private static Account acct = null;
  private static Logger logger = null;

  public Global() {
    if (acct == null) acct = new Account("","","");
    if (logger == null) logger = LoggerFactory.getLogger("app");
  }

  public static Account getAcct() {
    if (acct == null) acct = new Account("", "", "");
    return acct;
  }

  public static Logger getLogger() {
    if (logger == null) logger = LoggerFactory.getLogger("app");
    return logger;
  }

  public static String currencyFormat(BigDecimal n) {
    return NumberFormat.getCurrencyInstance().format(n);
  }

  public Pane loadFXML(String fxmlPath) {
    var loader = new FXMLLoader(getClass().getResource(fxmlPath));
    Pane pane = null;
    try {
        pane = (Pane) loader.load();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return pane;
  }
}
