package ui.jfx;

// Standard Library
import java.math.BigDecimal;

// Imports
import fareye.Account;

// JavaFX
import javafx.scene.image.Image;
import javafx.beans.property.StringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
  * Observable Bank Account
  *
  * This extends our original bank account class to contain observable properties
  * for use in JavaFX bindings.
  **/
public class ObservableAccount extends Account {
  // Observable Properties
  private StringProperty pin      = new SimpleStringProperty();
  private StringProperty balance  = new SimpleStringProperty();
  private StringProperty fname    = new SimpleStringProperty();
  private StringProperty mname    = new SimpleStringProperty();
  private StringProperty lname    = new SimpleStringProperty();

  private StringProperty pass     = new SimpleStringProperty();
  // TODO: Potentially add transactions history properties here to consolidate functionality

  private ObjectProperty<Image> avatar  = new SimpleObjectProperty<Image>();
  private StringProperty country  = new SimpleStringProperty();
  private StringProperty currency  = new SimpleStringProperty();

  // Pass through constructors
  public ObservableAccount(String fname, String mname, String lname) {
    super(fname, mname, lname);
    setFirstName(fname);
    setMiddleName(mname);
    setLastName(lname);
    setPassword("");
    setPin(0);
    setBalance(BigDecimal.valueOf(0));
  }

  public ObservableAccount(String fname, String mname, String lname, String password,
      int acctNum, BigDecimal bal) {
    super(fname, mname, lname, password, acctNum, bal);
    setFirstName(fname);
    setMiddleName(mname);
    setLastName(lname);
    setPin(acctNum);
    setPassword(password);
    setBalance(bal);
  }

  // Update observable properties on account changes

  // Getters
  public StringProperty getPinProperty() { return pin; }
  public StringProperty getBalanceProperty() { return balance; }
  public StringProperty getFNameProperty() { return fname; }
  public StringProperty getMNameProperty() { return mname; }
  public StringProperty getLNameProperty() { return lname; }
  public StringProperty getPassProperty() { return pass; }

  // External properties
  public ObjectProperty<Image> getAvatarProperty() { return avatar; }

  public StringProperty getCountryProperty() { return country; }
  public StringProperty getCurrencyProperty() { return currency; }

  @Override
  public void setPin(int pin) {
    super.setPin(pin);
    this.pin.set(String.valueOf(pin));
  }

  @Override
  public void setBalance(BigDecimal bal) {
    super.setBalance(bal);
    this.balance.set(bal.toString());
  }

  @Override
  public void setFirstName(String fname) {
    super.setFirstName(fname);
    this.fname.set(fname);
  }

  @Override
  public void setMiddleName(String mname) {
    super.setMiddleName(mname);
    this.mname.set(mname);
  }

  @Override
  public void setLastName(String lname) {
    super.setLastName(lname);
    this.lname.set(lname);
  }

  @Override
  public void setPassword(String pass) {
    super.setPassword(pass);
    this.pass.set(pass);
  }
}
