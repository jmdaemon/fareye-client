package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositController {

  @FXML
  private Button backButton;

  @FXML
  private TextField depositAmount;

  @FXML
  private Label depositAmtLabel;

  @FXML
  private Button depositButton;

  @FXML
  private Label currency;

  public void depositMoney() {
    double amount = Double.parseDouble(depositAmount.getText());
    (AppNavigator.getUser()).deposit(amount);
  }
}
