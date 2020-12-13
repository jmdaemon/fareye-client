package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

public class HttpTests {
  private static final String site = "http://webcode.me";

  @Test
  public void sendGET_ToGoogle_ReturnResponse() throws Exception {
    Http conn = new Http();
    //String response = conn.get("http://google.com", "");
    //String response = conn.get("http://localhost:8080", "");
    String response = conn.get(site, "");
    System.out.println(response);
    assertNotNull(response);
  }

  @Test
  public void sendPOST_ToGoogle_ReturnResponse() throws Exception {
    Http conn = new Http();
    //String response = conn.post("http://google.com", "");
    //String response = conn.post("http://localhost:8080", "");
    String response = conn.post(site, "");
    System.out.println(response);
    assertNotNull(response);
  }
  
}
