package app.utils.csv;

import static app.utils.log.Log.*;
import app.utils.log.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

  public static String searchLog(String keyword, String filepath) {
    List<Line> entries = grepFile(keyword, filepath);
    String result = cutTimeStamp(entries.get(0).getContent());
    return result;
  }

  public static String[] searchLogAll(String keyword, String filepath) {
    List<Line> entries = grepFile(keyword, filepath);
    String[] lines = new String[entries.size()];
    for (int i = 0; i < entries.size(); i++) {
      lines[i] = entries.get(i).getContent();
    }
    return lines;
  }

  public static String msgToCSV(String data) {
    String convertToCSV = data.replaceAll(TAB_DELIM, ", ");
    String result = convertToCSV.replaceAll("\\$,", "");
    return result;
  }

  public static void writeToFile(String msg, String filepath) throws IOException {
    if (!fileExists(filepath)) {
      return;
    }
    String entry = msgToCSV(msg);
    BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, true));
    writer.append(entry);
    writer.close();
  }

  public static void initializeLog(String initMSG, String filepath) throws IOException {
    String entry = msgToCSV(initMSG);
    BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
    writer.write(entry);
    writer.close();
  }
}
