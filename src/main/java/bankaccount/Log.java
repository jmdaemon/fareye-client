package app.log; 

import app.bankAccount.*;

import java.util.Arrays;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Log {
  private StringBuffer log;

  public Log() { 
    this.log = new StringBuffer();
  }

  private String genTimeStamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd.HH.mm.sss");
    Date date = new Date();  
    return (formatter.format(date));
  }

  public void logAppend(String msg) {
    this.log.append(msg);
  }

  public void logTo(String msg, BankAccount acct) {
    Log acctLog = acct.getLog();
    //System.out.println(msg);
    //System.out.println(acctLog.toStringBuffer().toString());
    //acct.getLog().toStringBuffer().append(msg);
    acctLog.logAppend(msg);
  }

  public void logMessage(String msg) { 
    String timeStamp = genTimeStamp();
    logAppend(timeStamp + "\t" + msg + "\t\n");
  }

  public void logMessage(String msg, double amount) {
    String timeStamp = genTimeStamp();
    logAppend(timeStamp + "\t" + msg + "\t" + "[$" + amount + "]\n");
  }

  public void logMessage(BankAccount sender, BankAccount receiver, double amount) { // sender is the current bankAccount
    String timeStamp = genTimeStamp();
    String senderMsg = ("[$" + amount + " to account " + receiver.getAcctNum() + "]\t\n"); 
    String receiverMsg = ("[$" + amount + " received from account " + sender.getAcctNum() + "]\t\n");
    logTo( (timeStamp + "\tTransfer " + senderMsg), sender);
    logTo( (timeStamp + "\tTransfer " + receiverMsg), receiver);
  }

  public void logMessage(BankAccount receiver, double amount) {
    String timeStamp = genTimeStamp();
    String failMsg = ("[$" + amount + " to account " + receiver.getAcctNum() + "]\n"); 
    logAppend(timeStamp + "\tTransfer Failed\t" + failMsg); 
  }

  public String search(String msg) {
    Pattern pattern = Pattern.compile(msg);
    Matcher matcher = pattern.matcher(this.log.toString());
    
    StringBuilder results = new StringBuilder();
    if (matcher.find()) {
      results.append(matcher.group());
    }
    return results.toString();
  }
  
  public String parseLog(int index) {
    String[] parsedLog = this.log.toString().split("\\t+");
    String result = parsedLog[index];
    return result;
  }

  public String parseLog(String matches, String delim) {
    String[] parsedLog = matches.split(delim);
    StringBuilder result = new StringBuilder(); 
    for (String value : parsedLog) { 
      result.append(value);
    }
    return result.toString();
  }
 
  public String searchFor(String msg) {
    String matches = search(msg);
    String line = parseLog(matches, "\\r?\\n");
    String result = parseLog(matches, "\\t+");

    //System.out.println("First line " + line);
    //System.out.println("Final result " + result);
    return result;
  }

  public StringBuffer toStringBuffer() { return this.log; }
}
