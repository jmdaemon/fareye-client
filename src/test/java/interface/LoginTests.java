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
  private UI appInterface = new UI();
  private String[] nullStringArray = new String[2];
  //private static Stage newStage;

  //public static void createApp() {
  @Start
  //public void createApp() {
  public void start(Stage stage) throws Exception {
    //try { 
    appInterface = new UI();
    //Stage newStage = new Stage();
    //appInterface.start(newStage); 
    appInterface.start(stage); 
    //appInterface.main(new String[0]);
    //} catch(Exception e) {
      //e.printStackTrace();
    //}
  }
  
  //@BeforeAll
  //public static void setUp() {
  //@BeforeEach
  //public void setUp() {
    //Platform.runLater(this::createApp);
    //Platform.runLater(LoginTests::createApp);
    //Platform.startup(LoginTests::createApp);
    //Platform.runLater(new Runnable() { LoginTests.appInterface.main(nullStringArray); });
  //}

  //@BeforeEach
  //public void startApp() throws Exception {
    //appInterface.start(newStage); 
  //}

  @Test
  void ComponentsMatchSetText(FxRobot robot) {
      // Match label texts
      //FxAssert.verifyThat("#userName", TextInputControlMatchers.hasText("Username"));
      //FxAssert.verifyThat("#password", TextInputControlMatchers.hasText("Password"));
      //FxAssert.verifyThat("#userName", TextMatchers.hasText("Username"));
      //FxAssert.verifyThat("#password", TextMatchers.hasText("Password"));
      //TextInputControl textField = (TextInputControl) srcControl.get();

      // Check that these components exist
      FxAssert.verifyThat("#userName", NodeMatchers.isNotNull());
      FxAssert.verifyThat("#password", NodeMatchers.isNotNull());
      FxAssert.verifyThat("#enter", NodeMatchers.isNotNull());

      //robot.clickOn("#enter");
      //FxAssert.verifyThat("#overlay", NodeMatchers.isNotNull());
  }

  @Test
  void on_enter_goto_dashboard(FxRobot robot) {
      ////robot.clickOn("#enter");
      //////robot.clickOn("button");
      ////FxAssert.verifyThat("#overlay", NodeMatchers.isNotNull());
      //FxAssert.verifyThat("#enter", NodeMatchers.isNotNull());
      //robot.clickOn(NodeQuery.lookup("#enter").query());
      robot.write("abcd\n"); // \n => Go to Password Field
      robot.write("abcd\n"); // \n => Hit Enter
      //robot.clickOn("#enter");
      FxAssert.verifyThat("#overlay", NodeMatchers.isNotNull());
  }
}
