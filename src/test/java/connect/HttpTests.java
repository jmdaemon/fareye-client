package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpTests {
  private static final String getFrom = "http://webcode.me";
  private static final String postTo  = "https://httpbin.org/post";
  private HashMap<String, String> values;

  @BeforeEach
  private void setUp() { 
    this.values = new HashMap<String, String>() {{ 
      put("name", "John Doe"); 
      put ("occupation", "gardener"); 
    }};
  }

  @Test
  public void sendGET_ToSite_ReturnResponse() throws Exception {
    Http conn = new Http();
    String response = conn.get(getFrom);
    assertNotNull(response);
  }

  @Test
  public void sendPOST_ToSite_ReturnResponse() throws Exception {
    Http conn = new Http(); 
    var objectMapper = new ObjectMapper(); 
    String requestBody = objectMapper.writeValueAsString(values);
    String response = conn.post(postTo, requestBody);
    assertNotNull(response);
  }
  
}
