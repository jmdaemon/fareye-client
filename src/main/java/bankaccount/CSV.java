package app.log.csv;

import app.log.*;

import java.util.ArrayList; 
import java.util.Collection;
import java.io.BufferedWriter;
import java.io.BufferedReader; 
import java.io.FileWriter;
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

  public static boolean fileExists(String filepath) {
    File f = new File(filepath);
    if (f.exists() && !f.isDirectory()) { 
      return true; 
    } 
    return false;
  }
 
  public static List<Line> grepFile(String keyword, String filepath) {
    File file = new File(filepath);
    List<Line> results = Unix4j.grep(keyword, file).toLineList(); 
    return results;
  }

  public static String cutTimeStamp(String line) { 
    String[] slicedLine = line.split(COMMA_DELIM);
    return slicedLine[1];
  }

  public static String searchLog(String keyword) {
    List<Line> entries = grepFile(keyword, "./transaction_history.csv");
    Line entry = entries.get(0);
    String line  = entry.getContent();
    String result = cutTimeStamp(line);
    return result;
  }
  
  public static String[] searchLogAll(String keyword) {
    List<Line> entries = grepFile(keyword, "./transaction_history.csv");
    String[] lines = new String[entries.size()];
    for (int i = 0; i < entries.size(); i++) {
      lines[i] = entries.get(i).getContent();
    }
    return lines;
  }

  public static void writeToFile(String msg, String filepath) throws IOException {
    if (!fileExists(filepath)) {
      return;
    }
    BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
    writer.append(msg);
    writer.close();
  }
}
