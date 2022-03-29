package app.utility;

import java.lang.StringBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtility {
  static final String TAB_DELIM = "\\t+";
  static final String CARRIAGE_RETURN_DELIM = "\\r?\\n";
  static final String NEWLINE_DELIM = "\n";
  static final String COMMA_DELIM = ", ";

  /**
    * Generates a time stamp formatted as YYYY.MM.DD.HH.mm.sss
   */
  public static String timestamp() {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.mm.dd.HH.mm.sss");
    Date date = new Date();
    return (formatter.format(date));
  }
}
