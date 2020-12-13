package app.connect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public abstract class Connection {
  //protected String url;

  URL formRequest(String site) {
    URL url = null;
    try {
      url = new URL(site);
    } catch (MalformedURLException e) {
      e.printStackTrace();
      System.exit(1);
    }
    return url;
  }

  //public abstract String get(String to, String params) throws IOException;
  //public abstract String post(String to, String params) throws IOException;
  //public abstract String get(String to, String params) throws Exception;
  public abstract String get(String to) throws Exception;
  //public abstract String post(String to, HashMap<String, String>) throws Exception;
  public abstract String post(String to, String params) throws Exception;
}
