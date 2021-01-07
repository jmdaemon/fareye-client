package test.ui;

import app.ui.*;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.*;

import java.lang.Runnable;
import javafx.application.Platform;
import javafx.stage.Stage;

import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.TextInputControlMatchers;
import org.testfx.matcher.control.TextMatchers;
import org.testfx.service.query.*;

@ExtendWith(ApplicationExtension.class)
public class LoginTests {
  private UI appInterface;

  @Start
  public void start(Stage stage) throws Exception {
    appInterface = new UI();
    appInterface.start(stage); 
  }
  
  @Test
  void on_start_load_login(FxRobot robot) {
      // Check that these components exist
      FxAssert.verifyThat("#userName", NodeMatchers.isNotNull());
      FxAssert.verifyThat("#password", NodeMatchers.isNotNull());
      FxAssert.verifyThat("#enter", NodeMatchers.isNotNull());
  }

  @Test
  void on_enter_load_dashboard(FxRobot robot) {
      robot.write("abcd\n"); // \n => Go to Password Field
      robot.write("abcd\n"); // \n => Hit Enter
      FxAssert.verifyThat("#overlay", NodeMatchers.isNotNull());
  }
}
