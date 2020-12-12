package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

public class HttpsConnectionTests { 
  @Test
  public void ping_Google_ReturnsResult() {
    HttpsConnection connection = new HttpsConnection();
    String result = connection.ping("https://google.com");
    System.out.println(result);
    assertNotNull(result);
  }
}
