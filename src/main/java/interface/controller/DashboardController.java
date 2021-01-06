package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;

//import org.kordamp.ikonli.*;
//import org.kordamp.ikonli.javafx.*;
//import org.kordamp.ikonli.fontawesome5.*;

public class DashboardController {
  //private BankAccount user;

  //@FXML
  //private OverlayController overlayController;

  //public void setUser(BankAccount user) {
    //this.user = user;
    //overlayController.setUser(user);
  //}

  @FXML
  void gotoDepositView(InputEvent event) { AppNavigator.loadApp(AppNavigator.DEPOSIT); }

  public void loadDepositView(InputEvent event) {
    gotoDepositView(event);
  }

  //@FXML
  //public void initialize() {
    //setUser(AppNavigator.getUser());
  //}
}
