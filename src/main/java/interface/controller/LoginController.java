package app.ui;

import java.util.HashMap;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent; 

public class LoginController {
  private HashMap<String, String> userCreds;

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

  public void handleMouseClick (MouseEvent event) throws IOException {
    userCreds = new HashMap<String, String>();
    userCreds.put("userName", getUserName());
    userCreds.put("password", getPassword());

    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/DashboardView.fxml"));
    GridPane root = loader.load();
    root.getStylesheets().add(getClass().getResource("/resources/assets/Dashboard.css").toExternalForm());

    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);
  }

  //public void handleEnterUserName(ActionEvent event) {
    ////handleMouseClick(event);
  //}

  //public void handleEnterCredentials(ActionEvent event) {
    //handleMouseClick((MouseEvent) event);
  //}

  public HashMap<String, String> getUserCreds() {
    return this.userCreds;
  }
  
}
