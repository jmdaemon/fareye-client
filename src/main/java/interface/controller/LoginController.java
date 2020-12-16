package app.ui;

import app.bankAccount.*;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.net.URL;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent; 
import javafx.scene.input.KeyCode;
import javafx.scene.input.InputEvent;

public class LoginController implements Initializable {
  //private HashMap<String, String> userCreds;

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

  public void processCredentials(InputEvent event) {
    //userCreds = new HashMap<String, String>();
    // Check creds to see if they match a user
    //userCreds.put("userName", getUserName());
    //userCreds.put("password", getPassword());
    BankAccount user = new BankAccount(getUserName(), getPassword());
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    ((Node)event.getSource()).setUserData(user);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/DashboardView.fxml"));
    GridPane root = null;
    try { 
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    root.getStylesheets().add(getClass().getResource("/resources/assets/Dashboard.css").toExternalForm());

    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);
  } 

  //public HashMap<String, String> getUserCreds() {
    //return this.userCreds;
  //}

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    userName.setOnKeyPressed(event -> {
    if(event.getCode().equals(KeyCode.ENTER)){
        password.requestFocus(); 
    } 
    }); 

    password.setOnKeyPressed(event -> {
      if (event.getCode().equals(KeyCode.ENTER)){  
        processCredentials(event);
      } 
    });
  } 
}
