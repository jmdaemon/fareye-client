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

import java.util.ArrayList; 
import java.util.Collection;
import java.io.BufferedReader; 
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
import java.io.FileReader;

interface Delims {
  final String TAB_DELIM = "\\t+";
  final String NEWLINE_DELIM = "\\r?\\n";
  final String COMMA_DELIM = ", ";
}

public class Log implements Delims {
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
    String result = parseLog(parseLog(matches, NEWLINE_DELIM), TAB_DELIM);
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

  public <T> List<T> flattenListOfListsStream(List<List<T>> list) { 
    return list.stream() 
      .flatMap(Collection::stream) 
      .collect(Collectors.toList());    
  }

  public String readFile(String filepath) {
    if (!fileExists(filepath)) {
      return "";
    } 

    List<List<String>> records = new ArrayList<>(); 
    try (BufferedReader br = new BufferedReader(new FileReader(filepath))) { 
      String line; 
      while ((line = br.readLine()) != null) { 
        String[] values = line.split(NEWLINE_DELIM); 
        records.add(Arrays.asList(values)); 
      } 
    } catch (Exception e) {
      e.printStackTrace();
    }
    List<String> data = flattenListOfListsStream(records);
    String result = data.stream().collect(Collectors.joining(("\n")));
    return result;
  }

  public String searchCSV(String msg, String file) {
    Pattern pattern = Pattern.compile(msg);
    Matcher matcher = pattern.matcher(file);
    
    StringBuilder results = new StringBuilder();
    if (matcher.find()) {
      results.append(matcher.group());
    }
    return results.toString();
  }

  public String searchFileFor(String msg, String file) {
    String matches = searchCSV(msg, file);
    String result = parseLog(parseLog(matches, NEWLINE_DELIM), TAB_DELIM);
    return result;
  }
  public StringBuffer toStringBuffer() { return this.log; }
}
