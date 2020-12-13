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

  //URL checkPort(String site, int port) {
    //URL url = null;
    //if (portIsNull(port)) {
      //url = new URL(site);
    //} else 
      //url = new URL(site + ":" + port);
    //return url;
  //}

    public BufferedReader connectTo(String site) {
    URL url = formRequest(site);
    BufferedReader br = null;
    try {
      HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
      InputStream is = conn.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      br = new BufferedReader(isr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return br;
  }

  public String get(String to) {
    return "";
  }

  //public String post(String to, HashMap<String, String>) {
    //return "";
  //}
  public String post(String to, String params) {
    return "";
  }
  

  public String ping(String url) {
      String input = null;
      StringBuilder response = new StringBuilder();
      BufferedReader br = connectTo(url);

      try {
        while ((input = br.readLine()) != null) {
          response.append(input);
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      return response.toString();
  }
}
