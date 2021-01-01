package app.connect;

import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.net.MalformedURLException;
import java.lang.StringBuilder;
import java.util.HashMap;

import javax.net.ssl.SSLContext;

public class Https extends Connection {
  private int responseCode;

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

  //public void connectWithSSL() { 
    //URL url = new URL("https://certs.cac.washington.edu/CAtest/");
    //HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
    //urlConnection.setSSLSocketFactory(context.getSocketFactory());
    //InputStream in = urlConnection.getInputStream();
    //copyInputStreamToOutputStream(in, System.out); 
  //} 

  public String getResponseBody(HttpsURLConnection urlConnection) throws Exception { 
    InputStream is = urlConnection.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String input = null;
    StringBuilder response = new StringBuilder();
      while ((input = br.readLine()) != null) {
        response.append(input);
    } 
    responseCode = urlConnection.getResponseCode();
    return response.toString();
  }

  public String getWithSSL(String to, SSLContext context) throws Exception {
    URL url = new URL(to);
    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
    urlConnection.setSSLSocketFactory(context.getSocketFactory());
    return getResponseBody(urlConnection);
    } 

  public int getResponseCode() { return responseCode; }

}
