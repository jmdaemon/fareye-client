package app.log.csv;

import app.log.*;

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

import org.unix4j.*;
import org.unix4j.unix.Grep.*;
import org.unix4j.line.*;

public class CSV implements Delims {

  public static List<Line> grepFile(String keyword, String filepath) {
    File file = new File(filepath);
    List<Line> results = Unix4j.grep(keyword, file).toLineList(); 
    return results;
  }

  public static String cutTimeStamp(String line) { 
    String[] slicedLine = line.split(COMMA_DELIM);
    return slicedLine[1];
    //StringBuilder result = new StringBuilder(); 
    //for (String value : slicedEntry) { 
      //result.append(value);
    //}
    //return result.toString();
  }

  public static String searchLog(String keyword) {
    List<Line> entries = grepFile(keyword, "./transaction_history.csv");
    Line entry = entries.get(0);
    String line  = entry.getContent();
    String result = cutTimeStamp(line);
    return result;
    //String result = entry.toString();
    //System.out.println(result);
  }

  //public String search(String msg) {
    //Pattern pattern = Pattern.compile(msg);
    //Matcher matcher = pattern.matcher(this.log.toString());
    
    //StringBuilder results = new StringBuilder();
    //if (matcher.find()) {
      //results.append(matcher.group());
    //}
    //return results.toString();
  //}
  
  //public String parseLog(String matches, String delim) {
    //String[] parsedLog = matches.split(delim);
    //StringBuilder result = new StringBuilder(); 
    //for (String value : parsedLog) { 
      //result.append(value);
    //}
    //return result.toString();
  //}

  //public String searchFor(String msg) {
    //String matches = search(msg);
    //String result = parseLog(parseLog(matches, CARRIAGE_RETURN_DELIM), TAB_DELIM);
    //return result;
  //}

  //public String searchCSV(String msg, String file) {
    //Pattern pattern = Pattern.compile(msg);
    //Matcher matcher = pattern.matcher(file);
    
    //StringBuilder results = new StringBuilder();
    //if (matcher.find()) {
      //results.append(matcher.group());
    //}
    //return results.toString();
  //}

  //public String searchFileFor(String msg, String file) {
    //String matches = searchCSV(msg, file);
    //String result = parseLog(parseLog(matches, CARRIAGE_RETURN_DELIM), TAB_DELIM);
    //return result;
  //}

  //public String[] splitLog(String delim) {
    //String[] result = this.log.toString().split(delim);
    //return result;
  //}

  //public String[] splitLog(String log, String delim) {
    //String[] result = log.split(delim);
    //return result;
  //}
  
  //public boolean fileExists(String filepath) {
    //File f = new File(filepath);
    //if (f.exists() && !f.isDirectory()) { 
      //return true; 
    //} 
    //return false;
  //}

  //public String escapeChars(String data) {
    //String result = data.replaceAll("\\R", " ");
    //if (data.contains(",") || data.contains("\"") || data.contains("'")) {
        //data = data.replace("\"", "\"\"");
        //result = "\"" + data + "\"";
    //}
    //return result;
  //}

  //public String stringToCSV(String logEntry) {
    //String[] splitLog = splitLog(logEntry,"\\t+"); 
    //return Stream.of(splitLog)
      //.map(this::escapeChars)
      //.collect(Collectors.joining(", "));
  //}

  //public void writeToFile(String filepath) {
    //if (fileExists(filepath)) {
      //return;
    //} 

    //List<String> logData = Arrays.asList(splitLog("\\r?\\n"));
    //File logCSV = new File(filepath);
    //try (PrintWriter pw = new PrintWriter(logCSV)) {
      //logData.stream()
        //.map(this::stringToCSV) 
        //.forEach(pw::println);
    //} catch (Exception e) {
      //e.printStackTrace();
    //}
  //}

  //public <T> List<T> flattenListOfListsStream(List<List<T>> list) { 
    //return list.stream() 
      //.flatMap(Collection::stream) 
      //.collect(Collectors.toList());    
  //}

  //public String readFile(String filepath) {
    //if (!fileExists(filepath)) {
      //return "";
    //} 

    //List<List<String>> records = new ArrayList<>(); 
    //try (BufferedReader br = new BufferedReader(new FileReader(filepath))) { 
      //String line; 
      //while ((line = br.readLine()) != null) { 
        //String[] values = line.split(CARRIAGE_RETURN_DELIM); 
        //records.add(Arrays.asList(values)); 
      //} 
    //} catch (Exception e) {
      //e.printStackTrace();
    //}
    //List<String> data = flattenListOfListsStream(records);
    //String result = data.stream().collect(Collectors.joining((NEWLINE_DELIM)));
    //return result;
  //}

}
