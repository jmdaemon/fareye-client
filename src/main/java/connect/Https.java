package app.connect;

import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.lang.StringBuilder;
import java.util.HashMap;

public class Https extends Connection {

  boolean portIsNull(int port) {
    return (port != 0) ? true : false;
  } 
  public BufferedReader connectTo(String site) throws IOException { 
    URL url = formRequest(site);
    BufferedReader br = null;
    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    InputStream is = conn.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    br = new BufferedReader(isr);

    return br;
  }

  public String ping(String url) throws IOException {
      String input = null;
      StringBuilder response = new StringBuilder();
      BufferedReader br = connectTo(url);

        while ((input = br.readLine()) != null) {
          response.append(input);
      } 
      return response.toString();
  }

  public String get(String to) {
    return "";
  }

  public String post(String to, String params) {
    return "";
  }
  


}
