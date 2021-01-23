package app.ui;

import app.bankAccount.*;

import java.util.EventObject;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;

public class Navigator {

  public static final String DASHBOARD  = "/resources/fxmls/DashboardView.fxml";
  public static final String OVERLAY    = "/resources/fxmls/OverlayView.fxml";
  public static final String LOGIN      = "/resources/fxmls/LoginView.fxml";
  public static final String MENU_BAR   = "/resources/fxmls/MenuBar.fxml";
  public static final String MAIN       = "/resources/fxmls/Main.fxml";
  public static final String DEPOSIT    = "/resources/fxmls/DepositView.fxml";

  private static MainController mainController;

  public static void setMainController(MainController mainController) {
      Navigator.mainController = mainController;
  }

  public static void loadApp(String fxml) { 
    try { 
      mainController.setView(FXMLLoader.load(Navigator.class.getResource(fxml)));
      } catch (IOException e) {
          e.printStackTrace();
      }
  }

  public static void loadUser(BankAccount user) {
    mainController.setUser(user);
  }

  public static BankAccount getUser() {
    return mainController.getUser();
  }

  public static void loadScene(EventObject event, String fxml) { 
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(Navigator.class.getResource(fxml));

    AnchorPane root = null;
    try { 
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    setScene(stage, root);
  }

  public static void loadScene(EventObject event, String fxml, String userName, String password) { 
    //Context.getInstance().setUser(userName, password); 
    Context.getInstance().setUser(new BankAccount(userName, password)); 
    loadScene(event, fxml);
  }

  //public static void setScene(Stage stage, GridPane root) {
  public static void setScene(Stage stage, AnchorPane root) {
    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);
    stage.show();
  } 
}
