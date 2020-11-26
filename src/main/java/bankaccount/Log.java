package app.log; 

import app.bankAccount.*;

import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
  private StringBuffer log = new StringBuffer();

  private String genTimeStamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd.HH.mm.sss");
    Date date = new Date();  
    return (formatter.format(date));
  }

  public void logAppend(String msg) {
    this.log.append(msg);
  }

  public void logTo(String msg, BankAccount acct) {
    Log log = acct.getLog();
    log.logAppend(msg);
  }

  public void logMessage(String msg) { 
    String timeStamp = genTimeStamp();
    logAppend(timeStamp + "\t" + msg + "\t\n");
  }

  public void logMessage(String msg, double amount) {
    String timeStamp = genTimeStamp();
    logAppend(timeStamp + "\t" + msg + "\t" + "[$" + amount + "]\n");
  }

  public void logMessage(BankAccount sender, BankAccount receiver, double amount) {
    String timeStamp = genTimeStamp();
    String senderMsg = ("[$" + amount + " to account " + receiver.getAcctNum() + "]\n"); 
    String receiverMsg = ("[$" + amount + " received from account " + sender.getAcctNum() + "]\n");
    logTo( (timeStamp + "\tTransfer " + senderMsg), sender);
    logTo( (timeStamp + "\tTransfer " + receiverMsg), receiver);
  }

  public void logMessage(BankAccount receiver, double amount) {
    String timeStamp = genTimeStamp();
    String failMsg = ("[$" + amount + " to account " + receiver.getAcctNum() + "]\n"); 
    logAppend(timeStamp + "\tTransfer Failed\t" + failMsg); 
  }

  public String parseLog(int index) {
    String[] parsedLog = this.log.toString().split("\\t+");
    String logRes = parsedLog[index];
    return logRes;
  }

  public StringBuffer toStringBuffer() { return this.log; }
}
