package fareye;
//package test.fareye;

//import fareye.*;

// JUnit 5
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientTests {
  private static Client client = null;
  private static Client paul = null;
  private static Client bateman = null;

  @BeforeEach
  public void setUp() {
    client = new Client("John", "M.", "Doe", "money");
    paul = new Client("Paul", "Gardner", "Allen", "startup");
    bateman = new Client("Patrick", "Stewart", "Bateman", "murder");
  }

  //public static void canGenerateNonNullPassword() {
  @Test
  void canGenerateNonNullPassword() {
    String result = client.generatePassword(32);
    assertNotNull(result, "Should generate a non null password");
    assertEquals(result.length(), "Password should be 32 characters long");
  }
}
