package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositController {
  private BankAccount user;

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
  
}
