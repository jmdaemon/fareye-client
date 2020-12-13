package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

public class HttpsTests { 
  @Test
  public void ping_Google_ReturnsResult() {
    //Https conn = new HttpsConnection();
    Https conn = new Https();
    String result = conn.ping("https://google.com");
    //System.out.println(result); // Print html response
    assertNotNull(result);
  }
}
