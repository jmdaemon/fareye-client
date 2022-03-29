package app.utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtility {
  static final String TAB_DELIM = "\\t+";
  static final String CARRIAGE_RETURN_DELIM = "\\r?\\n";
  static final String NEWLINE_DELIM = "\n";
  static final String COMMA_DELIM = ", ";

  /**
    * Generates a time stamp formatted as [YYYY.MM.DD.HH.mm.sss]
   */
  public static String timestamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("[yyyy.mm.dd.HH.mm.sss]");
    Date date = new Date();
    return (formatter.format(date));
  }

  public static void init(String msg) {
    // Initialize File
  }

  LogUtility(String msg, boolean logExists) {
    if (!logExists) { init(msg); }
  }

  /*
   * LogUtility Requirements:
   * - Parse a file line by line
   * - Search the entire file for matching substrings. (Fuzzy file content finding/Grep all).
   * - Append new entries to a file
   * - Format outputs to a file horizontally
   */

  /**
    * Reads a file into a string
    */
  public static String readFile(String path) throws IOException {
    return Files.readString(Path.of(path));
  }

  /**
    * Append an entry to a file
    */
  public static boolean append() {
    //try {
      //Files.write(Paths.get("myfile.txt"), "the text".getBytes(), StandardOpenOption.APPEND); }
    //catch (IOException e) {
      ////exception handling left as an exercise for the reader
    //}
    return false;
  }
}
