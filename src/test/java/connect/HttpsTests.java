package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

public class HttpsTests { 
  private static final String getFrom = "http://webcode.me";
  private static final String postTo  = "https://httpbin.org/post";

  @BeforeEach
  private void setUp() { 
    this.values = new HashMap<String, String>() {{ 
      put("name", "John Doe"); 
      put ("occupation", "gardener"); 
    }};
  }

  @Test
  public void ping_Google_ReturnsResult() {
    Https conn = new Https();
    String result = conn.ping("https://google.com");
    assertNotNull(result);
  }

  @Test
  public void sendGET_ToSite_ReturnResponse() throws Exception {
    Https conn = new Https();
    String response = conn.get(getFrom);
    System.out.println(response);
    assertNotNull(response);
  }

  @Test
  public void sendPOST_ToSite_ReturnResponse() throws Exception {
    Https conn = new Https(); 
    var objectMapper = new ObjectMapper(); 
    String requestBody = objectMapper.writeValueAsString(values);
    String response = conn.post(postTo, requestBody);
    //System.out.println(response);
    assertNotNull(response);
  }

}
