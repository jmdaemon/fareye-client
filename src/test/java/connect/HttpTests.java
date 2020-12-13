package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

public class HttpTests {

  @Test
  public void sendGET_ToGoogle_ReturnResponse() throws Exception {
    Http conn = new Http();
    //String response = conn.get("http://google.com", "");
    String response = conn.get("http://localhost:8080", "");
    System.out.println(response);
    assertNotNull(response);
  }

  @Test
  public void sendPOST_ToGoogle_ReturnResponse() throws Exception {
    Http conn = new Http();
    //String response = conn.post("http://google.com", "");
    String response = conn.post("http://localhost:8080", "");
    System.out.println(response);
    assertNotNull(response);
  }
  
}
