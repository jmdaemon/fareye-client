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
  //@FXML
  //void nextPane(ActionEvent event) { AppNavigator.loadApp(AppNavigator.DASHBOARD); }

  //@FXML
  //void nextPane(InputEvent event) { AppNavigator.loadApp(AppNavigator.DASHBOARD); }

  //@FXML
  //void nextPane(InputEvent event, BankAccount user) { 
    //AppNavigator.loadUser(user);
    //AppNavigator.loadApp(AppNavigator.DASHBOARD); 
  //}

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
    BankAccount user = new BankAccount(getUserName(), getPassword());
    //nextPane(event, new BankAccount(getUserName(), getPassword()));
    //GridPane root = null;
    //Stage stage = null;
    ////FXMLLoader loader = AppNavigator.getLoader(event, root, stage, AppNavigator.DASHBOARD);
    //Object[] query = AppNavigator.getLoader(event, root, stage, AppNavigator.DASHBOARD);
    //FXMLLoader loader = (FXMLLoader) query[0];
    //stage = (Stage) query[1];
    //root = (GridPane) query[2];
    ////stage.setRoot(root);
    ////loader.<DashboardController>getController().setUser(user); 
    //Scene scene = ((Node)event.getSource()).getScene();
    ////scene.setRoot((Parent)root);
    //scene.setRoot(root);
    //stage.setScene(scene); 

    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
    FXMLLoader loader = new FXMLLoader(getClass().getResource(AppNavigator.DASHBOARD));
    GridPane root = null;
    try { 
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //loader.<DashboardController>getController().setUser(user); 
    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);

    //AppNavigator.setScene(stage, root);
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
