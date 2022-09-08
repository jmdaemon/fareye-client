package fareye;

// JUnit 5
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class ClientTests {

  @Test
  void canGeneratePasswords() {
    final int len = 32;
    String result = Client.generatePassword(len);
    assertNotNull(result);
    assertEquals(result.length(), len);
  }
}
