package app.connect;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.lang.StringBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.lang.InterruptedException;

public class Http extends Connection {

  public String get(String to, String params) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(to))
            .build();

    HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

    //System.out.println(response.body());
    return response.body();
  }

  public String post(String to, String params) {
    return "";
  }

  //public String getResponse(HttpURLConnection conn) throws IOException {
    //BufferedReader in = null;
    //StringBuilder response = null;
    //in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    //String input;

    //response = new StringBuilder();
    //while ((input = in.readLine()) != null) {
      //response.append(input);
    //} 
    //in.close();
    //return response.toString(); 
  //}

  //public void sendRequest(HttpURLConnection conn, String requestType, String params) throws ProtocolException, IOException {
    //conn.setRequestMethod(requestType);
    //conn.setDoOutput(true);
    //DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    //wr.writeBytes(params);
    //wr.flush();
    //wr.close();
  //}

	//public String get(String to, String params) throws IOException {
    //URL url = formRequest(to);
    //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //sendRequest(conn, "GET", params);
		//return getResponse(conn);
	//}
	
	//public String post(String to, String params) throws ProtocolException, IOException {
    //URL url = formRequest(to);
    //HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    //sendRequest(conn, "POST", params);
    //return getResponse(conn);
	//}
}
