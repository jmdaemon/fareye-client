package app.ui;

//import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginController {

  @FXML
  private Button enter;

  @FXML
  private Label userName_Label;

  @FXML
  private TextField userName;

  @FXML
  private Label password_Label;

  @FXML
  private PasswordField password;
  
  @FXML
  private Hyperlink resetPassword;

  public String getUserName() {
    return userName.getText();
  }
  
}
