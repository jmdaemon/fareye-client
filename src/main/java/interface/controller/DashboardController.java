package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;

public class DashboardController {

  @FXML
  void gotoDepositView(InputEvent event) { AppNavigator.loadApp(AppNavigator.DEPOSIT); }

  public void loadDepositView(InputEvent event) {
    gotoDepositView(event);
  }

}
