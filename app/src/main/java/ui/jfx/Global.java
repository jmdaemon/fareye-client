package ui.jfx;

import fareye.Account;

//static Account acct;

//private static Global single_instance = null;
public class Global {
  // Account Singleton shared across the user interface
  private static Account acct = null;

  //Account get() {
    //return acct;
  //}

  private Global() {
    acct = new Account("","","");
  }

  public static Account getAcct() {
    if (acct == null)
      acct = new Account("", "", "");

    return acct;
    }
}
