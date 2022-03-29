package app.fareye;

// JUnit 5
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class ClientTests {
  private Client client;
  private Client allen;
  private Client bateman;

  @BeforeEach
  public void setUp() {
    this.client = new Client("John", "M.", "Doe", "money");
    this.paul = new Client("Paul", "Gardner", "Allen", "startup");
    this.bateman = new Client("Patrick", "Stewart", "Bateman", "murder");
  }

  public static void canGenerateNonNullPassword() {
    String result = this.client.generatePassword(32);
    assertNotNull(result, "Should generate a non null password");
    assertEquals(result.length(), "Password should be 32 characters long");
  }
}
