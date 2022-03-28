package app.connect;

import java.net.URL;
import java.io.*;
import javax.net.ssl.HttpsURLConnection;
import java.lang.StringBuilder;
import java.util.Map;
import java.util.HashMap;

import javax.net.ssl.SSLContext;

public class Https {
  private int responseCode;

  public int getResponseCode() { return responseCode; }

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

  public HttpsURLConnection createSecureConnection(String to, SSLContext context) throws Exception {
    URL url = new URL(to);
    HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
    urlConnection.setSSLSocketFactory(context.getSocketFactory());
    return urlConnection;
  }

  public String getWithSSL(String to, SSLContext context) throws Exception {
    HttpsURLConnection urlConnection = createSecureConnection(to, context);
    urlConnection.setRequestMethod("GET");
    return getResponseBody(urlConnection);
  }

  public String postWithSSL(String to, SSLContext context) throws Exception {
    HttpsURLConnection urlConnection = createSecureConnection(to, context);
    urlConnection.setRequestMethod("POST");
    return getResponseBody(urlConnection);
  }

  public String sendPostCreds(String to, Map<String, String> params, SSLContext context) throws Exception {
    String urlParameters  = "username=" + params.get("username") +
      "&password=" + params.get("password") + "&balance=" + params.get("balance") +
      "&firstName=" + params.get("firstName") + "&middleName=" + params.get("middleName") + "&lastName=" + params.get("lastName");

    byte[] postData       = urlParameters.getBytes("UTF-8");
    int    postDataLength = postData.length;
    HttpsURLConnection urlConnection = createSecureConnection(to, context);
    urlConnection.setDoOutput(true);
    urlConnection.setInstanceFollowRedirects(false);
    urlConnection.setRequestMethod("GET");
    urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    urlConnection.setRequestProperty("charset", "utf-8");
    urlConnection.setRequestProperty("Content-Length", String.valueOf(postDataLength));
    urlConnection.setUseCaches(false);
    try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
      wr.write( postData );
    }
    return getResponseBody(urlConnection);

  }

}
