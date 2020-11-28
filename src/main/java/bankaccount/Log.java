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
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.stream.*;
import java.util.List;


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
    acct.getLog().logAppend(msg);
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
    String senderMsg = ("[$" + amount + " to account " + receiver.getAcctNum() + "]\t\n"); 
    String receiverMsg = ("[$" + amount + " received from account " + sender.getAcctNum() + "]\t\n");
    logTo( (timeStamp + "\tTransfer " + senderMsg), sender);
    logTo( (timeStamp + "\tTransfer " + receiverMsg), receiver);
  }

  public void logMessage(BankAccount receiver, double amount) {
    String timeStamp = genTimeStamp();
    String failMsg = ("[$" + amount + " to account " + receiver.getAcctNum() + "]\n"); 
    receiver.getLog().logAppend(timeStamp + "\tTransfer Failed\t" + failMsg); 
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
    String result = parseLog(parseLog(matches, "\\r?\\n"), "\\t+");
    return result;
  }

  public String[] splitLog(String delim) {
    String[] result = this.log.toString().split(delim);
    return result;
  }

  public String[] splitLog(String log, String delim) {
    String[] result = log.split(delim);
    return result;
  }
  
  public boolean fileExists(String filepath) {
    File f = new File(filepath);
    if (f.exists() && !f.isDirectory()) { 
      return true; 
    } 
    return false;
  }

  public String escapeChars(String data) {
    String result = data.replaceAll("\\R", " ");
    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
        data = data.replace("\"", "\"\"");
        result = "\"" + data + "\"";
    }
    return result;
  }

  public String stringToCSV(String logEntry) {
    String[] splitLog = splitLog(logEntry,"\\t+"); 
    return Stream.of(splitLog)
      .map(this::escapeChars)
      .collect(Collectors.joining(", "));
  }

  public void writeToFile(String filepath) {
    if (fileExists(filepath)) {
      return;
    } 

    List<String> logData = Arrays.asList(splitLog("\\r?\\n"));
    File logCSV = new File(filepath);
    try (PrintWriter pw = new PrintWriter(logCSV)) {
      logData.stream()
        .map(this::stringToCSV) 
        .forEach(pw::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public String readFile(String filepath) {
    if (!fileExists(filepath)) {
      return "";
    } 
    String logData = "";
    try {
    Path path = Paths.get(getClass().getClassLoader().getResource(filepath).toURI());
         
    Stream<String> logCSV = Files.lines(path);
    logData = logCSV.collect(Collectors.joining("\n"));
    logCSV.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return logData;
    //StringBuilder result = new StringBuilder();
    //File logCSV = new File(filepath);
    //try (BufferedReader br = new BufferedReader(new FileReader(logCSV))) { 
      //String line;
      //while ((line = br.readLine()) != null) {
            //resultStringBuilder.append(line).append("\n");
        //}
    //} catch (Exception e) {
      //e.printStackTrace();
    //}
    //return result.toString();
  }
  public StringBuffer toStringBuffer() { return this.log; }
}
