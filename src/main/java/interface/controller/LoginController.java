package app.ui;

import app.bankAccount.*;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.InputEvent;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;

public class LoginController {

  @FXML
  private Button enter;

  @FXML
  private TextField userName;

  @FXML
  private PasswordField password;
  
  @FXML
  private Hyperlink resetPassword;

  public String getUserName() { return userName.getText(); }

  public String getPassword() { return password.getText(); }

  public void processCredentials(InputEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
    FXMLLoader loader = new FXMLLoader(getClass().getResource(AppNavigator.DASHBOARD));
    //FXMLLoader loader = FXMLLoader.load(getClass().getResource(AppNavigator.DASHBOARD));
    GridPane root = null;
    try { 
      //root = (GridPane) loader.load();
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //loader.<DashboardController>getController().setUser(user); 
    Context.getInstance().setUser(getUserName(), getPassword());
    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);
    stage.show();
  } 

  @FXML
  public void initialize() {
    userName.setOnKeyPressed(event -> {
      if(event.getCode().equals(KeyCode.ENTER)) {
          password.requestFocus(); 
      }
    }); 

    password.setOnKeyPressed(event -> {
      if (event.getCode().equals(KeyCode.ENTER)) {
        processCredentials(event);
      } 
    });
  } 
}
