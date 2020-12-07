package app.log; 

import app.bankAccount.*;
import static app.log.csv.CSV.*;

import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements Delims {
  private static final String filepath = "./transaction_history.csv";

  public Log() { }

  public static String genTimeStamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd.HH.mm.sss");
    Date date = new Date();  
    return (formatter.format(date));
  }

  public void logAppend(String msg) {
    try { 
      writeToFile(msg + "\t\n", filepath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void initLog(String initMSG) {
    try { 
      initializeLog(genTimeStamp() + "\t" + initMSG + "\t\n");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logTo(String msg, BankAccount acct) {
    acct.getLog().logAppend(genTimeStamp() + "\t" + msg);
  }

  public void logMessage(String msg) { 
    logAppend(genTimeStamp() + "\t" + msg);
  }

  public void logMessage(String msg, double amount) {
    logAppend(genTimeStamp() + "\t" + msg + "$\t" + "[$" + amount + "]");
  }

  public String composeMsg(String transferMsg,double amount, String msg, BankAccount acct) {
    return ("\t" + transferMsg + " " + "[$" + amount + " " + msg + " " + acct.getAcctNum() + "]");
  }

  public void logMessage(BankAccount sender, BankAccount receiver, double amount) {
    logTo(composeMsg("Transfer", amount, "to account", receiver), sender);
    logTo(composeMsg("Transfer", amount, "received from account", sender), receiver);
  }

  public void logMessage(BankAccount receiver, double amount) {
    logTo(composeMsg("Transfer Failed", amount, "to account", receiver), receiver); 
  }
}
