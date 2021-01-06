package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

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

}
