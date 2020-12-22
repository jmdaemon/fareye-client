package app.connect;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.*;

import org.mockserver.integration.ClientAndServer;

public class HttpsTests { 
  private static ClientAndServer mockServer;
  private static final String getFrom = "http://webcode.me";
  private static final String postTo  = "https://httpbin.org/post";

  //@BeforeAll
  //public void startServer() { 
    //mockServer = new MockServerClient("localhost", 1080) 
      //.when( 
          //request() 
          //.withMethod("POST") 
          //.withPath("/login")
          //.withBody("{username: 'foo', password: 'bar'}")
          //) 
      //.respond( 
          //response() 
          //.withStatusCode(302) 
          //.withCookie( 
            //"sessionId", "2By8LOhBmaW5nZXJwcmludCIlMDAzMW"
            //) 
          //.withHeader( 
            //"Location", "https://www.mock-server.com"
            //)
          //);
      ////mockServer = startClientAndServer(1080);
  //} 

  //@AfterAll
  //public static void stopMockServer() {
      //mockServer.stop();
  //} 

  //@BeforeEach
  //private void setUp() { 
    //this.values = new HashMap<String, String>() {{ 
      //put("name", "John Doe"); 
      //put ("occupation", "gardener"); 
    //}};
  //}

  //@Test
  //public void ping_Google_ReturnsResult() {
    //Https conn = new Https();
    //String result = conn.ping("https://google.com");
    //assertNotNull(result);
  //}

  //@Test
  //public void sendGET_ToSite_ReturnResponse() throws Exception {
    //Https conn = new Https();
    //String response = conn.get(getFrom);
    //System.out.println(response);
    //assertNotNull(response);
  //}

  //@Test
  //public void sendPOST_ToSite_ReturnResponse() throws Exception {
    //Https conn = new Https(); 
    //var objectMapper = new ObjectMapper(); 
    //String requestBody = objectMapper.writeValueAsString(values);
    //String response = conn.post(postTo, requestBody);
    ////System.out.println(response);
    //assertNotNull(response);
  //}

}
