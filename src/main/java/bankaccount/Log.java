package app.utils.log; 

import static app.utils.csv.CSV.*;
import app.bankAccount.*;

import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log implements Delims {
  //private static final String filepath = "./transaction_history.csv";

  public Log() { }

  public static String genTimeStamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd.HH.mm.sss");
    Date date = new Date();  
    return (formatter.format(date));
  }

  public static void logAppend(String msg, String filepath) {
    try { 
      writeToFile(msg + "\t\n", filepath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void initLog(String initMSG, String filepath) {
    try { 
      initializeLog(genTimeStamp() + "\t" + initMSG + "\t\n", filepath);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void logTo(String msg, BankAccount acct, String filepath) {
    acct.getLog().logAppend(genTimeStamp() + "\t" + msg, filepath);
  }

  public static void logMessage(String msg, String filepath) { 
    logAppend(genTimeStamp() + "\t" + msg, filepath);
  }

  public static void logMessage(String msg, double amount, String filepath) {
    logAppend(genTimeStamp() + "\t" + msg + "$\t" + "[$" + amount + "]", filepath);
  }

  public static String composeMsg(String transferMsg,double amount, String msg, BankAccount acct) {
    return ("\t" + transferMsg + " " + "[$" + amount + " " + msg + " " + acct.getAcctNum() + "]");
  }

  public static void logMessage(BankAccount sender, BankAccount receiver, double amount, String filepath) {
    logTo(composeMsg("Transfer", amount, "to account", receiver), sender, filepath);
    logTo(composeMsg("Transfer", amount, "received from account", sender), receiver, filepath);
  }

  public static void logMessage(BankAccount receiver, double amount, String filepath) {
    logTo(composeMsg("Transfer Failed", amount, "to account", receiver), receiver, filepath); 
  }
}
