package app.connect;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.MalformedURLException;
import java.lang.InterruptedException;

public class Http {
  private int responseCode;

  public int getResponseCode() { return responseCode; }

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

  public String get(String to) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(to))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    responseCode = response.statusCode();
    return response.body();
  }

  public String post(String to, String requestBody) throws IOException, InterruptedException {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create(to))
      .POST(HttpRequest.BodyPublishers.ofString(requestBody))
      .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    responseCode = response.statusCode();
    return response.body();
  }


}
