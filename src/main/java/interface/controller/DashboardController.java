package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputEvent;

public class DashboardController {
  @FXML 
  private OverlayController overlayController;

  public void setUser(BankAccount acct) {
    overlayController.setUser(acct);
  }

  @FXML
  public void initialize() { }
}
