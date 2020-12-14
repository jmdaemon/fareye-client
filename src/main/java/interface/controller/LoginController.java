package app.ui;

import java.util.HashMap;
//import javafx.collections.ObservableMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
//import javafx.event.MouseEvent;
import javafx.scene.input.MouseEvent; 

public class LoginController {
  private HashMap<String, String> userCreds;
  //private ObservableMap<String, String> userCreds;

  @FXML
  private Button enter;

  @FXML
  private TextField userName;

  @FXML
  private PasswordField password;
  
  @FXML
  private Hyperlink resetPassword;

  public String getUserName() {
    return userName.getText();
  }

  public String getPassword() {
    return password.getText();
  }

  //public void handleMouseClick (MouseEvent event) {
  public void handleMouseClick (MouseEvent event) {
    userCreds = new HashMap<String, String>();
    userCreds.put("userName", getUserName());
    userCreds.put("password", getPassword());
    System.out.println(userCreds.get("userName") + " " + userCreds.get("password"));
  }
  
}
