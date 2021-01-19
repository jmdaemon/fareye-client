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

public class AppNavigator {

  public static final String DASHBOARD  = "/resources/fxmls/DashboardView.fxml";
  public static final String OVERLAY    = "/resources/fxmls/OverlayView.fxml";
  public static final String LOGIN      = "/resources/fxmls/LoginView.fxml";
  public static final String MENU_BAR   = "/resources/fxmls/MenuBar.fxml";
  public static final String MAIN       = "/resources/fxmls/Main.fxml";
  public static final String DEPOSIT    = "/resources/fxmls/DepositView.fxml";

  private static MainController mainController;

  public static void setMainController(MainController mainController) {
      AppNavigator.mainController = mainController;
  }

  public static void loadApp(String fxml) { 
    try { 
      mainController.setView(FXMLLoader.load(AppNavigator.class.getResource(fxml)));
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

  //public static FXMLLoader getLoader(ActionEvent event, GridPane root, String fxml) { 
  //public static <T> FXMLLoader getLoader(T event, GridPane root, Stage stage, String fxml) { 
  //public static FXMLLoader getLoader(EventObject event, GridPane root, Stage stage, String fxml) { 
  public static Object[] getLoader(EventObject event, GridPane root, Stage stage, String fxml) { 
    //Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    //FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    FXMLLoader loader = new FXMLLoader(AppNavigator.class.getResource(fxml));

    //GridPane root = null;
    try { 
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    //return loader;
    //loader.<DashboardController>getController().setUser(user);
    return new Object[]{loader, stage, root};
  }

  public static void setScene(Stage stage, GridPane root) {
    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);
  }

}
